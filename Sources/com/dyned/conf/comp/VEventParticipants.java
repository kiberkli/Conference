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
import com.dyned.conf.eom.VEvent;
import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.ListPageInterface;
import com.webobjects.eocontrol.EOArrayDataSource;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

public class VEventParticipants extends CompCommon {

	public VEvent event;
	
	public VEvent eventInList;
	
	public AttendeeSelectedVEvent attendeeSelectedVEventInList;
	public NSArray<AttendeeSelectedVEvent> attendeeSelectedVEventList;

    public VEventParticipants(WOContext context) {
        super(context);
    }

    public void setVEventForPage(VEvent aValue) {
    	event = aValue;
    	
    	NSArray<EOSortOrdering> sortOrderings = new NSArray<EOSortOrdering>(new EOSortOrdering(
    			AttendeeSelectedVEvent.ATTENDEE_KEY + "." + Attendee.NAME_FAMILY_KEY, EOSortOrdering.CompareAscending)
    	);
    	
    	EOQualifier qualifier = EOQualifier.qualifierWithQualifierFormat(AttendeeSelectedVEvent.PARTICIPANTS_KEY + " > 0", null);

    	attendeeSelectedVEventList = event.attendeeSelectedVEvents(qualifier, sortOrderings, false);
    }

    public VEventParticipants vEventParticipants() {
    	VEventParticipants nextPage  = (VEventParticipants)pageWithName(VEventParticipants.class);
    	nextPage.setVEventForPage(eventInList);
    	return nextPage;
    }

    public VenueEventsStatsPage venueEventsStatsPage() {
    	VenueEventsStatsPage nextPage = (VenueEventsStatsPage)pageWithName(VenueEventsStatsPage.class);
    	nextPage.setVenueForPage(event.venue());
    	return nextPage;
    }
 
    public WOActionResults downloadAsExcelAction() {
    	
    	((Session)session()).excelFileName = event.lable();
    	
    	ListPageInterface lpi = (ListPageInterface)D2W.factory().pageForConfigurationNamed(
    			"ListExcelAttendeeSelectedVEvent", 
    			session()
    	);
    	EOArrayDataSource ds = new EOArrayDataSource(null, ec);
    	ds.setArray(attendeeSelectedVEventList);
    	lpi.setDataSource(ds);
    	lpi.setNextPage(this.context().page());
    	return (WOActionResults)lpi;
    }

}



//EOArrayDataSource ds = new EOArrayDataSource(
//EOEntityClassDescription.classDescriptionForEntityName(
//		AttendeeSelectedVEvent.ENTITY_NAME
//), 
//ec
//);
