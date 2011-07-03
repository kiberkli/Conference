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

package com.dyned.conf;

import org.apache.log4j.Logger;

import com.webobjects.appserver.*;
import com.webobjects.eoaccess.EOObjectNotAvailableException;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimeZone;

import er.calendar.ERPublishCalendarPage;
import er.extensions.appserver.*;
import er.extensions.eof.ERXEC;

import com.dyned.conf.CalendarEvent;
import com.dyned.conf.comp.AdminsLogin;
import com.dyned.conf.comp.AdminsPage;
import com.dyned.conf.comp.AttendeeHomePage;
import com.dyned.conf.comp.AttendeeRegistrationPage;
import com.dyned.conf.comp.AttendeesLostPasswordPage;
import com.dyned.conf.comp.Main;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.Venue;

public class DirectAction extends ERXDirectAction {
	
	private static Logger log = Logger.getLogger(DirectAction.class);
	
	public static final String ATTENDEE_KEY = "attendee";
	public static final String CONFID_KEY = "confid";
	public static final String EMAIL_KEY = "email";
	public static final String PASSWORD_KEY = "password";

	public DirectAction(WORequest request) {
		super(request);
	}

	@Override
	public WOActionResults defaultAction() {
		return pageWithName(Main.class);
	}

	public WOActionResults attendeeLostPasswordPageAction() {
		return pageWithName(AttendeesLostPasswordPage.class);
	}
	
	public WOActionResults adminsPageAction() {
		return pageWithName(AdminsPage.class);
	}

	public WOActionResults adminsLoginAction() {
		String attendeeEmail = request().stringFormValueForKey(DirectAction.ATTENDEE_KEY);
		AdminsLogin nextPage = (AdminsLogin)pageWithName(AdminsLogin.class);

		// The attendee email is here from a link when requesting an invite letter for visa request.
		if (attendeeEmail != null && attendeeEmail.length() > 0)
			nextPage.setAttendeeEmail(attendeeEmail);

		return nextPage;
	}

	public WOActionResults attendeeRegistrationAction() {
		String secretCode = request().stringFormValueForKey(DirectAction.CONFID_KEY);
		String userEMailAddress = request().stringFormValueForKey(DirectAction.EMAIL_KEY);
		String userPassword = request().stringFormValueForKey(DirectAction.PASSWORD_KEY);

		EOEditingContext ec = session().defaultEditingContext();
		((Session)session()).setTimeOut(300);

		// Let's see if the user provided an email and password so they don't register again.
		if (
				(userEMailAddress != null && userEMailAddress.length() > 0)
		) {
			if (userPassword == null || userPassword.length() == 0) {
				log.error("Missing password for " + userEMailAddress);
				Main nextPage = (Main)pageWithName(Main.class);
				nextPage.setMessageOnScreen("Missing password");
				return nextPage;
			}

			Attendee attendee = null;

			//Object[] keys = {Attendee.USER_EMAIL_ADDRESS_KEY, Attendee.USER_PASSWORD_KEY};
			//Object[] values = {userEMailAddress, userPassword};
			Object[] keys = {Attendee.USER_EMAIL_ADDRESS_KEY};
			Object[] values = {userEMailAddress};
			NSDictionary<Object, Object> bindings = new NSDictionary<Object, Object>(values, keys);

			try {
				attendee = (Attendee)EOUtilities.objectMatchingValues(ec, Attendee.ENTITY_NAME, bindings);
			} catch (EOUtilities.MoreThanOneException e1) {
				log.error("More then one with email address " + userEMailAddress);
				log.error(e1.getMessage());

				Main nextPage = (Main)pageWithName(Main.class);
				nextPage.setMessageOnScreen("Bad login, please try again");
				return nextPage;
			} catch (EOObjectNotAvailableException e2) {
				log.error("Invalid login by " + userEMailAddress);
				log.error(e2.getMessage());

				Main nextPage = (Main)pageWithName(Main.class);
				nextPage.setMessageOnScreen("Invalid Login, please try again");
				return nextPage;
			} catch (RuntimeException e3) {
				log.error("Full runtime exception, logging in " + userEMailAddress);
				log.error(e3.getMessage());

				Main nextPage = (Main)pageWithName(Main.class);
				nextPage.setMessageOnScreen("Unable to log you in at this time");
				return nextPage;
			}
			
			if (attendee != null && attendee.pwHashCodeInt() != userPassword.hashCode()) {
				if (attendee.userPassword() != null && attendee.userPassword().compareTo(userPassword) == 0) {
    				// Log in ok with old password, reset the password to hashcode.
					attendee.setUserPassword(attendee.userPassword());
    				try {
    					ec.saveChanges();
    				} catch (RuntimeException ex) {
    					ec.revert();
    					log.error("User logged in but we failed to upgrade password for " + userEMailAddress);
    					log.error(ex.getMessage());
    					ex.printStackTrace();
    				}
    			} else {
    				log.error("Attendee " + userEMailAddress + " doees not have a upgraded password nor a matching old password.");

    				Main nextPage = (Main)pageWithName(Main.class);
    				nextPage.setMessageOnScreen("Invalid Login, please try again");
    				return nextPage;
    			}
			}

			if (attendee != null) {
				((Session)session()).setAttendee(attendee);
				((Session)session()).setAdministrator(null);
				((Session)session()).setTimeOut(3600);
				
    			log.info("Attendee " + attendee.fullName() + " ("+attendee.userEmailAddress()+") logged in.");

    			// TODO Change this code to go the venue's specific page if there is only one registered.
				/*
				if (attendee.venues().count() > 1) {
					AttendeeHomePage attendeeHomePage = pageWithName(AttendeeHomePage.class);
					return attendeeHomePage;
				} else {
					ConferencePage conferencePage = pageWithName(ConferencePage.class);
					conferencePage.setVenueForPage(attendee.venues().lastObject());
					return conferencePage;
				}
				*/
				
				// Using this because the Beijing conference is over.
				AttendeeHomePage attendeeHomePage = pageWithName(AttendeeHomePage.class);
				return attendeeHomePage;
				
			} else {
				Main nextPage = (Main)pageWithName(Main.class);
				nextPage.setMessageOnScreen("Unknown error, please try again later");
				return nextPage;
			}
		}

		// If the person give us the secretCode for a venue we need to register them
		if (secretCode != null && secretCode.length() > 0 && secretCode.length() < 50) {

			Object[] keys = {Venue.SECRET_CODE_KEY};
			Object[] values = {secretCode};

			NSDictionary<Object, Object> bindings = new NSDictionary<Object, Object>(values, keys);

			Venue venue = null;
			try {
				venue = (Venue)(EOUtilities.objectMatchingValues(ec, Venue.ENTITY_NAME, bindings));
			} catch (EOUtilities.MoreThanOneException e1) {
				log.error("More then one venue matches " + secretCode);
				return pageWithName(Main.class);
			} catch (EOObjectNotAvailableException e2) {
				log.error("Could not find a venue matching " + secretCode);
				return pageWithName(Main.class);
			} catch (RuntimeException e3) {
				log.error("Problem with the database looking for secretCode: " + secretCode);
				return pageWithName(Main.class);
			}

			if (venue != null) {
				session().setTimeOut(3600);
				((Session)session()).setAttendee(null);
				AttendeeRegistrationPage nextPage = pageWithName(AttendeeRegistrationPage.class);
				nextPage.setVenueForPage(venue);
				return nextPage; 
			}
		}

		Main nextPage = (Main)pageWithName(Main.class);
		nextPage.setMessageOnScreen("Please enter login information or a conference code.");
		return nextPage.generateResponse();
	}

	public WOActionResults iCalendarAction() {
		EOEditingContext ec = ERXEC.newEditingContext();
		NSMutableArray<CalendarEvent> calendarEventList = new NSMutableArray<CalendarEvent>();

		String secretCode = request().stringFormValueForKey(DirectAction.CONFID_KEY);
		String attendeeEmail = request().stringFormValueForKey(DirectAction.ATTENDEE_KEY);

		// Get attendee and venue from query strings:
		Attendee attendee = Attendee.fetchAttendee(ec, Attendee.USER_EMAIL_ADDRESS_KEY, attendeeEmail);
		Venue venue = Venue.fetchVenue(ec, Venue.SECRET_CODE_KEY, secretCode);
		//NSArray<VEvent> eventList = venue.events();

		calendarEventList.add(
				new CalendarEvent(
						attendee.travelInformationForVenue(venue).arrivalDate(),
						null,
						"Arriving for " + venue.lable(),
						"ARRIVAL-"+attendee.travelInformationForVenue(venue).uniqueID()+"-"+venue.secretCode()+"@dyned.com",
						true
				)
		);

//		calendarEventList.add(
//				new CalendarEvent(
//						attendee.travelInformationForVenue(venue).departureDate(),
//						null,
//						"Leaving " + venue.lable(),
//						"DEPARTURE-"+attendee.travelInformationForVenue(venue).uniqueID()+"-"+venue.secretCode()+"@dyned.com",
//						true
//				)
//		);
//		
//		for (int i = 0; i < eventList.count(); i++) {
//			VEvent eventInList = eventList.objectAtIndex(i);
//			calendarEventList.add(
//					new CalendarEvent(
//							eventInList.dateTimeStart(),
//							eventInList.dateTimeEnd(),
//							eventInList.lable(),
//							eventInList.uniqueID()+"-"+venue.secretCode()+"@dyned.com",
//							false
//					)
//			);			
//		}
		
		// Build the ics:
		ERPublishCalendarPage icalendar = new ERPublishCalendarPage(request().context());
		icalendar.setCalendarName(venue.lable());
		icalendar.setCalendarTimeZone(NSTimeZone.getDefault().getDisplayName());
		icalendar.addEventsFromArray(calendarEventList);
		
		return icalendar;
	}
	
}
