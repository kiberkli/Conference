// $LastChangedRevision$ DO NOT EDIT.  Make changes to HotelInformation.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _HotelInformation extends  EOGenericRecord {
	public static final String ENTITY_NAME = "HotelInformation";

	// Attributes
	public static final String CHECK_IN_KEY = "checkIn";
	public static final String CHECK_OUT_KEY = "checkOut";
	public static final String HOTEL_ADDRESS_KEY = "hotelAddress";
	public static final String HOTEL_NAME_KEY = "hotelName";
	public static final String NUM_ROOMS_KEY = "numRooms";
	public static final String TELEPHONE_KEY = "telephone";

	// Relationships
	public static final String ATTENDEE_KEY = "attendee";
	public static final String VENUE_KEY = "venue";

  private static Logger LOG = Logger.getLogger(_HotelInformation.class);

  public HotelInformation localInstanceIn(EOEditingContext editingContext) {
    HotelInformation localInstance = (HotelInformation)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp checkIn() {
    return (NSTimestamp) storedValueForKey("checkIn");
  }

  public void setCheckIn(NSTimestamp value) {
    if (_HotelInformation.LOG.isDebugEnabled()) {
    	_HotelInformation.LOG.debug( "updating checkIn from " + checkIn() + " to " + value);
    }
    takeStoredValueForKey(value, "checkIn");
  }

  public NSTimestamp checkOut() {
    return (NSTimestamp) storedValueForKey("checkOut");
  }

  public void setCheckOut(NSTimestamp value) {
    if (_HotelInformation.LOG.isDebugEnabled()) {
    	_HotelInformation.LOG.debug( "updating checkOut from " + checkOut() + " to " + value);
    }
    takeStoredValueForKey(value, "checkOut");
  }

  public String hotelAddress() {
    return (String) storedValueForKey("hotelAddress");
  }

  public void setHotelAddress(String value) {
    if (_HotelInformation.LOG.isDebugEnabled()) {
    	_HotelInformation.LOG.debug( "updating hotelAddress from " + hotelAddress() + " to " + value);
    }
    takeStoredValueForKey(value, "hotelAddress");
  }

  public String hotelName() {
    return (String) storedValueForKey("hotelName");
  }

  public void setHotelName(String value) {
    if (_HotelInformation.LOG.isDebugEnabled()) {
    	_HotelInformation.LOG.debug( "updating hotelName from " + hotelName() + " to " + value);
    }
    takeStoredValueForKey(value, "hotelName");
  }

  public Integer numRooms() {
    return (Integer) storedValueForKey("numRooms");
  }

  public void setNumRooms(Integer value) {
    if (_HotelInformation.LOG.isDebugEnabled()) {
    	_HotelInformation.LOG.debug( "updating numRooms from " + numRooms() + " to " + value);
    }
    takeStoredValueForKey(value, "numRooms");
  }

  public String telephone() {
    return (String) storedValueForKey("telephone");
  }

  public void setTelephone(String value) {
    if (_HotelInformation.LOG.isDebugEnabled()) {
    	_HotelInformation.LOG.debug( "updating telephone from " + telephone() + " to " + value);
    }
    takeStoredValueForKey(value, "telephone");
  }

  public com.dyned.conf.eom.Attendee attendee() {
    return (com.dyned.conf.eom.Attendee)storedValueForKey("attendee");
  }

  public void setAttendeeRelationship(com.dyned.conf.eom.Attendee value) {
    if (_HotelInformation.LOG.isDebugEnabled()) {
      _HotelInformation.LOG.debug("updating attendee from " + attendee() + " to " + value);
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
    if (_HotelInformation.LOG.isDebugEnabled()) {
      _HotelInformation.LOG.debug("updating venue from " + venue() + " to " + value);
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
  

  public static HotelInformation createHotelInformation(EOEditingContext editingContext, com.dyned.conf.eom.Attendee attendee, com.dyned.conf.eom.Venue venue) {
    HotelInformation eo = (HotelInformation) EOUtilities.createAndInsertInstance(editingContext, _HotelInformation.ENTITY_NAME);    
    eo.setAttendeeRelationship(attendee);
    eo.setVenueRelationship(venue);
    return eo;
  }

  public static NSArray<HotelInformation> fetchAllHotelInformations(EOEditingContext editingContext) {
    return _HotelInformation.fetchAllHotelInformations(editingContext, null);
  }

  public static NSArray<HotelInformation> fetchAllHotelInformations(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _HotelInformation.fetchHotelInformations(editingContext, null, sortOrderings);
  }

  public static NSArray<HotelInformation> fetchHotelInformations(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_HotelInformation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<HotelInformation> eoObjects = (NSArray<HotelInformation>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static HotelInformation fetchHotelInformation(EOEditingContext editingContext, String keyName, Object value) {
    return _HotelInformation.fetchHotelInformation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static HotelInformation fetchHotelInformation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<HotelInformation> eoObjects = _HotelInformation.fetchHotelInformations(editingContext, qualifier, null);
    HotelInformation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (HotelInformation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one HotelInformation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static HotelInformation fetchRequiredHotelInformation(EOEditingContext editingContext, String keyName, Object value) {
    return _HotelInformation.fetchRequiredHotelInformation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static HotelInformation fetchRequiredHotelInformation(EOEditingContext editingContext, EOQualifier qualifier) {
    HotelInformation eoObject = _HotelInformation.fetchHotelInformation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no HotelInformation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static HotelInformation localInstanceIn(EOEditingContext editingContext, HotelInformation eo) {
    HotelInformation localInstance = (eo == null) ? null : (HotelInformation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
