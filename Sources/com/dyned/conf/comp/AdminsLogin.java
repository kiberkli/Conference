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
	
	private static Logger log = Logger.getLogger(AdminsLogin.class);
	
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
    		//Object[] keys = {Admin.USERNAME_KEY, Admin.PASSWORD_KEY};
    		//Object[] values = {username.trim(), password};
    		Object[] keys = {Admin.USERNAME_KEY};
    		Object[] values = {username.trim()};
    		NSDictionary<Object, Object> bindings = new NSDictionary<Object, Object>(values, keys);

    		try {
    			administrator = (Admin)EOUtilities.objectMatchingValues(ec,Admin.ENTITY_NAME, bindings);
    		} catch (EOUtilities.MoreThanOneException e1) {
    			messsageOnPage = "Bad login, please try again:";
    			log.error("Message to user: " + messsageOnPage);
    			log.error(e1.getMessage());
    			e1.printStackTrace();
    			return null;
    		} catch (EOObjectNotAvailableException e2) {
    			messsageOnPage = "Invalid Login, please try again:";
    			log.error("Message to user: " + messsageOnPage);
    			log.error(e2.getMessage());
    			e2.printStackTrace();
    			return null;
    		} catch (RuntimeException e3) {
    			messsageOnPage = "Unable to log you in at this time.";
    			log.error("Message to user: " + messsageOnPage);
    			log.error(e3.getMessage());
    			e3.printStackTrace();
    			return null;
    		}

    		// Fixing the password. Remove the old clear text passwords to the hashcode.
    		if (administrator != null && administrator.pwHashCodeInt() != password.hashCode()) {
    			if (administrator.password() != null && administrator.password().compareTo(password) == 0) {
    				// Log in ok with old password, reset the password to hashcode.
    				administrator.setPassword(administrator.password());
    				try {
    					ec.saveChanges();
    				} catch (RuntimeException e4) {
    					ec.revert();
    					log.error("User logged in but we failed to upgrade password for " + username);
    					log.error(e4.getMessage());
    					e4.printStackTrace();
    				}
    			} else {
    				messsageOnPage = "Invalid Login, please try again.";
    				log.error("User " + username + " doees not have a upgraded password nor a matching old password.");
    				log.error("Message to user: " + messsageOnPage);
    				return null;
    			}
    		}
    		
    		if (administrator != null) {
    			((Session)session()).setAdministrator(administrator);
    			((Session)session()).setTimeOut(3600);

    			log.info("User " + administrator.fullName() + " ("+administrator.username()+") logged in.");

    			// The attendee email is here from a link when requesting an invite letter for visa request.
    			if (attendeeEmail != null) {
    				Attendee attendee = Attendee.fetchAttendee(ec, Attendee.USER_EMAIL_ADDRESS_KEY, attendeeEmail);
    				if (attendee != null) {
    					AdminsAttendeeViewPage nextPage = (AdminsAttendeeViewPage)pageWithName(AdminsAttendeeViewPage.class);
    					nextPage.setAttendeeForPage(attendee);
    					return nextPage;
    				} else {
    					messsageOnPage = "Could not find Administrator.";
    					return null;
    				}
    			} else {
    				AdminsHomePage adminsHomePage = (AdminsHomePage)(pageWithName(AdminsHomePage.class));
    				return adminsHomePage;
    			}
    		} else {
    			messsageOnPage = "Unable to log you in at this time.:";
    			log.error("Something bad happened. No administrator found with " +
    					"username = " + username +
    					" and password = " + password + 
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