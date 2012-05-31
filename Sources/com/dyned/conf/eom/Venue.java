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

package com.dyned.conf.eom;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TimeZone;

import com.ibm.icu.util.GregorianCalendar;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimeZone;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.foundation.ERXTimestampUtilities;

public class Venue extends _Venue {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Venue.class);
	
	public static Venue withSecretCode(EOEditingContext ec, String secretCode) {
		if (ec != null && secretCode != null)
			return Venue.fetchVenue(ec, Venue.SECRET_CODE_KEY, secretCode);
		else
			return null;
	}
	
	public String documentsURLViaJavascript() {
		return "window.location = '" + this.documentsURLFull() + "'";
	}
	
	public boolean documentsURLEmpty() {
		return (this.documentsURL() == null || this.documentsURL().length() == 0);
	}
	
	public String documentsURLFull() {
		
		// WARNING: using a username and password in a url may lead to phishing warnings on the client.
		
		if (this.documentsURL() == null || this.documentsURL().length() == 0)
			return "#";
		
		String protocolString = this.documentsURL().substring(
				0,   
				this.documentsURL().indexOf("://")+3
		);
		String hostPathString = this.documentsURL().substring(protocolString.length());
		StringBuffer fullURL = new StringBuffer(protocolString);

		if (this.documentsURLUsername() != null && this.documentsURLUsername().length() > 0) {
			fullURL.append(this.documentsURLUsername() + ":");
			if (this.documentsURLPassword() != null && this.documentsURLPassword().length() > 0) 
				fullURL.append(this.documentsURLPassword() + "@");
		}
		fullURL.append(hostPathString);
		return fullURL.toString();
	}
	
	public boolean expired() {
		return this.dateEnd().before(new NSTimestamp());
	}

	public void remove(Attendee attendee) {
		this.removeFromAttendeesRelationship(attendee);
		attendee.deleteHotelInformationForVenu(this);
		attendee.deleteSelectedEventsForVenue(this);
		attendee.deleteTravelInformationForVenu(this);
	}
	
	public String facilityPhoneURL() {
		if (this.facilityPhone() != null)
			return "tel:"+this.facilityPhone().replaceAll("[^0-9]", "");
		else
			return "#";
	}
	
	public String descriptionForHTML() {
		if (this.description() != null)
			return this.description().replaceAll("\\r\\n", "<br>");
		else
			return null;
	}
	
	public String mapAddressForHTML() {
		if (this.mapAddress() != null) 
			return this.mapAddress().replaceAll("\\r\\n", "<br>");
		else
			return null;
	}
	
	public String googleAddressSearchURLEmbedded() {
		return googleAddressSearchURL() + "&iwloc=&output=embed";
	}

	public String googleAddressSearchURL() {
		String urlEncoded = null;
		try { 
			urlEncoded = URLEncoder.encode(this.mapAddress().replaceAll("\\r\\n", " "), "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			urlEncoded = this.mapAddress().replaceAll("\\r\\n", " ");
		}
		return "http://maps.google.com/maps?q=" + urlEncoded;
	}

	public NSArray<VEvent> eventsSorted() {
		NSMutableArray<EOSortOrdering> sortOrderings = new NSMutableArray<EOSortOrdering>();
		
		//sortOrderings.add(EOSortOrdering.sortOrderingWithKey(VEvent.DATE_TIME_START_KEY, EOSortOrdering.CompareAscending));
		sortOrderings.add(EOSortOrdering.sortOrderingWithKey(VEvent.SECTION_DATE_KEY, EOSortOrdering.CompareAscending));
		sortOrderings.add(EOSortOrdering.sortOrderingWithKey(VEvent.START_HOUR_KEY, EOSortOrdering.CompareAscending));
		sortOrderings.add(EOSortOrdering.sortOrderingWithKey(VEvent.START_MINUTE_KEY, EOSortOrdering.CompareAscending));
		sortOrderings.add(EOSortOrdering.sortOrderingWithKey(VEvent.LABLE_KEY, EOSortOrdering.CompareAscending));

		return EOSortOrdering.sortedArrayUsingKeyOrderArray(this.events(), sortOrderings.immutableClone());
	}
	
	public NSTimeZone tz() {
		return NSTimeZone.timeZoneWithName(this.timeZoneInfo().zoneId(), false);
	}
	
//	public TimeZone tz() {
//		return TimeZone.getTimeZone(this.timeZoneInfo().zoneId());		
//	}
}
