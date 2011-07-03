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
import com.dyned.conf.eom.AttendeeFunction;
import com.dyned.conf.eom.SpecialNeed;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

public class AttendeeRegistrationPage2 extends CompCommon {

	private static Logger log = Logger.getLogger(AttendeeRegistrationPage2.class);
			
	public Venue venue;
	public Attendee attendee;
	
	public NSArray<AttendeeFunction> attendeeFunctionList;
	public AttendeeFunction attendeeFunctionInList;
	public AttendeeFunction attendeeFunctionSelected;
	
	public NSArray<SpecialNeed> specialNeedList;
	//public NSArray<SpecialNeed> specialNeedSelectedList;
	public SpecialNeed specialNeedInList;
	

	// Not on this page:
//	public String salutation;
//	public String nameFamily;
//	public String nameGiven;
//	
//	public String userEMailAddress;
//	public String userPassword;
//	public String userPasswordVerified;
	
	// Form data
	public String jobTitle;
	
	public String companyName;
	public String streetAddress1;
	public String streetAddress2;
	public String addressCity;
	public String addressPostalCode;
	public String addressCountry;
	
	public String fax;
	public String phone;
	public String specialNeedsNotes;
	
	public String messageOnScreen;

	public AttendeeRegistrationPage2(WOContext context) {
        super(context);
        
        messageOnScreen = new String();
        
        attendeeFunctionList = fullAttendeeFunctionList();
        specialNeedList = fullSpecialNeedList();

		attendee = ((Session)session()).attendee;
}
	
//	public void setAttendeeForPage(Attendee aValue) {
//		attendee = aValue;
//	}
	
	public void setVenueForPage(Venue aValue) {
		venue = aValue;
	}
	
	public ConferencePage submitForm() {
		attendee.setDateLastEdited(new NSTimestamp());
		if (saveMyChanges("Failed to update attendee " + attendee.nameFamily())) {
			ConferencePage conferencePage = (ConferencePage)pageWithName(ConferencePage.class);
//			conferencePage.setAttendeeForPage(attendee);
			conferencePage.setVenueForPage(venue);
//			conferencePage.setAttendeeAndVenueForPage(attendee, venue);
			return conferencePage;
		} else {
			messageOnScreen = "We were unable to update your information, please try again later.";
			return null;
		}
	}

//	public CompCommon submitForm() {
//		attendee.setDateLastEdited(new NSTimestamp());
//		if (saveMyChanges("Failed to update attendee " + attendee.nameFamily())) {
//			if (venue.expired()) {
//				log.info("The venue " + venue.lable() + " has expired. Sending " + attendee.nameFamily() + " to home page.");
//				AttendeeHomePage returnPage = ((AttendeeHomePage)pageWithName(AttendeeHomePage.class));
//				return ((CompCommon)returnPage);
//			} else {
//				ConferencePage conferencePage = (ConferencePage)pageWithName(ConferencePage.class);
//				conferencePage.setVenueForPage(venue);
//				return (CompCommon)conferencePage;
//			}
//		} else {
//			messageOnScreen = "We were unable to update your information, please try again later.";
//			return null;
//		}
//	}
}