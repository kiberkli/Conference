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
import com.dyned.conf.eom.Admin;
import com.dyned.conf.eom.Attendee;
import com.webobjects.appserver.WOContext;

import er.extensions.appserver.ERXApplication;
import er.extensions.components.ERXComponent;

import com.webobjects.eoaccess.EOObjectNotAvailableException;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSDictionary;

public class AdminsLogin extends ERXComponent {
	
	public EOEditingContext ec;
	
	public String username;
	public String password;
	
	public String messsageOnPage;
	
	public String attendeeEmail;
	
    public AdminsLogin(WOContext context) {
        super(context);
        
        ec = ((Session)session()).defaultEditingContext();

        username = new String();
        password = new String();
        
        messsageOnPage = new String();
        
        attendeeEmail = null;
    }

    // Use this only when planning to jump to the view attendee profile page.
    public void setAttendeeEmail(String aValue) {
    	attendeeEmail = aValue;
    }
    
    public ERXComponent loginAdministrator() {

    	if ( (username == null) || (username.length() == 0) || (password == null) || (password.length() == 0) ) {
    		messsageOnPage = "You must enter your username and password.";
    		return null;
    	} else {
    		Admin administrator = null;
    		Object[] keys = {Admin.USERNAME_KEY, Admin.PASSWORD_KEY};
    		Object[] values = {username, password};
    		NSDictionary<Object, Object> bindings = new NSDictionary<Object, Object>(values, keys);

    		try {
    			administrator = (Admin)EOUtilities.objectMatchingValues(ec,Admin.ENTITY_NAME, bindings);
    		} catch (EOUtilities.MoreThanOneException e1) {
    			messsageOnPage = "Bad login, please try again:";
    			ERXApplication.log.error("Message to user: " + messsageOnPage);
    			ERXApplication.log.error(e1.getMessage());
    			return null;
    		} catch (EOObjectNotAvailableException e2) {
    			messsageOnPage = "Invalid Login, please try again:";
    			ERXApplication.log.error("Message to user: " + messsageOnPage);
    			ERXApplication.log.error(e2.getMessage());
    			return null;
    		} catch (RuntimeException e3) {
    			messsageOnPage = "Unable to log you in at this time.";
    			ERXApplication.log.error("Message to user: " + messsageOnPage);
    			ERXApplication.log.error(e3.getMessage());
    			return null;
    		}
    		
    		if (administrator != null) {
    			((Session)session()).setAdministrator(administrator);
    			((Session)session()).setTimeOut(3600);

    			// The attendee email is here from a link when requesting an invite letter for visa request.
    			if (attendeeEmail != null) {
    				Attendee attendee = Attendee.fetchAttendee(ec, Attendee.USER_EMAIL_ADDRESS_KEY, attendeeEmail);
    				if (attendee != null) {
    					AdminsAttendeeViewPage nextPage = (AdminsAttendeeViewPage)pageWithName(AdminsAttendeeViewPage.class);
    					nextPage.setAttendeeForPage(attendee);
    					return nextPage;
    				} else {
    					messsageOnPage = "Could not find Attendee.";
    					return null;
    				}
    			} else {
    				AdminsHomePage adminsHomePage = (AdminsHomePage)(pageWithName(AdminsHomePage.class));
    				return adminsHomePage;
    			}
    		} else {
    			messsageOnPage = "Login Failed, please try again:";
    			ERXApplication.log.error("Something bad happened. No administrator found with " +
    					"username = " + username +
    					"and password = " + password + 
    					". Unable to catch this with an execption.");
    					
    			return null;
    		}
    	}
    }
    
    public  AdminsLogin emailPasswordToAdministrator() {
    	
    	// http://wiki.objectstyle.org/confluence/display/WO/Web+Applications-Development-Sending+Emails
    	// TODO Write code to send password to an admin.
    	
    	messsageOnPage = "Not supported at this time.";
    	return null;
    }
}