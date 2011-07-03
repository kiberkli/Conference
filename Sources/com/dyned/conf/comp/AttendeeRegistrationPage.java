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
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXApplication;

public class AttendeeRegistrationPage extends CompCommon {
	
	private static Logger log = Logger.getLogger(AttendeeRegistrationPage.class);
	
	public Venue venue;

	public NSArray<String> saluationLists;
	public String saluationItem;

	// Form data
	public String salutation;
	public String nameFamily;
	public String nameGiven;
	
	public String userEMailAddress;
	public String userPassword;
	public String userPasswordVerified;
	
// Previously not on this page:
	public String jobTitle;
	
	public String companyName;
	public String addressStreet1;
	public String addressStreet2;
	public String addressCity;
	public String addressState;
	public String addressPostalCode;
	public String addressCountry;
	
	public String fax;
	public String phone;
	public String specialNeedsNotes;
	
	public String messageOnScreen;
	
    public AttendeeRegistrationPage(WOContext context) {
        super(context);

        saluationLists = Attendee.salutations; //new NSArray<String>("Mr.", "Mrs.", "Ms", "Dr.");
        saluationItem = null;

        messageOnScreen = new String();
    }
    
    public void setVenueForPage(Venue aValue) {
    	venue = aValue;
    }
    
//    public AttendeeRegistrationPage2 submitForm() {
//    public ConferencePage submitForm() {
    public CompCommon submitForm() {
    	if (
    			(userPassword == null || userPassword.length() == 0 || !userPassword.equals(userPasswordVerified))	
    	) {
    		messageOnScreen = "The passwords do not match";
    		return null;
    	} else if (
    			(nameGiven != null && nameGiven.length() > 0) &&
    			(userEMailAddress != null && userEMailAddress.length() >  0)
    	) {
    		if (emailAddressExistInAttendee(userEMailAddress)) {
        		messageOnScreen = "The email address has already been registered.";
    			return null;
    		} 
    		Attendee attendee = (Attendee)(EOUtilities.createAndInsertInstance(ec, Attendee.ENTITY_NAME));
    		attendee.setSalutation(salutation);
    		attendee.setNameFamily(nameFamily);
    		attendee.setNameGiven(nameGiven);
    		attendee.setUserEmailAddress(userEMailAddress);
    		attendee.setUserPassword(userPassword);
    		attendee.addToVenuesRelationship(venue);

    		attendee.setCompanyName(companyName);
    		attendee.setAddressStreet1(addressStreet1);
    		attendee.setAddressStreet2(addressStreet2);
    		attendee.setAddressCity(addressCity);
    		attendee.setAddressState(addressState);
    		attendee.setAddressPostalCode(addressPostalCode);
    		attendee.setAddressCountry(addressCountry);
    		attendee.setFax(fax);
    		attendee.setPhone(phone);

    		attendee.setDateRegistered(new NSTimestamp());
    		attendee.setDateLastVisit(new NSTimestamp());
    		attendee.setDateLastEdited(null);

    		if (saveMyChanges("Failed to add new attendee " + nameGiven)) {
    			session().setTimeOut(3600);
    			((Session)session()).setAttendee(attendee);
    			//AttendeeRegistrationPage2 nextPage = (AttendeeRegistrationPage2)pageWithName(AttendeeRegistrationPage2.class);
    			//nextPage.setAttendeeForPage(attendee);
    			//nextPage.setVenueForPage(venue);
    			//nextPage.setAttendeeAndVenueForPage(attendee, venue);
    			
    			// Send welcome message here
    			try {
    				AttendeeRegistrationConfirmationMessage messageBody = (AttendeeRegistrationConfirmationMessage)pageWithName(AttendeeRegistrationConfirmationMessage.class.getName());
    				messageBody.setVenuForPage(venue);
    				EMailUtility.composeAndSendComponentMail(
    						messageBody,
    						venue.admin().emailAddress(),
    						venue.admin().fullName(),
    						attendee.userEmailAddress(),
    						attendee.nameGiven() + " " + attendee.nameFamily(),
    						null,
    						venue.admin().emailAddress(),    					
    						venue.lable() + " Registration Confirmation"
    				);
    			} catch (RuntimeException ex) {
    				log.error("Failed sending welcome message to "+attendee.userEmailAddress());
    				log.error(ex.getMessage());
    			}
    			
    			if (venue.expired()) {
    				log.info("The venue " + venue.lable() + " has expired. Sending " + attendee.nameFamily() + " to home page.");
    				AttendeeHomePage nextPage = ((AttendeeHomePage)pageWithName(AttendeeHomePage.class));
        			return nextPage;
    			} else {
    				ConferencePage nextPage = (ConferencePage)pageWithName(ConferencePage.class);
        			nextPage.setVenueForPage(venue);
        			return nextPage;
    			}
    		} else {
    			messageOnScreen = "We were unable to register you at this time, please try again later.";
    			return null;
    		}
    	}

    	return null;
    }

}