/*

Copyright (c) 2011, DynEd International, Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

	* Redistributions of source code must retain the above copyright notice, 
	  this list of conditions and the following disclaimer.

	* Redistributions in binary form must reproduce the above copyright notice, 
	  this list of conditions and the following disclaimer in the documentation 
	  and/or other materials provided with the distribution.

	* Neither the name of DynEd International, Inc. nor the names of its 
	  contributors may be used to endorse or promote products derived from this 
	  software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR 
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

package com.dyned.conf.comp;

import org.apache.log4j.Logger;

import com.dyned.conf.TimestampUtilities;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.TravelInformation;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXApplication;
import er.extensions.components.ERXComponent;

public class AttendeeTravelInformation extends CompCommon {

	private static Logger log = Logger.getLogger(AttendeeTravelInformation.class);
	
	public ERXComponent returnToPage;

	public Venue venue;
	public Attendee attendee;

	public String travelInformationError;

	private NSTimestamp travelArrivalDate;
	private NSTimestamp travelArrivalTime;
	private NSTimestamp travelDepartDate;
	private NSTimestamp travelDepartTime;
	
    public AttendeeTravelInformation(WOContext context) {
        super(context);
    }

    // Page data: ==========================================================================

    public void setVenue(Object aValue) {
    	venue = (Venue)aValue;
    }
    
    public void setAttendee(Object aValue) {
    	attendee = (Attendee)aValue;
    }

    public void setReturnToPage(Object aValue) {
    	returnToPage = (ERXComponent)aValue;
    }
    
    public TravelInformation travelInformation() {
    	
    	if (attendee.travelInformationForVenue(venue) == null) {
    		TravelInformation travelInformation =
    			(TravelInformation)EOUtilities.createAndInsertInstance(ec, TravelInformation.ENTITY_NAME);

    		travelArrivalDate = TimestampUtilities.timestampByAddingDays(venue.dateStart(), -1);
    		travelArrivalTime = new NSTimestamp(venue.dateStart());

    		travelDepartDate = TimestampUtilities.timestampByAddingDays(venue.dateEnd(), 1);
    		travelDepartTime = new NSTimestamp(venue.dateEnd());

    		travelInformation.setVenueRelationship(venue);
    		travelInformation.setAttendeeRelationship(attendee);
    		
    		log.error("Created missing travel information");
    	}

    	return attendee.travelInformationForVenue(venue);
    }

    // Get form data: ==========================================================================
    
    public NSTimestamp travelArrivalDate() {
    	return travelInformation().arrivalDate();
    }
    
    public NSTimestamp travelArrivalTime() {
    	return travelInformation().arrivalDate();
    }
    
    public NSTimestamp travelDepartDate() {
    	return travelInformation().departureDate();
    }
    
    public NSTimestamp travelDepartTime() {
    	return travelInformation().departureDate();
    }

    // Set form data: ==========================================================================

    public void setTravelArrivalDate(Object aValue) {
    	travelArrivalDate = (NSTimestamp)aValue;
    }
    
    public void setTravelArrivalTime(Object aValue) {
    	travelArrivalTime = (NSTimestamp)aValue;
    }
    
    public void setTravelDepartDate(Object aValue) {
    	travelDepartDate = (NSTimestamp)aValue;
    }
    
    public void setTravelDepartTime(Object aValue) {
    	travelDepartTime = (NSTimestamp)aValue;
    }

    // =========================================================================================

    public ERXComponent saveTravelInformation() {
		travelInformation().setArrivalDate(
				TimestampUtilities.timestampByAddingTime(travelArrivalDate, travelArrivalTime));
		travelInformation().setDepartureDate(
				TimestampUtilities.timestampByAddingTime(travelDepartDate, travelDepartTime));

		if (saveMyChanges("Failed to save travel information for attendee "+ attendee.nameFamily())) {
			return returnToPage;
		} else {
			travelInformationError = "Unable to save your travel information, please try again later.";
			return null;
		}
	}
    
    public ERXComponent cancelAttendeeProfile() {
    	ec.revert();
		return returnToPage;
    }

}
