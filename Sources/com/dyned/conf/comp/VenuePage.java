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
import com.dyned.conf.eom.Venue;

import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.*;

import er.extensions.appserver.ERXApplication;

public class VenuePage extends CompCommon {

	private static Logger log = Logger.getLogger(VenuePage.class);
			
	public Admin administrator;

	public NSArray<Venue> venueList;
	public Venue venueInList;
	public Venue venue;
	
	public String messageOnPage;
	public String promptOnPage;
	
	// Form fields:
//	public String dayStartString;
//	public String dayEndString;
	
	public NSTimestamp dateStart;
	public NSTimestamp dateEnd;
	public String description;
	public String lable;
	public String webpageURL;
	public String facilityName;
	public String facilityPhone;
	public String mapAddress;
	public String secretCode;

	public String documentsURL;
	public String documentsURLUsername;
	public String documentsURLPassword;

	public VenuePage(WOContext context) {
        super(context);
        
        administrator = ((Session)session()).administrator;
        
        venueList = fullVenuList();
        venue = null;
        
        dateStart = new NSTimestamp();
        dateEnd = new NSTimestamp();
        
        description = new String();
        lable = new String();
        webpageURL = new String();
        mapAddress = new String();
        secretCode = new String();

        documentsURL = new String();
        documentsURLUsername = new String();
        documentsURLPassword = new String();
        
        messageOnPage = new String();
        promptOnPage = new String("Add a New Venue");
    }
	
	public void setVenueForPage(Venue aValue) {
		if (aValue != null) {
			dateStart = aValue.dateStart();
			dateEnd = aValue.dateEnd();

			description = aValue.description();
			lable = aValue.lable();
			webpageURL = aValue.webpageURL();
			facilityName = aValue.facilityName();
			facilityPhone = aValue.facilityPhone();
			mapAddress = aValue.mapAddress();
			secretCode = aValue.secretCode();

			documentsURL = aValue.documentsURL();
			documentsURLUsername = aValue.documentsURLUsername();
			documentsURLPassword = aValue.documentsURLPassword();
			
			promptOnPage = "Edit the Selected Venue";
		}
		venue = aValue;
	}
	
	public VenuePage deleteThisVenue() {
		if (venueInList.attendees().count() > 0) {
			venueInList.setInactive(true);
		} else
			ec.deleteObject(venueInList);
		try {
			ec.saveChanges();
		} catch (RuntimeException ex) {
			ec.revert();
			messageOnPage = "There was a problem with the database. Please try again later.";
			log.error(ex.getMessage());
			return null;
		}
		return pageWithName(VenuePage.class);
	}
	
	public VenuePage editSelectedVenue() {
		VenuePage nextPage = pageWithName(VenuePage.class);
		nextPage.setVenueForPage(venueInList);
		return nextPage;
	}
	
	public VenuePage saveFormData() {
		
		if (
				documentsURL != null && 
				documentsURL.length() > 0 
				&& 
				!documentsURL.startsWith("http://")
				) {
			messageOnPage = "The Documents URL is not a proper http URL.";
			return null;
		}
				
		if (
				(mapAddress != null && mapAddress.length() > 0) &&
				(lable != null && lable.length() > 0) &&
				(dateStart != null) && 
				(dateEnd != null)
		) {
			if (venue == null) {
				log.info("Venue appears to be new.");
				venue = (Venue)EOUtilities.createAndInsertInstance(ec, Venue.ENTITY_NAME);
				venue.setDateCreated(new NSTimestamp());
				venue.setAdminRelationship(administrator);
			}
			venue.setDateModified(new NSTimestamp());
			venue.setLable(lable);
			venue.setDateStart(dateStart);
			venue.setDateEnd(dateEnd);
			venue.setWebpageURL(webpageURL);
			venue.setFacilityName(facilityName);
			venue.setFacilityPhone(facilityPhone);
			venue.setMapAddress(mapAddress);
			venue.setDescription(description);
			venue.setSecretCode(secretCode);
			
			venue.setDocumentsURL(documentsURL);
			venue.setDocumentsURLUsername(documentsURLUsername);
			venue.setDocumentsURLPassword(documentsURLPassword);

			if (saveMyChanges("Failed to save new or edited venue to database"))
				return pageWithName(VenuePage.class);
			else
				messageOnPage = "There was a problem with the database. Please try again later.";
				return null;
		} else {
			messageOnPage = "Some values are invalid or missing, a short description is required and check the dates.";
			return null;
		}
	}
	
    public AdminsHomePage cancelVenue() {
    	ec.revert();
    	return pageWithName(AdminsHomePage.class);
    }
    
    public VenueEventsPage venueEventsPage() {
    	VenueEventsPage nextPage = pageWithName(VenueEventsPage.class);
    	nextPage.setVenueForPage(venueInList);
    	return nextPage;
    }
    
    public VenueAttendeesPage venueAttendeesPage() {
    	VenueAttendeesPage nextPage = pageWithName(VenueAttendeesPage.class);
    	nextPage.setVenueForPage(venueInList);
    	return nextPage;
    }
}



// Grave yard

//log.info("administrator: " + administrator.fullName());
//log.info("lable:         " + lable);
//log.info("dateStart:     " + dateStart);
//log.info("dateEnd:       " + dateEnd);
//log.info("webpageURL:    " + webpageURL);
//log.info("mapAddress:    " + mapAddress);
//log.info("description:   " + description);
//log.info("lable:         " + lable);
