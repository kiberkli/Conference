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
import com.dyned.conf.eom.Venue;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

public class AdminsHomePage extends CompCommon {

	public Admin administrator;

	public NSArray<Venue> venueList;
	public Venue venueInList;
	
	public AdminsHomePage(WOContext context) {
        super(context);
        ec = ((Session)session()).defaultEditingContext();

        administrator = ((Session)session()).administrator;
        
        venueList = fullVenuList();
        venueInList = null;
        
    }
	
	public VenueInvitationPage sendAttendeeInvite() {
		return pageWithName(VenueInvitationPage.class);
	}
	
	public VenuePage venuePage() {
		return pageWithName(VenuePage.class);
	}
	
	public VenueEventsPage venueEventsPage() {
		VenueEventsPage nextPage = pageWithName(VenueEventsPage.class);
		nextPage.setVenueForPage(venueInList);
		return nextPage;
	}
	
	public VenueEmailPage venueEmailPage() {
		return pageWithName(VenueEmailPage.class);
	}
	
	public VenueAttendeesPage venueAttendeesPage() {
		VenueAttendeesPage nextPage = (VenueAttendeesPage)pageWithName(VenueAttendeesPage.class);
		nextPage.setVenueForPage(venueInList);
		return nextPage;
	}
	
	public VenueEventsStatsPage venueEventsStatsPage() {
		VenueEventsStatsPage nextPage = (VenueEventsStatsPage)pageWithName(VenueEventsStatsPage.class);
		nextPage.setVenueForPage(venueInList);
		return nextPage;
	}
	
	public AdminsPage adminsPage() {
		AdminsPage nextPage = (AdminsPage)pageWithName(AdminsPage.class);
		return nextPage;
	}
	
}