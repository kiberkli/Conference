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

import com.dyned.conf.Session;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORedirect;
import com.webobjects.foundation.NSDictionary;

public class AttendeeRegistrationComplete extends CompCommon {
	
	public Venue venue;
	public Attendee attendee;

    public AttendeeRegistrationComplete(WOContext context) {
        super(context);
		attendee = ((Session)session()).attendee;
    }
    
	public void setVenueForPage(Venue venueValue){
		venue = venueValue;
	}
	
	@SuppressWarnings("unchecked")
	public WORedirect logout() {
		session().terminate();

		WORedirect adminsLogin = (WORedirect) pageWithName("WORedirect");
		adminsLogin.setUrl(context().directActionURLForActionNamed(
				"default", 
				new NSDictionary(Boolean.FALSE, "wosid")));

		return adminsLogin;
	}

	public AttendeeHomePage attendeeHomePage() {
		return (AttendeeHomePage)pageWithName(AttendeeHomePage.class);
	}
	
	public ConferencePage conferencePage() {
		ConferencePage nextPage = (ConferencePage)pageWithName(ConferencePage.class);
		nextPage.setVenueForPage(venue);
		return nextPage;
	}
	
	public AttendeeProfilePage attendeeProfilePage() {
		return (AttendeeProfilePage)pageWithName(AttendeeProfilePage.class);
	}
}