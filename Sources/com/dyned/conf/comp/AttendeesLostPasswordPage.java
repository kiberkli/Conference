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

import com.dyned.conf.EMailUtility;
import com.dyned.conf.eom.Attendee;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities.MoreThanOneException;
import er.extensions.appserver.ERXApplication;

public class AttendeesLostPasswordPage extends CompCommon {

	public String messageOnScreen;
	public String attendeeEmail;
	public boolean messageSent;

	public AttendeesLostPasswordPage(WOContext context) {
		super(context);

		messageOnScreen = new String();
		attendeeEmail = new String();
		
		messageSent = false;
	}

	public void setMessageOnScreen(String aString) {
		messageOnScreen = aString;
	}

	public void setMessageSent(boolean aValue) {
		messageSent = aValue;
	}
	
	public AttendeesLostPasswordPage sendLostPassword() {
		if (attendeeEmail != null) {
			
			Attendee attendee = null;
			
			try {
			attendee = Attendee.fetchAttendee(ec, Attendee.USER_EMAIL_ADDRESS_KEY, attendeeEmail);
			} catch (MoreThanOneException ex) {
				ERXApplication.log.error("Too many attendees match " + attendeeEmail);
				ex.printStackTrace();
			} catch (RuntimeException ex) {
				ERXApplication.log.error(ex.getMessage());
				ex.printStackTrace();
			}

			if (attendee != null) {
				try {
					AttendeesLostPasswordMessage  messageBody = (AttendeesLostPasswordMessage)pageWithName(AttendeesLostPasswordMessage.class);
					messageBody.setAttendeeForPage(attendee);
					EMailUtility.composeAndSendComponentMail(
							messageBody,
							"webmaster@dyned.com",
							"DynEd International, Inc.",
							attendee.userEmailAddress(),
							attendee.fullName(),
							null,
							"webmaster@dyned.com",
							"Your Requested Information"
					);
					messageOnScreen = "Your password has been set to your email address with log in information. You may close this window.";
				} catch (RuntimeException ex) {
					ERXApplication.log.error("Failed sending welcome message to " + attendee.userEmailAddress());
					ERXApplication.log.error(ex.getMessage());
					ex.printStackTrace();
					messageOnScreen = "Unable to sent you your password, please try again later.";
				}
			} else {
				messageOnScreen = "Unable to find address.";
			}
		}
		AttendeesLostPasswordPage nextPage = (AttendeesLostPasswordPage)pageWithName(AttendeesLostPasswordPage.class);
		nextPage.setMessageOnScreen(messageOnScreen);
		nextPage.setMessageSent(true);
		return nextPage;
	}
}
