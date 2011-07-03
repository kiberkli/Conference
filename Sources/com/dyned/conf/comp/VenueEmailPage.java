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

import com.dyned.conf.EMailUtility;
import com.dyned.conf.Session;
import com.dyned.conf.eom.Admin;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import er.extensions.appserver.ERXApplication;

public class VenueEmailPage extends CompCommon {
	
	private static Logger log = Logger.getLogger(VenueEmailPage.class);
			
	public Admin administrator;

	public NSArray<Venue> venueList;
	public Venue venueInList;
	public Venue venueSelected;
	
	public String messageOnScreen;
	
	public String messageText;
	public String messageSubject;

    public VenueEmailPage(WOContext context) {
        super(context);
        ec = ((Session)session()).defaultEditingContext();

        administrator = ((Session)session()).administrator;
        
        venueList = fullVenuList();
        venueInList = null;
        venueSelected = null;
        
        messageText = new String("");
        messageSubject = new String("");
        
        messageOnScreen = new String("");
    }
    
    public void setVenueForPage(Venue aValue) {
    	venueSelected = aValue;
    }
    
    public AdminsHomePage cancelEmail() {
    	ec.revert();
    	return pageWithName(AdminsHomePage.class);
    }
    
    public VenueEmailResultsPage sendEmail() {
    	
    	if (venueSelected == null) {
    		messageOnScreen = "Please select a venue";
    		return null;
    	}
    	
    	NSMutableArray<String> errorsForMessages = new NSMutableArray<String>();
    	NSArray<Attendee> attendeeList = venueSelected.attendees();
    	
    	messageText = messageText.replaceAll("\\r\\n", "<br>\r\n");
    	int messageCount = 0;
    	
		VenueEmailMessage messageBody = (VenueEmailMessage)pageWithName(VenueEmailMessage.class.getName());
		messageBody.setVenueForPage(venueSelected);
		messageBody.setMessageText(messageText);
		messageBody.setMessageSubject(messageSubject);

		for (int attendeeIndex = 0; attendeeIndex < attendeeList.count(); attendeeIndex++) {
			Attendee attendeeInList = attendeeList.objectAtIndex(attendeeIndex);
			try {
				String errorString = EMailUtility.composeAndSendComponentMail(
						messageBody,
						venueSelected.admin().emailAddress(),
						venueSelected.admin().fullName(),
						attendeeInList.userEmailAddress(),
						attendeeInList.nameGiven() + " " + attendeeInList.nameFamily(),
						null,
						venueSelected.admin().emailAddress(),
						messageSubject
				);
				if (errorString == null)
					messageCount++;
				else {
					errorsForMessages.add(attendeeInList.userEmailAddress());
				}
			} catch (RuntimeException ex) {
				log.error("Error sending email to " + attendeeInList.userEmailAddress());
				log.error(ex.getMessage());
			}
		}

		VenueEmailResultsPage venueEmailResultsPage = (VenueEmailResultsPage)pageWithName(VenueEmailResultsPage.class);
    	venueEmailResultsPage.setErrorsForMessages(errorsForMessages);
    	venueEmailResultsPage.setMessagesSent(messageCount);
    	venueEmailResultsPage.setVenueForPage(venueSelected);
    	return venueEmailResultsPage;
    }
    
    public String venueInListPopupDisplayString() {
    	return venueInList.lable() + " (" + venueInList.attendees().count() + " Recipients - sent from " + venueInList.admin().fullName() + ")";
    }
    
}