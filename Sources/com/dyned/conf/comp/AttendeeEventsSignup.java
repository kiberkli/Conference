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

import com.dyned.conf.Session;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.AttendeeSelectedVEvent;
import com.dyned.conf.eom.VEvent;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.appserver.ERXApplication;

public class AttendeeEventsSignup extends CompCommon {

	private static Logger log = Logger.getLogger(AttendeeEventsSignup.class);
	
	public Venue venue;
	public Attendee attendee;

	public AttendeeSelectedVEvent eventforAttendeeInList;
	public NSArray<AttendeeSelectedVEvent> eventForAttendeeList;

	public String messageOnScreen;
	public String eventInListError;


	public AttendeeEventsSignup(WOContext context) {
		super(context);
		messageOnScreen = new String();
		eventInListError = new String();

		attendee = ((Session)session()).attendee;
	}

//	public void setAttendeeAndVenueForPage(Attendee attendeeValue, Venue venueValue){
	public void setVenueForPage(Venue venueValue){
//		attendee = attendeeValue;
		venue = venueValue;
		eventForAttendeeList = myEventForAttendeeList();
	}

	public NSArray<AttendeeSelectedVEvent>myEventForAttendeeList() {
		NSArray<VEvent> venueEvents = venue.eventsSorted();
		NSArray<AttendeeSelectedVEvent> attendeeSelectedVEvents = attendee.selectedEventsForVenue(venue);

		NSMutableArray<AttendeeSelectedVEvent> results = attendeeSelectedVEvents.mutableClone(); //new NSMutableArray<AttendeeSelectedVEvent>();

		int attendeeSelectedVEventsCount = attendeeSelectedVEvents.count();

		for (int venueEventIndex = 0; venueEventIndex < venueEvents.count(); venueEventIndex++) {
			VEvent vEvent = venueEvents.objectAtIndex(venueEventIndex);
			boolean eventFound = false;
			// ------------
			for (int attendeeSelectedVEventIndex = 0; attendeeSelectedVEventIndex < attendeeSelectedVEventsCount; attendeeSelectedVEventIndex++) {
				VEvent attendeeSelectedVEvent = attendeeSelectedVEvents.objectAtIndex(attendeeSelectedVEventIndex).event();
				if (attendeeSelectedVEvent.equals(vEvent) ) {
					log.debug("Not adding " + vEvent.lable());
					eventFound = true;
					break;
				}
			}
			// ------------
			if (!eventFound) {
				log.debug("Adding event " + vEvent.lable());
				AttendeeSelectedVEvent newAttendeeSelectedVEvents = (AttendeeSelectedVEvent)(EOUtilities.createAndInsertInstance(ec, AttendeeSelectedVEvent.ENTITY_NAME));
				newAttendeeSelectedVEvents.setParticipants(0);
				newAttendeeSelectedVEvents.setEventRelationship(vEvent);
				newAttendeeSelectedVEvents.setVenueRelationship(venue);
				attendee.addToSelectedEventsRelationship(newAttendeeSelectedVEvents);
				results.addObject(newAttendeeSelectedVEvents);				
			}
		}
		return results; //attendee.selectedEventsForVenue(venue);
	}

	public AttendeeEventsSignup saveSelectedEventsList() {
		if (saveMyChanges("Failed to save selected events to database")) {
			AttendeeEventsSignup nextPage = pageWithName(AttendeeEventsSignup.class);
			//nextPage.setAttendeeAndVenueForPage(attendee, venue);
			nextPage.setVenueForPage(venue);
			return nextPage;
		} else {
			eventInListError = "Unable update your activities list, please try again later.";
			return null;
		}
	}
	
	public AttendeeRegistrationComplete completeRegistration() {
		AttendeeRegistrationComplete nextPage = (AttendeeRegistrationComplete)pageWithName(AttendeeRegistrationComplete.class);
		//nextPage.setAttendeeAndVenueForPage(attendee, venue);
		nextPage.setVenueForPage(venue);
		return nextPage;
	}

}