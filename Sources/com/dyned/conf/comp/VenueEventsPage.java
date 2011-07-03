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

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.dyned.conf.Session;
import com.dyned.conf.TimestampUtilities;
import com.dyned.conf.eom.Admin;
import com.dyned.conf.eom.VEvent;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.foundation.*;

import er.extensions.appserver.ERXApplication;

public class VenueEventsPage extends CompCommon {

	private static Logger log = Logger.getLogger(VenueEventsPage.class);

	public Admin administrator;
	
	public Venue venue;

	public String messageOnPage;
	public String currentTimezone;

	public NSArray<NSTimestamp> venueDatesList;
	public NSTimestamp venueDatesListItem;
	public NSTimestamp selectedVenueDate;
	
	public VEvent eventInList;
	public VEvent event;
		
	public NSTimestamp dateTimeStart;
	public NSTimestamp dateTimeEnd;
	public String description;
	public String lable;
	public String meetingPlace;
	
	public NSArray<String> colorList;
	public String colorItemInList;
	public String selectedColor;

    public VenueEventsPage(WOContext context) {
        super(context);
        
        administrator = ((Session)session()).administrator;

        messageOnPage = new String("");
        currentTimezone = NSTimeZone.getDefault().getDisplayName();

        venue = null;

        eventInList = null;
        
        venueDatesList = null;
        venueDatesListItem = null;
        selectedVenueDate = null;
        
        colorList = new NSArray<String>("#ffffff","#2F64DA","#37B11B","#E71E27","#F38B1E","#BE33BB","#6138AF");
        selectedColor = "#ffffff";

    }
    
    public void setVenueForPage(Venue aValue) {
    	venue = aValue;
        venueDatesList = venueDays(venue);
        dateTimeStart = new NSTimestamp(0, venue.dateStart());
        dateTimeEnd =  new NSTimestamp(0, venue.dateEnd());
    }
    
    public VenueEventsPage addEventToList() {
    	if (venue != null) {
    		if (dateTimeEnd.before(dateTimeStart)) {
    	    	messageOnPage = "The event end time is before the start time";
    			return null;
    		}
    		if (event == null) {
    			event = (VEvent)(EOUtilities.createAndInsertInstance(ec, VEvent.ENTITY_NAME));
    			venue.addToEventsRelationship(event);
    		}
    		
    		log.info("selectedVenueDate: " + selectedVenueDate);
    		log.info("dateTimeStart:     " + dateTimeStart);
    		log.info("dateTimeEnd:       " + dateTimeEnd);

    		try {
    			event.setDateTimeStart(TimestampUtilities.timestampByAddingTime(selectedVenueDate, dateTimeStart));
    			event.setDateTimeEnd(TimestampUtilities.timestampByAddingTime(selectedVenueDate, dateTimeEnd));
    		} catch (RuntimeException ex) {
    			messageOnPage = "Unable to read the start or end time for the event.";
    			log.error("Badly formmated dates:");
    			log.error("   dateTimeStart: " + dateTimeStart);
    			log.error("   dateTimeEnd:   " + dateTimeEnd);
    			log.error(ex.getMessage());

    			return null;
    		}
    		event.setDescription(description);
    		event.setLable(lable);
    		event.setMeetingPlace(meetingPlace);
    		event.setColor(selectedColor);
    		
    		VenueEventsPage nextPage = pageWithName(VenueEventsPage.class);
    		nextPage.setVenueForPage(venue);
    		return nextPage;
    	}
    	messageOnPage = "There is no venue for this page";
    	log.error("Page " + VenueEventsPage.class.getName() + " requires a venue.");
    		
    	return null;
    }
    
    public VenueEventsPage deleteEventFromList() {
   		venue.removeFromEventsRelationship(eventInList);
   		ec.deleteObject((EOEnterpriseObject)eventInList);
   		return null;
    }
    
    public VenueEventsPage editEventInList() {
    	selectedVenueDate = new NSTimestamp(
    			eventInList.dateTimeStart().yearOfCommonEra(), 
    			eventInList.dateTimeStart().monthOfYear(), 
    			eventInList.dateTimeStart().dayOfMonth(), 
    			0, 0, 0, NSTimeZone.getDefault());
    	
    	dateTimeStart = eventInList.dateTimeStart();
    	dateTimeEnd = eventInList.dateTimeEnd();

    	lable = eventInList.lable();
    	description = eventInList.description();
    	meetingPlace = eventInList.meetingPlace();
    	
    	selectedColor = eventInList.color();

    	event = eventInList;
    	
    	return null;
    }
    
    public VenuePage saveAllEvents() {
    	if (saveMyChanges("Failed to save new events to venu "+venue.lable())) {
    		VenuePage nextPage = pageWithName(VenuePage.class);
        	return nextPage;
    	} else {
			messageOnPage = "There was a problem with the database. Please try again later.";
    		return null;
    	}
    }
    
    public VenuePage cancelAllEvents() {
    	ec.revert();
    	return pageWithName(VenuePage.class);
    }
    
    private NSArray<NSTimestamp> venueDays(Venue venue) {
    	NSMutableArray<NSTimestamp> results = new NSMutableArray<NSTimestamp>();
    	if (venue != null && venue.dateStart() != null && venue.dateEnd() != null) {

    		NSTimestamp dateStart = new NSTimestamp(venue.dateStart());
    		NSTimestamp dateEnd = new NSTimestamp(venue.dateEnd());
    		
    		int dayToAdd = 0;
    		while (
    				(results.lastObject() == null) || 
    				(! results.lastObject().after(dateEnd))
    			) {
    			GregorianCalendar myCalendar = new GregorianCalendar();
    			myCalendar.setTime(dateStart);
    			myCalendar.add(GregorianCalendar.DAY_OF_MONTH, dayToAdd);

    			int year = myCalendar.get(GregorianCalendar.YEAR);
    			int month = myCalendar.get(GregorianCalendar.MONTH);
    			int day = myCalendar.get(GregorianCalendar.DAY_OF_MONTH);    			
    			
    			NSTimestamp nextDay = new NSTimestamp(year, month+1, day, 0, 0, 0, NSTimeZone.getDefault());

    			results.addObject(nextDay);
    			dayToAdd = dayToAdd + 1;
    			if (dayToAdd > 30)
    				break;
    		}
    		//results.addObject(dateEnd);

    	} else {
    		log.error("No start and end date for this venue: " + venue.lable());
    	}
    	return results.immutableClone();
    }
    
    public String venueDatesListItemDisplay() {
    	NSTimestampFormatter formatter = new NSTimestampFormatter("%a %m/%d");
    	if (venueDatesListItem != null)
    		return formatter.format(venueDatesListItem);
    	else
    		return "(unknown)";
    }
        
    public String EventSubmitButtonValue() {
    	if (event == null)
    		return "Add to List";
    	else
    		return "Update";
    }
}