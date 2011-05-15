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

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSLog;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

public class TravelInformation extends _TravelInformation {
	public static final String ID_KEY = "id";

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(TravelInformation.class);
	
	public static NSArray<TravelInformation> fetchTravelInformationsForVenue(Venue aVenue) {
		EOQualifier qualifier = EOQualifier.qualifierToMatchAnyValue(new NSDictionary(aVenue, TravelInformation.VENUE_KEY));

		EOSortOrdering sortArrivalDateOrdering = new EOSortOrdering(
				TravelInformation.ARRIVAL_DATE_KEY, EOSortOrdering.CompareAscending);
		EOSortOrdering sortAttendeeNameOrdering = new EOSortOrdering(
				TravelInformation.ATTENDEE_KEY + "." + Attendee.NAME_FAMILY_KEY, EOSortOrdering.CompareAscending);

		NSMutableArray<EOSortOrdering> sortOrderings = new NSMutableArray<EOSortOrdering>();
		sortOrderings.add(sortArrivalDateOrdering);
		sortOrderings.add(sortAttendeeNameOrdering);

		return TravelInformation.fetchTravelInformations(aVenue.editingContext(), qualifier, sortOrderings.immutableClone());
	}
	
	private String dateToString(NSTimestamp aDate) {
    	NSTimestampFormatter formatter = new NSTimestampFormatter("%m/%d/%y");
    	if (aDate != null)
    		return formatter.format(aDate);
    	else
    		return "(unknown)";
	}
	
	public String arrivalDateToString() {
		return dateToString(arrivalDate());
	}

	public String departureDateToString() {
		return dateToString(departureDate());
	}
	
	public int uniqueID() {
		Integer result = null;
		
		try {
			NSDictionary dict = EOUtilities.primaryKeyForObject(this.editingContext(), this);
			result = (Integer)(dict.objectForKey(ID_KEY));
        } catch (RuntimeException ex) {
        	NSLog.err.appendln(ex.getMessage());
            NSLog.err.appendln("Failed to get primary key for EOGenericRecord, Object not saved yet?.");
		}
		
		return result.intValue();
	}

}
