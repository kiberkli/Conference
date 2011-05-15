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

import com.dyned.conf.eom.Admin;
import com.dyned.conf.eom.Attendee;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.ERXSession;

public class Session extends ERXSession {
	private static final long serialVersionUID = 1L;
	
	public Admin administrator;
	public Attendee attendee;
	
	public String excelFileName;

	public Session() {
		administrator = null;
		attendee = null;
	}
	
	public void awake() {
		super.awake();
	}
	
	public void setAdministrator(Admin aValue) {
		administrator = aValue;
	}
	
	public void setAttendee(Attendee aValue) {
		attendee = aValue;
	}
	
	public void terminate() {

		if (administrator != null) {
			administrator.setDateLastLogin(new NSTimestamp());
			ERXApplication.log.info("Administrator " + administrator.fullName() + " logged out.");
		}

		if (attendee != null) {
			attendee.setDateLastVisit(new NSTimestamp());
			ERXApplication.log.info("Attendee " + attendee.nameFamily() + " logged out.");
		}

		try {
			defaultEditingContext().saveChanges();
		} catch (RuntimeException ex) {
			ERXApplication.log.error("Unable to save last login date to database while terminating session.");
			ERXApplication.log.error(ex.getMessage());
		}


		super.terminate();
	}

}
