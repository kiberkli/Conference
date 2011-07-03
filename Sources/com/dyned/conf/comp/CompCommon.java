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
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.AttendeeFunction;
import com.dyned.conf.eom.SpecialNeed;
import com.dyned.conf.eom.Venue;

import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOObjectNotAvailableException;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.appserver.ERXApplication;
import er.extensions.components.ERXComponent;

public class CompCommon extends ERXComponent {

	private static Logger log = Logger.getLogger(CompCommon.class);
			 
	public EOEditingContext ec;
	
	public CompCommon(WOContext context) {
		super(context);
        ec = ((Session)session()).defaultEditingContext();
	}

	@SuppressWarnings("unchecked")
	public NSArray<AttendeeFunction> fullAttendeeFunctionList() {
		NSArray<AttendeeFunction> results = null;

		// Create the fetch spec:
		EOFetchSpecification theFetchSpecs = new EOFetchSpecification(AttendeeFunction.ENTITY_NAME, null, null);

		try {
			results = ec.objectsWithFetchSpecification(theFetchSpecs);
		} catch (RuntimeException ex) {
			log.error("Error fetching AttendeeFunction list:");
			log.error(ex.getMessage());
		}

		return results;
	}
	
	@SuppressWarnings("unchecked")
	public NSArray<SpecialNeed> fullSpecialNeedList() {
		NSArray<SpecialNeed> results = null;
		
		// Create the fetch spec:
		EOFetchSpecification theFetchSpecs = new EOFetchSpecification(SpecialNeed.ENTITY_NAME, null, null);

		try {
			results = ec.objectsWithFetchSpecification(theFetchSpecs);
		} catch (RuntimeException ex) {
			log.error("Error fetching AttendeeFunction list:");
			log.error(ex.getMessage());
		}

		return results;
	}
	
	@SuppressWarnings("unchecked")
	public NSArray<Venue> fullVenuList() {
		NSArray<Venue> results = null;
        
		EOQualifier eoQualifier = null; // Use when using a format string.
		NSMutableArray<EOSortOrdering> sortOrderings = new NSMutableArray<EOSortOrdering>();

		// Add sort options here:
		EOSortOrdering sortByDate = new EOSortOrdering(Venue.DATE_START_KEY, EOSortOrdering.CompareDescending);
		sortOrderings.addObject(sortByDate);

		// Create the fetch spec:
		EOFetchSpecification theFetchSpecs = new EOFetchSpecification(Venue.ENTITY_NAME, eoQualifier, sortOrderings);

		try {
			results = ec.objectsWithFetchSpecification(theFetchSpecs);
		} catch (RuntimeException ex) {
			log.error("Error fetching Venue list:");
			log.error(ex.getMessage());
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	public NSArray<Admin> fullAdministratorList() {
    	NSArray<Admin> results = null;

		EOQualifier eoQualifier = null; // Use when using a format string.
		NSMutableArray<EOSortOrdering> sortOrderings = new NSMutableArray<EOSortOrdering>();

		// Add sort options here:
		EOSortOrdering sortByDate = new EOSortOrdering(Admin.FULL_NAME_KEY, EOSortOrdering.CompareAscending);
		sortOrderings.addObject(sortByDate);

		// Create the fetch spec:
		EOFetchSpecification theFetchSpecs = new EOFetchSpecification(Admin.ENTITY_NAME, eoQualifier, sortOrderings);

		try {
			results = ec.objectsWithFetchSpecification(theFetchSpecs);
		} catch (RuntimeException ex) {
			log.error("Error fetching Admin list:");
			log.error(ex.getMessage());
		}
		
    	return results;
    }
	
	public boolean saveMyChanges(String errorStringOnFail) {
		boolean returnValue = false;
		try {
			ec.saveChanges();
			returnValue = true;
			log.info("++++ Save sucessful.");
		} catch (RuntimeException ex) {
			ec.revert();
			log.error("--- " + errorStringOnFail + ":");
			log.error(ex.getMessage());
		}
		return returnValue;
	}
	
	public boolean emailAddressExistInAdmins(String emailAddress) {
		Admin administrator = null;
		Object[] keys = {Admin.EMAIL_ADDRESS_KEY};
		Object[] values = {emailAddress};
		NSDictionary<Object, Object> bindings = new NSDictionary<Object, Object>(values, keys);

		try {
			administrator = (Admin)EOUtilities.objectMatchingValues(ec,Admin.ENTITY_NAME, bindings);
		} catch (EOUtilities.MoreThanOneException e1) {
			log.error("Found more then one of " + emailAddress);
			log.error(e1.getMessage());
		} catch (EOObjectNotAvailableException e2) {
			log.error("Did not find " + emailAddress);
			log.error(e2.getMessage());
		} catch (RuntimeException e3) {
			log.error("Looking for " + emailAddress + " failed");
			log.error(e3.getMessage());
		}
		
		if (administrator != null)
			return true;
		else
			return false;
	}
	
	public boolean emailAddressExistInAttendee(String emailAddress) {
		Attendee attendee = null;
		Object[] keys = {Attendee.USER_EMAIL_ADDRESS_KEY};
		Object[] values = {emailAddress};
		NSDictionary<Object, Object> bindings = new NSDictionary<Object, Object>(values, keys);

		try {
			attendee = (Attendee)EOUtilities.objectMatchingValues(ec,Attendee.ENTITY_NAME, bindings);
		} catch (EOUtilities.MoreThanOneException e1) {
			log.error("Found more then one of " + emailAddress);
			log.error(e1.getMessage());
		} catch (EOObjectNotAvailableException e2) {
			log.error("Did not find " + emailAddress);
			log.error(e2.getMessage());
		} catch (RuntimeException e3) {
			log.error("Looking for " + emailAddress + " failed");
			log.error(e3.getMessage());
		}
		
		if (attendee != null)
			return true;
		else
			return false;
	}

	//	public int monthsListIndex() {
//		return monthsListIndex = monthsListIndex + 1;
//	}
}

