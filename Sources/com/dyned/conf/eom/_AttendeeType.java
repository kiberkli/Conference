// $LastChangedRevision$ DO NOT EDIT.  Make changes to AttendeeType.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _AttendeeType extends  EOGenericRecord {
	public static final String ENTITY_NAME = "AttendeeType";

	// Attributes
	public static final String LABLE_KEY = "lable";

	// Relationships
	public static final String ATTENDEES_KEY = "attendees";

  private static Logger LOG = Logger.getLogger(_AttendeeType.class);

  public AttendeeType localInstanceIn(EOEditingContext editingContext) {
    AttendeeType localInstance = (AttendeeType)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String lable() {
    return (String) storedValueForKey("lable");
  }

  public void setLable(String value) {
    if (_AttendeeType.LOG.isDebugEnabled()) {
    	_AttendeeType.LOG.debug( "updating lable from " + lable() + " to " + value);
    }
    takeStoredValueForKey(value, "lable");
  }

  public NSArray<com.dyned.conf.eom.Attendee> attendees() {
    return (NSArray<com.dyned.conf.eom.Attendee>)storedValueForKey("attendees");
  }

  public NSArray<com.dyned.conf.eom.Attendee> attendees(EOQualifier qualifier) {
    return attendees(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.Attendee> attendees(EOQualifier qualifier, boolean fetch) {
    return attendees(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.Attendee> attendees(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.Attendee> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.Attendee.TYPE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.dyned.conf.eom.Attendee.fetchAttendees(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = attendees();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.Attendee>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.Attendee>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToAttendeesRelationship(com.dyned.conf.eom.Attendee object) {
    if (_AttendeeType.LOG.isDebugEnabled()) {
      _AttendeeType.LOG.debug("adding " + object + " to attendees relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "attendees");
  }

  public void removeFromAttendeesRelationship(com.dyned.conf.eom.Attendee object) {
    if (_AttendeeType.LOG.isDebugEnabled()) {
      _AttendeeType.LOG.debug("removing " + object + " from attendees relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "attendees");
  }

  public com.dyned.conf.eom.Attendee createAttendeesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Attendee");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "attendees");
    return (com.dyned.conf.eom.Attendee) eo;
  }

  public void deleteAttendeesRelationship(com.dyned.conf.eom.Attendee object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "attendees");
    editingContext().deleteObject(object);
  }

  public void deleteAllAttendeesRelationships() {
    Enumeration objects = attendees().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteAttendeesRelationship((com.dyned.conf.eom.Attendee)objects.nextElement());
    }
  }


  public static AttendeeType createAttendeeType(EOEditingContext editingContext, String lable
) {
    AttendeeType eo = (AttendeeType) EOUtilities.createAndInsertInstance(editingContext, _AttendeeType.ENTITY_NAME);    
		eo.setLable(lable);
    return eo;
  }

  public static NSArray<AttendeeType> fetchAllAttendeeTypes(EOEditingContext editingContext) {
    return _AttendeeType.fetchAllAttendeeTypes(editingContext, null);
  }

  public static NSArray<AttendeeType> fetchAllAttendeeTypes(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _AttendeeType.fetchAttendeeTypes(editingContext, null, sortOrderings);
  }

  public static NSArray<AttendeeType> fetchAttendeeTypes(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_AttendeeType.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<AttendeeType> eoObjects = (NSArray<AttendeeType>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static AttendeeType fetchAttendeeType(EOEditingContext editingContext, String keyName, Object value) {
    return _AttendeeType.fetchAttendeeType(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static AttendeeType fetchAttendeeType(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<AttendeeType> eoObjects = _AttendeeType.fetchAttendeeTypes(editingContext, qualifier, null);
    AttendeeType eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (AttendeeType)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one AttendeeType that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static AttendeeType fetchRequiredAttendeeType(EOEditingContext editingContext, String keyName, Object value) {
    return _AttendeeType.fetchRequiredAttendeeType(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static AttendeeType fetchRequiredAttendeeType(EOEditingContext editingContext, EOQualifier qualifier) {
    AttendeeType eoObject = _AttendeeType.fetchAttendeeType(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no AttendeeType that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static AttendeeType localInstanceIn(EOEditingContext editingContext, AttendeeType eo) {
    AttendeeType localInstance = (eo == null) ? null : (AttendeeType)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
