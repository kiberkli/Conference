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

import com.dyned.conf.TimestampUtilities;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.HotelInformation;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXApplication;
import er.extensions.components.ERXComponent;
import er.extensions.foundation.ERXTimestampUtilities;

public class AttendeeHotelReservation extends CompCommon {

	public ERXComponent returnToPage;

	public Venue venue;
	public Attendee attendee;

	//public HotelInformation hotelInformation;

	public boolean defaultHotel;
	private NSTimestamp hotelCheckInDate;
	private NSTimestamp hotelCheckInTime;
	private NSTimestamp hotelCheckOutDate;
	private NSTimestamp hotelCheckOutTime;
	
	public String hotelReservationError;

	public AttendeeHotelReservation(WOContext context) {
        super(context);
    }
	
	// Page Data ======================================================================================
	
	public void setVenue(Object aValue) {
		venue = (Venue)aValue;
	}
	
	public void setAttendee(Object aValue) {
		attendee = (Attendee)aValue;
	}
	
	public void setReturnToPage(Object aValue) {
		returnToPage = (ERXComponent)aValue;
	}
	
	public HotelInformation hotelInformation() {
		if (attendee.hotelInformationForVenue(venue) == null ) {
			HotelInformation hotelInformation = 
				(HotelInformation)EOUtilities.createAndInsertInstance(ec, HotelInformation.ENTITY_NAME);
			hotelCheckInDate = TimestampUtilities.timestampByAddingDays(venue.dateStart(), -1);
			hotelCheckInTime = TimestampUtilities.timestampByAddingDays(venue.dateStart(), -1);
			hotelCheckOutDate = new NSTimestamp(venue.dateEnd());
			hotelCheckOutTime = new NSTimestamp(venue.dateEnd());

			hotelInformation.setVenueRelationship(venue);
			hotelInformation.setAttendeeRelationship(attendee);
			
			ERXApplication.log.error("Created missing hotel information");
		}

		return attendee.hotelInformationForVenue(venue);
	}

	// Get form Data ======================================================================================

	public NSTimestamp hotelCheckInDate() {
		return hotelInformation().checkIn();
	}
	
	public NSTimestamp hotelCheckInTime() {
		return hotelInformation().checkIn();
	}
	
	public NSTimestamp hotelCheckOutDate() {
		return hotelInformation().checkOut();
	}

	public NSTimestamp hotelCheckOutTime() {
		return hotelInformation().checkOut();
	}

	// Set form Data ======================================================================================
	
	public void setHotelCheckInDate(Object aValue) {
		hotelCheckInDate = (NSTimestamp)aValue;
	}
	
	public void setHotelCheckInTime(Object aValue) {
		hotelCheckInTime = (NSTimestamp)aValue;
	}
	
	public void setHotelCheckOutDate(Object aValue) {
		hotelCheckOutDate = (NSTimestamp)aValue;
	}

	public void setHotelCheckOutTime(Object aValue) {
		hotelCheckOutTime = (NSTimestamp)aValue;
	}

	// ================================================================================================
	
	public ERXComponent saveHotelReservation() {

		hotelInformation().setCheckIn(
				ERXTimestampUtilities.timestampByAddingTime(hotelCheckInDate, hotelCheckInTime));
		hotelInformation().setCheckOut(
				ERXTimestampUtilities.timestampByAddingTime(hotelCheckOutDate, hotelCheckOutTime));
		
		if (defaultHotel) {
			hotelInformation().setHotelName(venue.facilityName());
			hotelInformation().setHotelAddress(venue.mapAddress());
		}
		
		if (saveMyChanges("Failed to save hotel reservation for attendee "+ attendee.nameFamily())) {
			return returnToPage;
		} else {
			hotelReservationError = "Unable to save your hotel information, please try again later.";
			return null;
		}
	}
	
	public ERXComponent cancelHotelReservation() {
		ec.revert();
		return returnToPage;
	}
}