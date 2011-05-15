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

import com.dyned.conf.DirectAction;
import com.dyned.conf.Session;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.appserver.ERXApplication;

public class AttendeeHomePage extends CompCommon {

	public String messageOnScreen;
	public Attendee attendee;
	public String secretCode;
	
	public Venue venueInList;

	public AttendeeHomePage(WOContext context) {
        super(context);
		messageOnScreen = new String();

		attendee = ((Session)session()).attendee;
    }
	
	public String statusTravelInformation() {
		if (attendee.travelInformationForVenue(venueInList) == null)
			return "Incomplete";
		else
			return "Ok";
	}
	
	public String statusHotelInformation() {
		if (attendee.hotelInformationForVenue(venueInList) == null)
			return "Incomplete";
		else
			return "Ok";
	}
	
	public String statusPassportInformation() {
		if (attendee.passportInformation() == null)
			return "Not Required";
		else
			return "Ok";
	}
	
	public String statusSelectedEvents() {
		if (venueInList.expired()) {
			return "Expired";
		}
		if (
				(attendee.selectedEventsForVenue(venueInList) == null) ||
				(attendee.selectedEventsForVenue(venueInList).isEmpty()) ||
				(attendee.selectedEventsForVenue(venueInList).count() == 0)
		)
			return "Incomplete";
		else
			return "Review";
	}
	
	public AttendeePassportInfoPage updatePassportInfo() {
		AttendeePassportInfoPage nextPage = (AttendeePassportInfoPage)pageWithName(AttendeePassportInfoPage.class);
		nextPage.setReturnToPage(this);
		return nextPage;
	}
	
	public ConferencePage updateViewVenu() {
		ConferencePage nextPage = (ConferencePage)pageWithName(ConferencePage.class);
		nextPage.setVenueForPage(venueInList);
		return nextPage;
	}
	
	public AttendeeEventsSignup updateEventsSignup() {
		AttendeeEventsSignup nextPage = (AttendeeEventsSignup)pageWithName(AttendeeEventsSignup.class);
		nextPage.setVenueForPage(venueInList);
		return nextPage;
	}
	
	public String urlToICalendar() {
    	NSMutableDictionary<String, Object> queryParams = new NSMutableDictionary<String, Object>();
    	queryParams.takeValueForKey(Boolean.FALSE, "wosid");
    	queryParams.takeValueForKey(venueInList.secretCode(), DirectAction.CONFID_KEY);
    	queryParams.takeValueForKey(attendee.userEmailAddress(), DirectAction.ATTENDEE_KEY);
    	return context().directActionURLForActionNamed("iCalendar", queryParams);
	}
	
	public AttendeeHomePage registerForVenue() {
		ERXApplication.log.info(attendee.nameFamily() + " registering for " + secretCode);
		Venue venueToRegisterFor = null;
		try {
			venueToRegisterFor = Venue.withSecretCode(ec, secretCode);
		} catch (RuntimeException ex) {
			ERXApplication.log.error("Execption looking up venue using scretCode.");
			ERXApplication.log.error(ex.getMessage());
			messageOnScreen = "Cound not look up an event with that code.";
			return null;
		}

		if (venueToRegisterFor != null) {
			// Check if the attendee is not already registered for it.
			if (attendee.venues().containsObject(venueToRegisterFor)) {
				ERXApplication.log.error("Attendee already registered for this venue.");
				messageOnScreen = "You are already registered for this event.";
				return null;
			} else {
				attendee.addToVenuesRelationship(venueToRegisterFor);
				if (saveMyChanges("Failed to save changes to database."))
					return (AttendeeHomePage)pageWithName(AttendeeHomePage.class);
				else
					return null;
			}
		} else {
			messageOnScreen = "Cound not find Conference with that code.";
			return null;
		}
	}
	
	public boolean pastVenuesAvailable() {
		return (attendee.pastVenues().count() > 0);
	}
	
	public boolean currentAndFutureVenuesAvailable() {
		return (attendee.currentAndFutureVenues().count() > 0);
	}
}