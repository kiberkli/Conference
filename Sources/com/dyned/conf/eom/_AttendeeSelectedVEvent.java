// $LastChangedRevision$ DO NOT EDIT.  Make changes to AttendeeSelectedVEvent.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _AttendeeSelectedVEvent extends  EOGenericRecord {
	public static final String ENTITY_NAME = "AttendeeSelectedVEvent";

	// Attributes
	public static final String PARTICIPANTS_KEY = "participants";

	// Relationships
	public static final String ATTENDEE_KEY = "attendee";
	public static final String EVENT_KEY = "event";
	public static final String VENUE_KEY = "venue";

  private static Logger LOG = Logger.getLogger(_AttendeeSelectedVEvent.class);

  public AttendeeSelectedVEvent localInstanceIn(EOEditingContext editingContext) {
    AttendeeSelectedVEvent localInstance = (AttendeeSelectedVEvent)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Integer participants() {
    return (Integer) storedValueForKey("participants");
  }

  public void setParticipants(Integer value) {
    if (_AttendeeSelectedVEvent.LOG.isDebugEnabled()) {
    	_AttendeeSelectedVEvent.LOG.debug( "updating participants from " + participants() + " to " + value);
    }
    takeStoredValueForKey(value, "participants");
  }

  public com.dyned.conf.eom.Attendee attendee() {
    return (com.dyned.conf.eom.Attendee)storedValueForKey("attendee");
  }

  public void setAttendeeRelationship(com.dyned.conf.eom.Attendee value) {
    if (_AttendeeSelectedVEvent.LOG.isDebugEnabled()) {
      _AttendeeSelectedVEvent.LOG.debug("updating attendee from " + attendee() + " to " + value);
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
  
  public com.dyned.conf.eom.VEvent event() {
    return (com.dyned.conf.eom.VEvent)storedValueForKey("event");
  }

  public void setEventRelationship(com.dyned.conf.eom.VEvent value) {
    if (_AttendeeSelectedVEvent.LOG.isDebugEnabled()) {
      _AttendeeSelectedVEvent.LOG.debug("updating event from " + event() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.VEvent oldValue = event();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "event");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "event");
    }
  }
  
  public com.dyned.conf.eom.Venue venue() {
    return (com.dyned.conf.eom.Venue)storedValueForKey("venue");
  }

  public void setVenueRelationship(com.dyned.conf.eom.Venue value) {
    if (_AttendeeSelectedVEvent.LOG.isDebugEnabled()) {
      _AttendeeSelectedVEvent.LOG.debug("updating venue from " + venue() + " to " + value);
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
  

  public static AttendeeSelectedVEvent createAttendeeSelectedVEvent(EOEditingContext editingContext, Integer participants
, com.dyned.conf.eom.Attendee attendee, com.dyned.conf.eom.Venue venue) {
    AttendeeSelectedVEvent eo = (AttendeeSelectedVEvent) EOUtilities.createAndInsertInstance(editingContext, _AttendeeSelectedVEvent.ENTITY_NAME);    
		eo.setParticipants(participants);
    eo.setAttendeeRelationship(attendee);
    eo.setVenueRelationship(venue);
    return eo;
  }

  public static NSArray<AttendeeSelectedVEvent> fetchAllAttendeeSelectedVEvents(EOEditingContext editingContext) {
    return _AttendeeSelectedVEvent.fetchAllAttendeeSelectedVEvents(editingContext, null);
  }

  public static NSArray<AttendeeSelectedVEvent> fetchAllAttendeeSelectedVEvents(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _AttendeeSelectedVEvent.fetchAttendeeSelectedVEvents(editingContext, null, sortOrderings);
  }

  public static NSArray<AttendeeSelectedVEvent> fetchAttendeeSelectedVEvents(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_AttendeeSelectedVEvent.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<AttendeeSelectedVEvent> eoObjects = (NSArray<AttendeeSelectedVEvent>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static AttendeeSelectedVEvent fetchAttendeeSelectedVEvent(EOEditingContext editingContext, String keyName, Object value) {
    return _AttendeeSelectedVEvent.fetchAttendeeSelectedVEvent(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static AttendeeSelectedVEvent fetchAttendeeSelectedVEvent(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<AttendeeSelectedVEvent> eoObjects = _AttendeeSelectedVEvent.fetchAttendeeSelectedVEvents(editingContext, qualifier, null);
    AttendeeSelectedVEvent eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (AttendeeSelectedVEvent)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one AttendeeSelectedVEvent that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static AttendeeSelectedVEvent fetchRequiredAttendeeSelectedVEvent(EOEditingContext editingContext, String keyName, Object value) {
    return _AttendeeSelectedVEvent.fetchRequiredAttendeeSelectedVEvent(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static AttendeeSelectedVEvent fetchRequiredAttendeeSelectedVEvent(EOEditingContext editingContext, EOQualifier qualifier) {
    AttendeeSelectedVEvent eoObject = _AttendeeSelectedVEvent.fetchAttendeeSelectedVEvent(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no AttendeeSelectedVEvent that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static AttendeeSelectedVEvent localInstanceIn(EOEditingContext editingContext, AttendeeSelectedVEvent eo) {
    AttendeeSelectedVEvent localInstance = (eo == null) ? null : (AttendeeSelectedVEvent)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
