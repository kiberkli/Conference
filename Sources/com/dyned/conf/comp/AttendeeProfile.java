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

import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.AttendeeFunction;
import com.dyned.conf.eom.SpecialNeed;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXApplication;
import er.extensions.components.ERXComponent;

public class AttendeeProfile extends CompCommon {
	
	private static Logger log = Logger.getLogger(AttendeeProfile.class);
			
	public Attendee attendee;
	public ERXComponent returnToPage;
	
	public NSArray<String> saluationLists;
	public String saluationItem;
	
	public AttendeeFunction attendeeFunctionItem;
	public NSArray<AttendeeFunction> attendeeFunctionList;

	public String userPassword;
	public String userPasswordVerified;
	private String userEmailaddress;
	
	public NSArray<SpecialNeed> specialNeedList;
	public SpecialNeed specialNeedInList;

	public String messageOnScreen;
	
	
    public AttendeeProfile(WOContext context) {
        super(context);
        
        saluationLists = Attendee.salutations; //new NSArray<String>("Mr.", "Mrs.", "Ms", "Dr.");
        saluationItem = null;
        
        attendeeFunctionList = fullAttendeeFunctionList();
        specialNeedList = fullSpecialNeedList();
        
        messageOnScreen = new String("");
    	
    }

    public void setReturnToPage(Object aValue) {
    	returnToPage = (ERXComponent)aValue;
    }
    
    public void setAttendee(Object aValue) {
    	attendee = (Attendee)aValue;
    }
    
    public void setUserEmailaddress(Object aValue) {
    	userEmailaddress = (String)aValue;
    	log.info("Set form email to " + userEmailaddress);
    }
    
    public String getUserEmailaddress() {
    	userEmailaddress = attendee.userEmailAddress();
    	return new String(attendee.userEmailAddress());
    }
    
    public ERXComponent cancelAttendeeProfile() {
    	ec.revert();
		return returnToPage;
    }
    
    public ERXComponent saveAttendeeProfile() {
    	if (
    			(userPassword != null && userPasswordVerified != null) &&
    			(userPassword.length() > 0 && userPassword.equals(userPasswordVerified))
    	) {
    		attendee.setUserPassword(userPassword);
    		log.info("Password is long and are equal.");
    	} else if (
    			(userPassword != null && userPasswordVerified != null) &&
    			(! userPassword.equals(userPasswordVerified))
    	) {
			messageOnScreen = "The passwords are not identical, please confirm you password.";
			log.info("Passwords are not the same.");
			return null;
    	}

    	log.info("Form email:     " + userEmailaddress);
    	log.info("Attendee email: " + attendee.userEmailAddress());
    	
    	if (!userEmailaddress.equals(attendee.userEmailAddress()) && emailAddressExistInAttendee(userEmailaddress)) {
    		messageOnScreen = "The address " + userEmailaddress + " is already registered.";
    		ec.revert();
			return null; //(ERXComponent)pageWithName(this.componentName());
    	} else 
    		attendee.setUserEmailAddress(userEmailaddress);

    	attendee.setDateLastEdited(new NSTimestamp());
    	
		messageOnScreen = "";
    	if (saveMyChanges("Failed to save updated profile for attendee "+attendee.nameFamily())) {
    		if (returnToPage == null)
    			return (AttendeeHomePage)pageWithName(AttendeeHomePage.class);
    		else
    			return returnToPage;
    	} else {
    		messageOnScreen = "Unable to save your information, please try again later.";
    		return null;
    	}
    }

}