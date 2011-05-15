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
import com.dyned.conf.eom.AttendeeSelectedVEvent;
import com.dyned.conf.eom.TravelInformation;
import com.dyned.conf.eom.Venue;
import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.ListPageInterface;
import com.webobjects.eocontrol.EOArrayDataSource;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

public class VenueAttendeesPage extends CompCommon {
	
	public String messageOnScreen;
	public Venue venue;
	
	public NSArray<Attendee> attendeeList;
	public Attendee attendeeInList;
	
	public AttendeeSelectedVEvent attendeeSelectedEventInList;
	
    public VenueAttendeesPage(WOContext context) {
        super(context);
        
        messageOnScreen = new String();
    }
    
    public boolean lateRegistration() {
    	return attendeeInList.dateRegistered().after(venue.dateEnd());
    }
    
    public void setVenueForPage(Venue aValue) {
    	venue = aValue;
    	//attendeeList = venue.attendees();
    	NSMutableArray<EOSortOrdering> sortOrderings = new NSMutableArray<EOSortOrdering>();
    	sortOrderings.add(new EOSortOrdering(Attendee.NAME_FAMILY_KEY, EOSortOrdering.CompareCaseInsensitiveAscending));
    	sortOrderings.add(new EOSortOrdering(Attendee.DATE_REGISTERED_KEY, EOSortOrdering.CompareDescending));
    	attendeeList = venue.attendees(null, sortOrderings.immutableClone());
    }
    
	public NSArray<AttendeeSelectedVEvent> attendeeSelectedEventList() {
		return attendeeInList.selectedEventsForVenue(venue);
	}
	
	public NSTimestamp attendeeInListTravelArrival() {
		try {
			return attendeeInList.travelInformationForVenue(venue).arrivalDate();
		} catch (RuntimeException ex) {
			return null;
		}
	}

	public NSTimestamp attendeeInListTravelDeparture() {
		try {
			return attendeeInList.travelInformationForVenue(venue).departureDate();
		} catch (RuntimeException ex) {
			return null;
		}

	}
	
	public boolean attendeeInListNoPhone() {
		if (attendeeInList.phone() == null)
			return true;
		else
			return false;
	}
	
	public VenueAttendeesPage removeAttendee() {
		// This does not delete the attendee as the attendee may be registered for other venues
		venue.remove(attendeeInList);
		if (saveMyChanges("Failed to remove attendee " + attendeeInList.nameFamily() + " from venue " + venue.lable())) {
			VenueAttendeesPage nextPage = pageWithName(VenueAttendeesPage.class);
			nextPage.setVenueForPage(venue);
			return nextPage;
		} else {
			messageOnScreen = "Unable to un-register " + attendeeInList.fullName() + ". Try again later.";
			return null;
		}
	}
	
	public WOActionResults downloadAllAttenseesAsExcel() {
	
    	((Session)session()).excelFileName = venue.lable();
    	
		NSMutableArray<EOSortOrdering> sortOrderings = new NSMutableArray<EOSortOrdering>();
		sortOrderings.add(EOSortOrdering.sortOrderingWithKey(Attendee.NAME_FAMILY_KEY, EOSortOrdering.CompareAscending));
		NSArray<Attendee> dsArray = EOSortOrdering.sortedArrayUsingKeyOrderArray(attendeeList, sortOrderings.immutableClone());

    	ListPageInterface lpi = (ListPageInterface)D2W.factory().pageForConfigurationNamed(
    			"ListExcelAttendee", 
    			session()
    	);
    	EOArrayDataSource ds = new EOArrayDataSource(null, ec);
    	ds.setArray(dsArray);
    	lpi.setDataSource(ds);
    	lpi.setNextPage(this.context().page());
    	return (WOActionResults)lpi;
	}
	
	public WOActionResults downloadArrivalsAsExcel() {

		NSArray<TravelInformation> travelInformationList = TravelInformation.fetchTravelInformationsForVenue(venue);

    	((Session)session()).excelFileName = venue.lable() + " Arrivals";

    	ListPageInterface lpi = (ListPageInterface)D2W.factory().pageForConfigurationNamed(
    			"ListExcelArrivalTravelInformation", 
    			session()
    	);
    	EOArrayDataSource ds = new EOArrayDataSource(null, ec);
    	ds.setArray(travelInformationList);
    	lpi.setDataSource(ds);
    	lpi.setNextPage(this.context().page());
    	return (WOActionResults)lpi;
	}

	public WOActionResults downloadDeparturesAsExcel() {

		NSArray<TravelInformation> travelInformationList = TravelInformation.fetchTravelInformationsForVenue(venue);

    	((Session)session()).excelFileName = venue.lable() + " Departures";

    	ListPageInterface lpi = (ListPageInterface)D2W.factory().pageForConfigurationNamed(
    			"ListExcelDepartureTravelInformation", 
    			session()
    	);
    	EOArrayDataSource ds = new EOArrayDataSource(null, ec);
    	ds.setArray(travelInformationList);
    	lpi.setDataSource(ds);
    	lpi.setNextPage(this.context().page());
    	return (WOActionResults)lpi;
	}
	
	public AdminsAttendeeProfilePage attendeeProfilePage() {
		AdminsAttendeeProfilePage nextPage = (AdminsAttendeeProfilePage)pageWithName(AdminsAttendeeProfilePage.class);
		nextPage.setAttendee(attendeeInList);
		nextPage.setVenue(venue);
		nextPage.setReturnToPage(this);
		return nextPage;
	}
}