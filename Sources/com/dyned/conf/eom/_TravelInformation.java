// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to TravelInformation.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _TravelInformation extends  EOGenericRecord {
	public static final String ENTITY_NAME = "TravelInformation";

	// Attributes
	public static final String ARRIVAL_DATE_KEY = "arrivalDate";
	public static final String ARRIVAL_FLIGHT_NUMBER_KEY = "arrivalFlightNumber";
	public static final String ARRIVAL_FROM_KEY = "arrivalFrom";
	public static final String DEPARTURE_DATE_KEY = "departureDate";
	public static final String DEPARTURE_FLIGHT_NUMBER_KEY = "departureFlightNumber";
	public static final String DEPARTURE_TO_KEY = "departureTo";

	// Relationships
	public static final String ATTENDEE_KEY = "attendee";
	public static final String VENUE_KEY = "venue";

  private static Logger LOG = Logger.getLogger(_TravelInformation.class);

  public TravelInformation localInstanceIn(EOEditingContext editingContext) {
    TravelInformation localInstance = (TravelInformation)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp arrivalDate() {
    return (NSTimestamp) storedValueForKey("arrivalDate");
  }

  public void setArrivalDate(NSTimestamp value) {
    if (_TravelInformation.LOG.isDebugEnabled()) {
    	_TravelInformation.LOG.debug( "updating arrivalDate from " + arrivalDate() + " to " + value);
    }
    takeStoredValueForKey(value, "arrivalDate");
  }

  public String arrivalFlightNumber() {
    return (String) storedValueForKey("arrivalFlightNumber");
  }

  public void setArrivalFlightNumber(String value) {
    if (_TravelInformation.LOG.isDebugEnabled()) {
    	_TravelInformation.LOG.debug( "updating arrivalFlightNumber from " + arrivalFlightNumber() + " to " + value);
    }
    takeStoredValueForKey(value, "arrivalFlightNumber");
  }

  public String arrivalFrom() {
    return (String) storedValueForKey("arrivalFrom");
  }

  public void setArrivalFrom(String value) {
    if (_TravelInformation.LOG.isDebugEnabled()) {
    	_TravelInformation.LOG.debug( "updating arrivalFrom from " + arrivalFrom() + " to " + value);
    }
    takeStoredValueForKey(value, "arrivalFrom");
  }

  public NSTimestamp departureDate() {
    return (NSTimestamp) storedValueForKey("departureDate");
  }

  public void setDepartureDate(NSTimestamp value) {
    if (_TravelInformation.LOG.isDebugEnabled()) {
    	_TravelInformation.LOG.debug( "updating departureDate from " + departureDate() + " to " + value);
    }
    takeStoredValueForKey(value, "departureDate");
  }

  public String departureFlightNumber() {
    return (String) storedValueForKey("departureFlightNumber");
  }

  public void setDepartureFlightNumber(String value) {
    if (_TravelInformation.LOG.isDebugEnabled()) {
    	_TravelInformation.LOG.debug( "updating departureFlightNumber from " + departureFlightNumber() + " to " + value);
    }
    takeStoredValueForKey(value, "departureFlightNumber");
  }

  public String departureTo() {
    return (String) storedValueForKey("departureTo");
  }

  public void setDepartureTo(String value) {
    if (_TravelInformation.LOG.isDebugEnabled()) {
    	_TravelInformation.LOG.debug( "updating departureTo from " + departureTo() + " to " + value);
    }
    takeStoredValueForKey(value, "departureTo");
  }

  public com.dyned.conf.eom.Attendee attendee() {
    return (com.dyned.conf.eom.Attendee)storedValueForKey("attendee");
  }

  public void setAttendeeRelationship(com.dyned.conf.eom.Attendee value) {
    if (_TravelInformation.LOG.isDebugEnabled()) {
      _TravelInformation.LOG.debug("updating attendee from " + attendee() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.Attendee oldValue = attendee();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "attendee");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "attendee");
    }
  }
  
  public com.dyned.conf.eom.Venue venue() {
    return (com.dyned.conf.eom.Venue)storedValueForKey("venue");
  }

  public void setVenueRelationship(com.dyned.conf.eom.Venue value) {
    if (_TravelInformation.LOG.isDebugEnabled()) {
      _TravelInformation.LOG.debug("updating venue from " + venue() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.Venue oldValue = venue();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "venue");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "venue");
    }
  }
  

  public static TravelInformation createTravelInformation(EOEditingContext editingContext, com.dyned.conf.eom.Attendee attendee, com.dyned.conf.eom.Venue venue) {
    TravelInformation eo = (TravelInformation) EOUtilities.createAndInsertInstance(editingContext, _TravelInformation.ENTITY_NAME);    
    eo.setAttendeeRelationship(attendee);
    eo.setVenueRelationship(venue);
    return eo;
  }

  public static NSArray<TravelInformation> fetchAllTravelInformations(EOEditingContext editingContext) {
    return _TravelInformation.fetchAllTravelInformations(editingContext, null);
  }

  public static NSArray<TravelInformation> fetchAllTravelInformations(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _TravelInformation.fetchTravelInformations(editingContext, null, sortOrderings);
  }

  public static NSArray<TravelInformation> fetchTravelInformations(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_TravelInformation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<TravelInformation> eoObjects = (NSArray<TravelInformation>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static TravelInformation fetchTravelInformation(EOEditingContext editingContext, String keyName, Object value) {
    return _TravelInformation.fetchTravelInformation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static TravelInformation fetchTravelInformation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<TravelInformation> eoObjects = _TravelInformation.fetchTravelInformations(editingContext, qualifier, null);
    TravelInformation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (TravelInformation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TravelInformation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static TravelInformation fetchRequiredTravelInformation(EOEditingContext editingContext, String keyName, Object value) {
    return _TravelInformation.fetchRequiredTravelInformation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static TravelInformation fetchRequiredTravelInformation(EOEditingContext editingContext, EOQualifier qualifier) {
    TravelInformation eoObject = _TravelInformation.fetchTravelInformation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TravelInformation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static TravelInformation localInstanceIn(EOEditingContext editingContext, TravelInformation eo) {
    TravelInformation localInstance = (eo == null) ? null : (TravelInformation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
