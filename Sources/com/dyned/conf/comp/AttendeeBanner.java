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

import com.dyned.conf.eom.Attendee;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORedirect;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSDictionary;

import er.extensions.components.ERXComponent;

public class AttendeeBanner extends ERXComponent {
	
	public EOEditingContext ec;

	public Attendee attendee;
	public ERXComponent returnToPage;
	
    public AttendeeBanner(WOContext context) {
        super(context);
//        attendee = ((Session)session()).attendee;
    }
    
    public AttendeeProfilePage attendeeProfilePage() {
    	AttendeeProfilePage nextPage = (AttendeeProfilePage)pageWithName(AttendeeProfilePage.class);
    	nextPage.setReturnToPage(returnToPage);
    	return nextPage;
    }
    
	public AttendeeHomePage attendeeHomePage() {
		return (AttendeeHomePage)pageWithName(AttendeeHomePage.class);
	}
    
	@SuppressWarnings("unchecked")
	public WORedirect logout() {
		session().terminate();

		WORedirect woRedirect = (WORedirect) pageWithName("WORedirect");
		woRedirect.setUrl(context().directActionURLForActionNamed(
				"default", 
				new NSDictionary(Boolean.FALSE, "wosid")));

		return woRedirect;
	}

}