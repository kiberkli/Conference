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

import com.dyned.conf.Application;
import com.dyned.conf.Session;
import com.dyned.conf.TimestampUtilities;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.HotelInformation;
import com.dyned.conf.eom.PassportInformation;
import com.dyned.conf.eom.TravelInformation;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOMailDelivery;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXApplication;
import er.extensions.components.ERXComponent;
import er.extensions.foundation.ERXTimestampUtilities;

public class ConferencePage extends CompCommon {
	
	private static Logger log = Logger.getLogger(ConferencePage.class);
	
	public ERXComponent thisPageComponet;

	public Venue venue;
	public Attendee attendee;

	public HotelInformation hotelInformation;
	public TravelInformation travelInformation;
	
//	public AttendeeSelectedVEvent eventforAttendeeInList;
//	public NSArray<AttendeeSelectedVEvent> eventForAttendeeList;
	
	public String messageOnScreen;
//	public String eventInListError;
	public String hotelReservationError;
	public String travelInformationError;
	public String letterOfInviteRequestError;
	
	public boolean showHotelReservation;
	public boolean defaultHotel;
	public NSTimestamp hotelCheckInDate;
	public NSTimestamp hotelCheckInTime;
	public NSTimestamp hotelCheckOutDate;
	public NSTimestamp hotelCheckOutTime;

	public boolean showTravelInformation;
	public NSTimestamp travelArrivalDate;
	public NSTimestamp travelArrivalTime;
	public NSTimestamp travelDepartDate;
	public NSTimestamp travelDepartTime;

	public String letterOfInviteRequestStatus;

	public ConferencePage(WOContext context) {
		super(context);
		
		thisPageComponet = this;
		
		messageOnScreen = new String();
		//eventInListError = new String();
		hotelReservationError = new String();
		travelInformationError = new String();
		letterOfInviteRequestError = new String();

		showHotelReservation = false;
		showTravelInformation = false;

		attendee = ((Session)session()).attendee;
		
		letterOfInviteRequestStatus = "Passport information is required for invitation letters.";
	}
	
//	public void setAttendeeAndVenueForPage(Attendee attendeeValue, Venue venueValue){
	public void setVenueForPage(Venue venueValue){
		//attendee = attendeeValue;
		venue = venueValue;
//		eventForAttendeeList = myEventForAttendeeList();

		hotelInformation = attendee.hotelInformationForVenue(venue);
		if (hotelInformation != null) {
			hotelCheckInDate = hotelInformation.checkIn();
			hotelCheckInTime = hotelInformation.checkIn();
			hotelCheckOutDate = hotelInformation.checkOut();
			hotelCheckOutTime = hotelInformation.checkOut();

			showHotelReservation = true;
		}

		travelInformation = attendee.travelInformationForVenue(venue);
		if (travelInformation != null) {
			travelArrivalDate = travelInformation.arrivalDate();
			travelArrivalTime = travelInformation.arrivalDate();
			travelDepartDate = travelInformation.departureDate();
			travelDepartTime = travelInformation.departureDate();

			showTravelInformation = true;
		}
		
	}

	public ConferencePage addTravelInformation() {
		showTravelInformation = true;

		if (travelInformation == null) {
			travelInformation =
				(TravelInformation)EOUtilities.createAndInsertInstance(ec, TravelInformation.ENTITY_NAME);

			travelArrivalDate = TimestampUtilities.timestampByAddingDays(venue.dateStart(), -1);
			travelArrivalTime = new NSTimestamp(venue.dateStart());

			travelDepartDate = TimestampUtilities.timestampByAddingDays(venue.dateEnd(), 1);
			travelDepartTime = new NSTimestamp(venue.dateEnd());

			//attendee.setTravelInformationRelationship(travelInformation);
			travelInformation.setVenueRelationship(venue);
			travelInformation.setAttendeeRelationship(attendee);
			
		}

		return null;
	}
	
	public ConferencePage saveTravelInformation() {
		travelInformation.setArrivalDate(
				TimestampUtilities.timestampByAddingTime(travelArrivalDate, travelArrivalTime));
		travelInformation.setDepartureDate(
				TimestampUtilities.timestampByAddingTime(travelDepartDate, travelDepartTime));

		if (saveMyChanges("Failed to save travel information for attendee "+ attendee.nameFamily())) {
			ConferencePage nextpage = pageWithName(ConferencePage.class);
//			nextpage.setAttendeeAndVenueForPage(attendee, venue);
			nextpage.setVenueForPage(venue);
			return nextpage;
		} else {
			travelInformationError = "Unable to save your travel information, please try again later.";
			return null;
		}
	}

	public ConferencePage addHotelReservations() {
		showHotelReservation = true;

		if (hotelInformation == null) {
			hotelInformation = 
				(HotelInformation)EOUtilities.createAndInsertInstance(ec, HotelInformation.ENTITY_NAME);
			hotelCheckInDate = TimestampUtilities.timestampByAddingDays(venue.dateStart(), -1);
			hotelCheckInTime = TimestampUtilities.timestampByAddingDays(venue.dateStart(), -1);
			hotelCheckOutDate = new NSTimestamp(venue.dateEnd());
			hotelCheckOutTime = new NSTimestamp(venue.dateEnd());

			//attendee.setHotelInformationRelationship(hotelInformation);
			hotelInformation.setVenueRelationship(venue);
			hotelInformation.setAttendeeRelationship(attendee);
		}

		return null;
	}
	
	public ConferencePage saveHotelReservation() {

		hotelInformation.setCheckIn(
				ERXTimestampUtilities.timestampByAddingTime(hotelCheckInDate, hotelCheckInTime));
		hotelInformation.setCheckOut(
				ERXTimestampUtilities.timestampByAddingTime(hotelCheckOutDate, hotelCheckOutTime));
		
		if (defaultHotel) {
			hotelInformation.setHotelName(venue.facilityName());
			hotelInformation.setHotelAddress(venue.mapAddress());
		}
		
		if (saveMyChanges("Failed to save hotel reservation for attendee "+ attendee.nameFamily())) {
			ConferencePage nextpage = pageWithName(ConferencePage.class);
//			nextpage.setAttendeeAndVenueForPage(attendee, venue);
			nextpage.setVenueForPage(venue);
			return nextpage;
		} else {
			hotelReservationError = "Unable to save your hotel information, please try again later.";
			return null;
		}
	}
	
	public boolean disabledRequestLetterOfInviteButton() {
		return (attendee.passportInformation() == null);
	}
	
	public ConferencePage requestLetterOfInvite() {

		NSMutableDictionary<String,Object> queryDictionary = new NSMutableDictionary<String,Object>();
		queryDictionary.takeValueForKey(attendee.userEmailAddress(), "attendee");
		queryDictionary.takeValueForKey(Boolean.FALSE, "wosid");
		
		String attendeeProfileURL = context().directActionURLForActionNamed(
				"adminsLogin", 
				queryDictionary, 
				((Application)application()).makeSecureLinks, 
				true);
				
		// Start the mail process here:
		String theSubject = new String("Letter of Invite requested for " + venue.lable());
		StringBuffer theBody = new StringBuffer(theSubject+"\r\n\r\n");
		String theSender = new String(attendee.userEmailAddress());
		NSMutableArray<Object> theRecipients = new NSMutableArray<Object>();
		
		theRecipients.add("kwang@dyned.com.cn");
		theRecipients.add("fgao@dyned.com.cn");
		theRecipients.add(venue.admin().emailAddress());
		
		theBody.append("\r\n");
		theBody.append(attendee.nameGiven() + " " + attendee.nameFamily() +" has requested a formal letter of invite for " +
				venue.lable() + ":\r\n");
		theBody.append("\r\n");
		theBody.append("Name:\r\n");
		theBody.append("- Familly:     " + attendee.nameFamily() + "\r\n");
		theBody.append("- Give/First:  " + attendee.nameGiven() + "\r\n");
		theBody.append("\r\n");
		if (attendee.travelInformationForVenue(venue) != null) {
			theBody.append("Travel Dates:\r\n");
			theBody.append("- Arrival:    " + 
					attendee.travelInformationForVenue(venue).arrivalDateToString() +"\r\n");
			theBody.append("- Departure:  " + 
					attendee.travelInformationForVenue(venue).departureDateToString() +"\r\n");
		} else 
			theBody.append("Travel information is not available at this time.\r\n");
		theBody.append("\r\n");
		theBody.append("Look up Passport Info:\r\n");
		theBody.append("- " + attendeeProfileURL + "\r\n");
		theBody.append("\r\n");
		theBody.append("You may reply to this email to request more information from "  + attendee.nameGiven() + " " + attendee.nameFamily() + ".");
		theBody.append("\r\n");
		theBody.append("\r\n");

		log.info(theBody.toString());

		WOMailDelivery mailMessage = WOMailDelivery.sharedInstance();
		try {
			mailMessage.composePlainTextEmail(
					theSender,
					theRecipients.immutableClone(),
					null,
					theSubject,
					theBody.toString(),
					WOMailDelivery.SEND_NOW
			);
			letterOfInviteRequestStatus = "Request for invitaion letter has been submitted.";
		} catch (RuntimeException ex) {
			letterOfInviteRequestError = "Unable to notify our staff of your request, please try again later.";
			log.error("Unable to send visa request email for " + attendee.nameFamily());
			log.error("Sender: " + theSender);
			log.error("Body:");
			log.error(theBody.toString());
			log.error(ex.getMessage());
		}

		return null;
	}
	
	public AttendeePassportInfoPage addPassportInformation() {
		if (attendee.passportInformation() == null) {
			PassportInformation passportInformation = (PassportInformation)EOUtilities.createAndInsertInstance(ec, PassportInformation.ENTITY_NAME);
			passportInformation.setDateRequested(new NSTimestamp());
			passportInformation.setNameFamily(attendee.nameFamily());
			passportInformation.setNameGiven(attendee.nameGiven());
//			if (attendee.travelInformationForVenue(venue) != null) {
//				passportInformation.setDateArrival(attendee.travelInformationForVenue(venue).arrivalDate());
//				passportInformation.setDateDeparture(attendee.travelInformationForVenue(venue).departureDate());
//			}
			attendee.setPassportInformationRelationship(passportInformation);
		}

		AttendeePassportInfoPage nextPage = (AttendeePassportInfoPage)pageWithName(AttendeePassportInfoPage.class);
		nextPage.setReturnToPage(this);
		return nextPage;
	}
	
	public AttendeeEventsSignup eventsSignup() {
		AttendeeEventsSignup nextPage = (AttendeeEventsSignup)pageWithName(AttendeeEventsSignup.class);
		//nextPage.setAttendeeAndVenueForPage(attendee, venue);
		nextPage.setVenueForPage(venue);
		return nextPage;
	}
	

//	public NSArray<AttendeeSelectedVEvent>myEventForAttendeeList() {
//		NSArray<VEvent> venueEvents = venue.eventsSorted();
//		NSArray<AttendeeSelectedVEvent> attendeeSelectedVEvents = attendee.selectedEventsForVenue(venue);
//		
//		NSMutableArray<AttendeeSelectedVEvent> results = attendeeSelectedVEvents.mutableClone(); //new NSMutableArray<AttendeeSelectedVEvent>();
//
//		int attendeeSelectedVEventsCount = attendeeSelectedVEvents.count();
//		
//		for (int venueEventIndex = 0; venueEventIndex < venueEvents.count(); venueEventIndex++) {
//			VEvent vEvent = venueEvents.objectAtIndex(venueEventIndex);
//
//			boolean eventFound = false;
//
//			for (int attendeeSelectedVEventIndex = 0; attendeeSelectedVEventIndex < attendeeSelectedVEventsCount; attendeeSelectedVEventIndex++) {
//				VEvent attendeeSelectedVEvent = attendeeSelectedVEvents.objectAtIndex(attendeeSelectedVEventIndex).event();
//				 
//				if (attendeeSelectedVEvent.equals(vEvent) ) {
//					log.info("Not adding " + vEvent.lable());
//					eventFound = true;
//					break;
//				}
//			}
//
//			if (!eventFound) {
//				log.info("Adding event " + vEvent.lable());
//				AttendeeSelectedVEvent newAttendeeSelectedVEvents = (AttendeeSelectedVEvent)(EOUtilities.createAndInsertInstance(ec, AttendeeSelectedVEvent.ENTITY_NAME));
//				newAttendeeSelectedVEvents.setParticipants(0);
//				newAttendeeSelectedVEvents.setEventRelationship(vEvent);
//				newAttendeeSelectedVEvents.setVenueRelationship(venue);
//				attendee.addToSelectedEventsRelationship(newAttendeeSelectedVEvents);
//				results.addObject(newAttendeeSelectedVEvents);				
//			}
//		}
//		
//		return results; //attendee.selectedEventsForVenue(venue);
//	}
	
//	public ConferencePage saveSelectedEventsList() {
//		if (saveMyChanges("Failed to save selected evetns to database")) {
//			ConferencePage nextPage = pageWithName(ConferencePage.class);
//			nextPage.setAttendeeAndVenueForPage(attendee, venue);
//			return nextPage;
//		} else {
//			eventInListError = "Unable update your activities list, please try again later.";
//			return null;
//		}
//	}

	/* Bindings:

EventsList : WORepetition {
	list = eventForAttendeeList;
	item = eventforAttendeeInList;
}

EventInListLable : WOString {
	value = eventforAttendeeInList.event.lable;
}

EventInListTimeStart : WOString {
	value = eventforAttendeeInList.event.dateTimeStart;
	dateformat = "%H:%M";
}

EventInListTimeEnd : WOString {
	value = eventforAttendeeInList.event.dateTimeEnd;
	dateformat = "%H:%M";
}

EventInListDay : WOString {
	value = eventforAttendeeInList.event.dateTimeStart;
	dateformat = "%A";
}

EventInListDate : WOString {
	value = eventforAttendeeInList.event.dateTimeStart;
	dateformat = "%m/%d";
}

EventInListDescription : WOString {
	value = eventforAttendeeInList.event.descriptionForHTML;
	escapeHTML = false;
}

EventInListMeetingPlace : WOString {
	value = eventforAttendeeInList.event.meetingPlace;
}

EventInListParticipants : WOTextField {
	value = eventforAttendeeInList.participants;
	size = "2";
	class = "numberfield";
}

EventInLlistError : WOString {
	value = eventInListError;
}

SelectedEventsForm : WOForm {
}

SelectedEventsListSubmit : WOSubmitButton {
	action = saveSelectedEventsList;
	value = "Update";
}

	 */

	/* HTML:

<!-- Fundays and Excursions Below -->
<h4>Sign Up for Fundays and Excursions</h4>

<p>Indicate the number of participants (you plus family members) for each Activity:</p>

<webobject name = "SelectedEventsForm">
<table cellpadding="0" cellspacing="0" border="0" class="inline">
<tr>
	<th>Participants</th>
	<th colspan="3">Date</th>
	<th>Activity</th>
	<th nowrap>Meeting Place</th>
</tr>
<webobject name = "EventsList">
<tr class="alternating">
	<td style="text-align: center;"><webobject name = "EventInListParticipants"/></td>
	<td><webobject name = "EventInListDay"/></td>
	<td><webobject name = "EventInListDate"/></td>
	<td nowrap><webobject name = "EventInListTimeStart"/>-<webobject name = "EventInListTimeEnd"/></td>
	<td><b><webobject name = "EventInListLable"/></b><br><webobject name = "EventInListDescription"/></td>
	<td style="text-align: center;"><webobject name = "EventInListMeetingPlace"/></td>
</tr>
</webobject>
<tr>
	<td colspan="6" style="text-align: left;"><webobject name = "SelectedEventsListSubmit"/>
	<input type="reset" value="Revert">
	<em><webobject name = "EventInLlistError"/></em></td>
</tr>
</table>
</webobject>
<!-- Fundays and Excursions Below -->

	 */
}

























/*

NSArray<VEvent> venueEvents = venue.events();

NSMutableArray<AttendeeSelectedVEvent> results = new NSMutableArray<AttendeeSelectedVEvent>(attendee.selectedEventsForVenue(venue));

for (int venueEventIndex = 0; venueEventIndex < venueEvents.count(); venueEventIndex++) {
	VEvent vEvent = venueEvents.objectAtIndex(venueEventIndex);

	int attendeeSelectedVEventsCount = results.count();
	boolean eventNeedsToBeAdded = false;

	for (int attendeeSelectedVEventIndex = 0; attendeeSelectedVEventIndex < attendeeSelectedVEventsCount; attendeeSelectedVEventIndex++) {
		VEvent attendeeSelectedVEvent = results.objectAtIndex(attendeeSelectedVEventIndex).event();
		 
		if (! attendeeSelectedVEvent.equals(vEvent) ) {
			log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Not adding " + vEvent.lable());
			eventNeedsToBeAdded = true;
		}
	}

	if (eventNeedsToBeAdded) {
		log.info("Adding event " + vEvent.lable());
		AttendeeSelectedVEvent newAttendeeSelectedVEvents = (AttendeeSelectedVEvent)(EOUtilities.createAndInsertInstance(ec, AttendeeSelectedVEvent.ENTITY_NAME));
		newAttendeeSelectedVEvents.setParticipants(0);
		newAttendeeSelectedVEvents.setEventRelationship(vEvent);
		newAttendeeSelectedVEvents.setVenueRelationship(venue);
		attendee.addToSelectedEventsRelationship(newAttendeeSelectedVEvents);
		results.addObject(newAttendeeSelectedVEvents);				
	}
}

return results; //attendee.selectedEventsForVenue(venue);

 */
