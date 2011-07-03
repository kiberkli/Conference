// $LastChangedRevision$ DO NOT EDIT.  Make changes to VEvent.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _VEvent extends  EOGenericRecord {
	public static final String ENTITY_NAME = "VEvent";

	// Attributes
	public static final String COLOR_KEY = "color";
	public static final String DATE_TIME_END_KEY = "dateTimeEnd";
	public static final String DATE_TIME_START_KEY = "dateTimeStart";
	public static final String DESCRIPTION_KEY = "description";
	public static final String LABLE_KEY = "lable";
	public static final String MEETING_PLACE_KEY = "meetingPlace";

	// Relationships
	public static final String ATTENDEE_SELECTED_V_EVENTS_KEY = "attendeeSelectedVEvents";
	public static final String VENUE_KEY = "venue";

  private static Logger LOG = Logger.getLogger(_VEvent.class);

  public VEvent localInstanceIn(EOEditingContext editingContext) {
    VEvent localInstance = (VEvent)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String color() {
    return (String) storedValueForKey("color");
  }

  public void setColor(String value) {
    if (_VEvent.LOG.isDebugEnabled()) {
    	_VEvent.LOG.debug( "updating color from " + color() + " to " + value);
    }
    takeStoredValueForKey(value, "color");
  }

  public NSTimestamp dateTimeEnd() {
    return (NSTimestamp) storedValueForKey("dateTimeEnd");
  }

  public void setDateTimeEnd(NSTimestamp value) {
    if (_VEvent.LOG.isDebugEnabled()) {
    	_VEvent.LOG.debug( "updating dateTimeEnd from " + dateTimeEnd() + " to " + value);
    }
    takeStoredValueForKey(value, "dateTimeEnd");
  }

  public NSTimestamp dateTimeStart() {
    return (NSTimestamp) storedValueForKey("dateTimeStart");
  }

  public void setDateTimeStart(NSTimestamp value) {
    if (_VEvent.LOG.isDebugEnabled()) {
    	_VEvent.LOG.debug( "updating dateTimeStart from " + dateTimeStart() + " to " + value);
    }
    takeStoredValueForKey(value, "dateTimeStart");
  }

  public String description() {
    return (String) storedValueForKey("description");
  }

  public void setDescription(String value) {
    if (_VEvent.LOG.isDebugEnabled()) {
    	_VEvent.LOG.debug( "updating description from " + description() + " to " + value);
    }
    takeStoredValueForKey(value, "description");
  }

  public String lable() {
    return (String) storedValueForKey("lable");
  }

  public void setLable(String value) {
    if (_VEvent.LOG.isDebugEnabled()) {
    	_VEvent.LOG.debug( "updating lable from " + lable() + " to " + value);
    }
    takeStoredValueForKey(value, "lable");
  }

  public String meetingPlace() {
    return (String) storedValueForKey("meetingPlace");
  }

  public void setMeetingPlace(String value) {
    if (_VEvent.LOG.isDebugEnabled()) {
    	_VEvent.LOG.debug( "updating meetingPlace from " + meetingPlace() + " to " + value);
    }
    takeStoredValueForKey(value, "meetingPlace");
  }

  public com.dyned.conf.eom.Venue venue() {
    return (com.dyned.conf.eom.Venue)storedValueForKey("venue");
  }

  public void setVenueRelationship(com.dyned.conf.eom.Venue value) {
    if (_VEvent.LOG.isDebugEnabled()) {
      _VEvent.LOG.debug("updating venue from " + venue() + " to " + value);
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
  
  public NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> attendeeSelectedVEvents() {
    return (NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent>)storedValueForKey("attendeeSelectedVEvents");
  }

  public NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> attendeeSelectedVEvents(EOQualifier qualifier) {
    return attendeeSelectedVEvents(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> attendeeSelectedVEvents(EOQualifier qualifier, boolean fetch) {
    return attendeeSelectedVEvents(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> attendeeSelectedVEvents(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.AttendeeSelectedVEvent.EVENT_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.dyned.conf.eom.AttendeeSelectedVEvent.fetchAttendeeSelectedVEvents(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = attendeeSelectedVEvents();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToAttendeeSelectedVEventsRelationship(com.dyned.conf.eom.AttendeeSelectedVEvent object) {
    if (_VEvent.LOG.isDebugEnabled()) {
      _VEvent.LOG.debug("adding " + object + " to attendeeSelectedVEvents relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "attendeeSelectedVEvents");
  }

  public void removeFromAttendeeSelectedVEventsRelationship(com.dyned.conf.eom.AttendeeSelectedVEvent object) {
    if (_VEvent.LOG.isDebugEnabled()) {
      _VEvent.LOG.debug("removing " + object + " from attendeeSelectedVEvents relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "attendeeSelectedVEvents");
  }

  public com.dyned.conf.eom.AttendeeSelectedVEvent createAttendeeSelectedVEventsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AttendeeSelectedVEvent");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "attendeeSelectedVEvents");
    return (com.dyned.conf.eom.AttendeeSelectedVEvent) eo;
  }

  public void deleteAttendeeSelectedVEventsRelationship(com.dyned.conf.eom.AttendeeSelectedVEvent object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "attendeeSelectedVEvents");
    editingContext().deleteObject(object);
  }

  public void deleteAllAttendeeSelectedVEventsRelationships() {
    Enumeration objects = attendeeSelectedVEvents().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteAttendeeSelectedVEventsRelationship((com.dyned.conf.eom.AttendeeSelectedVEvent)objects.nextElement());
    }
  }


  public static VEvent createVEvent(EOEditingContext editingContext, NSTimestamp dateTimeEnd
, NSTimestamp dateTimeStart
, String lable
, com.dyned.conf.eom.Venue venue) {
    VEvent eo = (VEvent) EOUtilities.createAndInsertInstance(editingContext, _VEvent.ENTITY_NAME);    
		eo.setDateTimeEnd(dateTimeEnd);
		eo.setDateTimeStart(dateTimeStart);
		eo.setLable(lable);
    eo.setVenueRelationship(venue);
    return eo;
  }

  public static NSArray<VEvent> fetchAllVEvents(EOEditingContext editingContext) {
    return _VEvent.fetchAllVEvents(editingContext, null);
  }

  public static NSArray<VEvent> fetchAllVEvents(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _VEvent.fetchVEvents(editingContext, null, sortOrderings);
  }

  public static NSArray<VEvent> fetchVEvents(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_VEvent.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<VEvent> eoObjects = (NSArray<VEvent>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static VEvent fetchVEvent(EOEditingContext editingContext, String keyName, Object value) {
    return _VEvent.fetchVEvent(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static VEvent fetchVEvent(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<VEvent> eoObjects = _VEvent.fetchVEvents(editingContext, qualifier, null);
    VEvent eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (VEvent)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VEvent that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VEvent fetchRequiredVEvent(EOEditingContext editingContext, String keyName, Object value) {
    return _VEvent.fetchRequiredVEvent(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static VEvent fetchRequiredVEvent(EOEditingContext editingContext, EOQualifier qualifier) {
    VEvent eoObject = _VEvent.fetchVEvent(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VEvent that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VEvent localInstanceIn(EOEditingContext editingContext, VEvent eo) {
    VEvent localInstance = (eo == null) ? null : (VEvent)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
