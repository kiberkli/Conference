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
import com.dyned.conf.eom.Invitee;
import com.dyned.conf.eom.Venue;

import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.*;

public class VenueInvitationPage extends CompCommon {

	private static Logger log = Logger.getLogger(VenueInvitationPage.class);
	
	public Admin administrator;
	public String messageOnPage;

	public NSMutableArray<Invitee> inviteeList;
	public Invitee inviteeInlist;

	public String inviteeFreeFormName;
	public String inviteeEmailAddress;

	public String invitationText;
	public String invitationSubject;

	public NSArray<Venue> venueList;
	public Venue venueSelected;
	public Venue venueInList;

    public VenueInvitationPage(WOContext context) {
        super(context);
        ec = ((Session)session()).defaultEditingContext();
        administrator = ((Session)session()).administrator;
        
        messageOnPage = new String();
        inviteeList = new NSMutableArray<Invitee>();
        
        inviteeFreeFormName = new String();
        inviteeEmailAddress = new String();
        
        invitationText = new String();
        invitationSubject = new String();
        
        venueList = fullVenuList();
    }


    public VenueInvitationPage addInviteeToList() {
    	if (
    			(inviteeEmailAddress != null) && 
    			(inviteeEmailAddress.length() > 0)
    			) {
    		Invitee newInvitee = (Invitee)(EOUtilities.createAndInsertInstance(ec, Invitee.ENTITY_NAME));
    		newInvitee.setAdminRelationship(administrator);
    		newInvitee.setEmailAddress(inviteeEmailAddress);
    		newInvitee.setFreeFormName(inviteeFreeFormName);
    		newInvitee.setVenueRelationship(venueSelected);
    		
    		inviteeList.addObject(newInvitee);
    		
    		inviteeEmailAddress = "";
    		inviteeFreeFormName = "";
    	} else
    		messageOnPage = "E-Mail address is required.";
    	return null;
    }
    
    public VenueInvitationPage deleteInviteeFromList() {
   		inviteeList.remove(inviteeInlist);
   		ec.deleteObject(inviteeInlist);
    	return null;
    }

    public VenueInvitationPage sendInvitation() {
    	if (
    			(inviteeList != null) && (inviteeList.count() > 0) &&
    			(invitationText != null) && (invitationText.length() > 0) &&
    			(venueSelected != null)
    	) {
    		
    	} else {
    		messageOnPage = "Insufficient information to send invitation. Required values are list of invitees, invitation text and the venue.";
    	}
    	
    	//TODO Write code to send invite email messages.
    	
    	return null;
    }
    
    public AdminsHomePage cancelInvite() {
    	ec.revert();
    	return pageWithName(AdminsHomePage.class);
    }
    
}





/*
@SuppressWarnings("unchecked")
public NSArray<Venue> venues() {
	NSArray<Venue> results = null;
	
	EOQualifier eoQualifier = null; // Use when using a format string.
	NSMutableArray<EOSortOrdering> sortOrderings = new NSMutableArray<EOSortOrdering>();

	// Add sort options here:
	EOSortOrdering sortByDate = new EOSortOrdering(Venue.DATE_START_KEY, EOSortOrdering.CompareAscending);
	sortOrderings.addObject(sortByDate);

	// Create the fetch spec:
	EOFetchSpecification theFetchSpecs = new EOFetchSpecification(Venue.ENTITY_NAME, eoQualifier, sortOrderings);

	try {
		results = ec.objectsWithFetchSpecification(theFetchSpecs);
	} catch (Exception ex) {
		log.error("Error fetching Venue list:");
		log.error(ex.getMessage());
	}

	return results;
}
*/
