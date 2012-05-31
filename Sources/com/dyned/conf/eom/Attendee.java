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

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.log4j.Logger;

import com.dyned.conf.TimestampUtilities;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;

public class Attendee extends _Attendee {
	private static Logger log = Logger.getLogger(Attendee.class);

	public static final NSArray<String> salutations = new NSArray<String>("Mr.", "Mrs.", "Ms.", "Dr.");

	public void setUserPassword(String pw) {
		if (pw != null) {
			this.setPwHashCode(pw.hashCode());
			super.setUserPassword(null);
		} 
	}
	
	public int pwHashCodeInt() {
		if (this.pwHashCode() != null)
			return this.pwHashCode().intValue();
		else
			return 0;
	}
	
    public String resetPassword() {
    	SecureRandom random = new SecureRandom();
    	String randomPassword = new BigInteger(130, random).toString(32);
    	this.setUserPassword(randomPassword);
    	return randomPassword;
	}
	
	public NSArray<Venue> pastVenues() {
		NSArray<Venue> results = null;
		
		String queryString = new String(Venue.DATE_END_KEY   + " < %@ ");
		NSMutableArray<Object> objects = new NSMutableArray<Object>(new NSTimestamp());
		
		EOQualifier qualifier = EOQualifier.qualifierWithQualifierFormat(queryString, objects);
		NSArray<EOSortOrdering> sortOrderings = new NSArray<EOSortOrdering>(new EOSortOrdering(Venue.DATE_START_KEY, EOSortOrdering.CompareAscending));
		
		results = this.venues(qualifier, sortOrderings);
		return results;
	}
	
	public NSArray<Venue> currentAndFutureVenues() {
		NSArray<Venue> results = null;
		
		String queryString = new String(Venue.DATE_END_KEY + " >= %@");
		NSArray<Object> objects = new NSArray<Object>(new NSTimestamp());
		
		EOQualifier qualifier = EOQualifier.qualifierWithQualifierFormat(queryString, objects);
		NSArray<EOSortOrdering> sortOrderings = new NSArray<EOSortOrdering>(new EOSortOrdering(Venue.DATE_START_KEY, EOSortOrdering.CompareAscending));
		
		results = this.venues(qualifier, sortOrderings);
		return results;
	}
	
	public String phoneURL() {
		if (this.phone() != null)
			return "tel:"+this.phone().replaceAll("[^0-9]", "");
		else
			return "#";
	}

	public String fullName() {
		StringBuffer returnString = null;
		if (this.nameFamily() != null) {
			returnString = new StringBuffer(this.nameFamily());
			if (this.nameGiven() != null)
				returnString.append(" (" + this.nameGiven() + ")");
		}
		return returnString.toString();
	}
	
	public String userEmailAddressURL() {
		if (this.userEmailAddress() != null)
			return "mailto:"+this.userEmailAddress();
		else
			return "#";
	}
	
	@SuppressWarnings("unchecked")
	public HotelInformation hotelInformationForVenue(Venue aVenue) {
		if (aVenue != null) {
			EOQualifier qualifier = EOQualifier.qualifierToMatchAnyValue(new NSDictionary(aVenue,HotelInformation.VENUE_KEY));

			EOSortOrdering sortOrdering = new EOSortOrdering(HotelInformation.CHECK_IN_KEY, EOSortOrdering.CompareAscending);
			NSArray<EOSortOrdering> sortOrderings = new NSArray<EOSortOrdering>(sortOrdering); 

			return (this.hotelInformations(qualifier, sortOrderings, false)).lastObject();
		} else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public TravelInformation travelInformationForVenue(Venue aVenue) {
		if (aVenue != null) {
			EOQualifier qualifier = EOQualifier.qualifierToMatchAnyValue(new NSDictionary(aVenue,HotelInformation.VENUE_KEY));

			EOSortOrdering dateSortOrdering = new EOSortOrdering(TravelInformation.ARRIVAL_DATE_KEY, EOSortOrdering.CompareAscending);
			NSArray<EOSortOrdering> sortOrderings = new NSArray<EOSortOrdering>(dateSortOrdering); 

			return (this.travelInformations(qualifier, sortOrderings, false)).lastObject();
		} else
			return null;
	}
	
	public NSArray<AttendeeSelectedVEvent> selectedEventsForVenue(Venue aVenue) {
		if (aVenue != null) {
			EOQualifier eoQualifier = EOQualifier.qualifierToMatchAnyValue(new NSDictionary<String, Object>(aVenue, AttendeeSelectedVEvent.VENUE_KEY));

//			EOSortOrdering datetimeSortOrdering = new EOSortOrdering(AttendeeSelectedVEvent.EVENT_KEY+"."+VEvent.DATE_TIME_START_KEY, EOSortOrdering.CompareAscending);
//			EOSortOrdering lableSortOrdering = new EOSortOrdering(AttendeeSelectedVEvent.EVENT_KEY+"."+VEvent.LABLE_KEY,  EOSortOrdering.CompareAscending);

			NSMutableArray<EOSortOrdering> sortOrderings = new NSMutableArray<EOSortOrdering>();
//			sortOrderings.add(datetimeSortOrdering);
//			sortOrderings.add(lableSortOrdering);
			//sortOrderings.add(EOSortOrdering.sortOrderingWithKey(VEvent.DATE_TIME_START_KEY, EOSortOrdering.CompareAscending));
			sortOrderings.add(EOSortOrdering.sortOrderingWithKey(AttendeeSelectedVEvent.EVENT_KEY+"."+VEvent.SECTION_DATE_KEY, EOSortOrdering.CompareAscending));
			sortOrderings.add(EOSortOrdering.sortOrderingWithKey(AttendeeSelectedVEvent.EVENT_KEY+"."+VEvent.START_HOUR_KEY, EOSortOrdering.CompareAscending));
			sortOrderings.add(EOSortOrdering.sortOrderingWithKey(AttendeeSelectedVEvent.EVENT_KEY+"."+VEvent.START_MINUTE_KEY, EOSortOrdering.CompareAscending));
			sortOrderings.add(EOSortOrdering.sortOrderingWithKey(AttendeeSelectedVEvent.EVENT_KEY+"."+VEvent.LABLE_KEY, EOSortOrdering.CompareAscending));
			return this.selectedEvents(eoQualifier, sortOrderings, false);
		} else
			return null;
	}
	
	public void deleteHotelInformationForVenu(Venue aVenue) {
		if (aVenue != null) {
			HotelInformation hotelInformation = this.hotelInformationForVenue(aVenue);
			if (hotelInformation != null) {
				this.deleteHotelInformationsRelationship(hotelInformation);
				this.editingContext().deleteObject(hotelInformation);
			}
		}
	}

	public void deleteTravelInformationForVenu(Venue aVenue) {
		if (aVenue != null) {
			TravelInformation travelInformation = this.travelInformationForVenue(aVenue);
			if (travelInformation != null) {
				this.deleteTravelInformationsRelationship(travelInformation);
				this.editingContext().deleteObject(travelInformation);
			}
		}
	}

	public void deleteSelectedEventsForVenue(Venue aVenue) {
		if (aVenue != null) {
			NSArray<AttendeeSelectedVEvent> selectedEventsForVenue = this.selectedEventsForVenue(aVenue);
			for (int i = 0; i < selectedEventsForVenue.count(); i++) {
				this.deleteSelectedEventsRelationship(selectedEventsForVenue.objectAtIndex(i));
				this.editingContext().deleteObject(selectedEventsForVenue.objectAtIndex(i));
			}
		}
	}

//	@SuppressWarnings("unchecked")
//	public NSArray<AttendeeSelectedVEvent> selectedEventsForVenueOld(Venue aVenue) {
//		if (aVenue != null) {
//			NSArray<AttendeeSelectedVEvent> results = null;
//
//			if (aVenue != null) {
//
//				// This is the format string used in the EOQualifier
//				StringBuffer formatString = new StringBuffer(
//						AttendeeSelectedVEvent.VENUE_KEY + " = %@ AND " + 
//						AttendeeSelectedVEvent.ATTENDEE_KEY + " = %@"
//				);
//
//				// This is used to add search parameters using objects such as Language, Topics, etc.
//				NSMutableArray args = new NSMutableArray();
//				args.addObject(aVenue);
//				args.addObject(this);
//
//				EOQualifier eoQualifier = EOQualifier.qualifierWithQualifierFormat(formatString.toString(), args);
//
//				NSArray<EOSortOrdering> dateStartOrderings = new NSArray<EOSortOrdering>(
//						EOSortOrdering.sortOrderingWithKey(
//								AttendeeSelectedVEvent.EVENT_KEY + "." + VEvent.DATE_TIME_START_KEY,
//								EOSortOrdering.CompareAscending
//						)
//				);
//
//				EOFetchSpecification theFetchSpecs = new EOFetchSpecification(
//						AttendeeSelectedVEvent.ENTITY_NAME,
//						eoQualifier,
//						dateStartOrderings
//				);
//
//				try {
//					results = this.editingContext().objectsWithFetchSpecification(theFetchSpecs);
//				} catch (RuntimeException ex) {
//					log.info("Found no selected events for from attendee " + this.nameFamily() + "and venue " + aVenue.lable());
//					log.error(ex.getMessage());
//				}
//
//			}
//
//			return results;
//		} else
//			return null;
//	}

}


//String[] keys = {AttendeeSelectedVEvent.VENUE_KEY, AttendeeSelectedVEvent.ATTENDEE_KEY};
//Object[] values = {aVenue, this};
//NSDictionary bindings = new NSDictionary(values, keys);
//
//try {
//	results = EOUtilities.objectsMatchingValues(this.editingContext(), AttendeeSelectedVEvent.ENTITY_NAME, bindings);
//} catch (RuntimeException ex) {
//	log.info("Found no selected events for from attendee " + this.nameFamily() + "and venue " + aVenue.lable());
//	log.error(ex.getMessage());
//}

//results = EOUtilities.objectsMatchingValues(this.editingContext(), AttendeeSelectedVEvent.ENTITY_NAME, bindings);
//results = EOUtilities.objectsMatchingKeyAndValue(
//		this.editingContext(), 
//		AttendeeSelectedVEvent.ENTITY_NAME, 
//		AttendeeSelectedVEvent.VENUE_KEY, 
//		aVenue);


/*

Trying to create this SQL query:

select  VEvent.idVenue, VEvent.lable, AttendeeSelectedVEvent.participants, AttendeeSelectedVEvent.idAttendee 
from AttendeeSelectedVEvent
right outer join (VEvent) on (VEvent.id = AttendeeSelectedVEvent.idVEvent)
where ((AttendeeSelectedVEvent.idAttendee = 2 or AttendeeSelectedVEvent.idAttendee is null) and VEvent.idVenue = 1);

Actual query:

SELECT t0.id, t0.idAttendee, t0.idVEvent, t0.idVenue, t0.participants 
FROM AttendeeSelectedVEvent t0 
WHERE ((t0.idAttendee is NULL OR t0.idAttendee = 2) AND t0.idVenue = 1)


		 */
		
        // This is used to add search parameters using objects such as Language, Topics, etc.
//        NSMutableArray args = new NSMutableArray();
//        
//        // This is the format string used in the EOQualifier
//        StringBuffer formatString = new StringBuffer(
//        		AttendeeSelectedVEvent.VENUE_KEY + " = %@ AND " +
//        		"(" + AttendeeSelectedVEvent.ATTENDEE_KEY + " = %@ OR " + AttendeeSelectedVEvent.ATTENDEE_KEY + " = null)"
//        );
//
//        log.error(formatString);
//        
//        args.addObject(aVenue);
//        args.addObject(this);
//
//        EOQualifier eoQualifier = EOQualifier.qualifierWithQualifierFormat(formatString.toString(), args);
//
//        EOFetchSpecification theFetchSpecs = new EOFetchSpecification(
//        		AttendeeSelectedVEvent.ENTITY_NAME,
//        		eoQualifier,
//        		null
//        );
//        //theFetchSpecs.setPrefetchingRelationshipKeyPaths(new NSArray(AttendeeSelectedVEvent.EVENT_KEY));
////        NSDictionary<String, String> hints = new NSDictionary<String, String>(
////        		EODatabaseContext.CustomQueryExpressionHintKey, 
////        		"SELECT t0.id, t0.idAttendee, t0.idVEvent, t0.idVenue, t0.participants FROM AttendeeSelectedVEvent t0 WHERE ((t0.idAttendee is NULL OR t0.idAttendee = ?) AND t0.idVenue = ?)"
////        ); 
//        NSDictionary<String, String> hints = new NSDictionary<String, String>(
//        		EODatabaseContext.CustomQueryExpressionHintKey, 
//        		"SELECT t0.id, t0.idAttendee, t0.idVEvent, t0.idVenue, t0.participants FROM AttendeeSelectedVEvent t0 RIGHT OUTER JOIN (VEvent t1) ON (t1.id = t0.idVEvent) WHERE ((t0.idAttendee is NULL OR t0.idAttendee = ?) AND t0.idVenue = ?)"
//        ); 
//
//        theFetchSpecs.setHints(hints);
//        theFetchSpecs.setIsDeep(true);



//String[] keys = {AttendeeSelectedVEvent.VENUE_KEY, AttendeeSelectedVEvent.ATTENDEE_KEY};
//Object[] values = {aVenue, this};
//NSDictionary bindings = new NSDictionary(values, keys);
//
//try {
//	results = EOUtilities.objectsMatchingValues(this.editingContext(), AttendeeSelectedVEvent.ENTITY_NAME, bindings);
//} catch (RuntimeException ex) {
//	log.info("Found no events for from attendee " + this.nameFamily() + "and venue " + aVenue.lable());
//	log.error(ex.getMessage());
//}
