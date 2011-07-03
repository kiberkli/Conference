// $LastChangedRevision$ DO NOT EDIT.  Make changes to AttendeeFunction.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _AttendeeFunction extends  EOGenericRecord {
	public static final String ENTITY_NAME = "AttendeeFunction";

	// Attributes
	public static final String LABLE_KEY = "lable";

	// Relationships
	public static final String ATTENDEES_KEY = "attendees";

  private static Logger LOG = Logger.getLogger(_AttendeeFunction.class);

  public AttendeeFunction localInstanceIn(EOEditingContext editingContext) {
    AttendeeFunction localInstance = (AttendeeFunction)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String lable() {
    return (String) storedValueForKey("lable");
  }

  public void setLable(String value) {
    if (_AttendeeFunction.LOG.isDebugEnabled()) {
    	_AttendeeFunction.LOG.debug( "updating lable from " + lable() + " to " + value);
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
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.Attendee.FUNCTION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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
    if (_AttendeeFunction.LOG.isDebugEnabled()) {
      _AttendeeFunction.LOG.debug("adding " + object + " to attendees relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "attendees");
  }

  public void removeFromAttendeesRelationship(com.dyned.conf.eom.Attendee object) {
    if (_AttendeeFunction.LOG.isDebugEnabled()) {
      _AttendeeFunction.LOG.debug("removing " + object + " from attendees relationship");
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


  public static AttendeeFunction createAttendeeFunction(EOEditingContext editingContext, String lable
) {
    AttendeeFunction eo = (AttendeeFunction) EOUtilities.createAndInsertInstance(editingContext, _AttendeeFunction.ENTITY_NAME);    
		eo.setLable(lable);
    return eo;
  }

  public static NSArray<AttendeeFunction> fetchAllAttendeeFunctions(EOEditingContext editingContext) {
    return _AttendeeFunction.fetchAllAttendeeFunctions(editingContext, null);
  }

  public static NSArray<AttendeeFunction> fetchAllAttendeeFunctions(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _AttendeeFunction.fetchAttendeeFunctions(editingContext, null, sortOrderings);
  }

  public static NSArray<AttendeeFunction> fetchAttendeeFunctions(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_AttendeeFunction.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<AttendeeFunction> eoObjects = (NSArray<AttendeeFunction>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static AttendeeFunction fetchAttendeeFunction(EOEditingContext editingContext, String keyName, Object value) {
    return _AttendeeFunction.fetchAttendeeFunction(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static AttendeeFunction fetchAttendeeFunction(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<AttendeeFunction> eoObjects = _AttendeeFunction.fetchAttendeeFunctions(editingContext, qualifier, null);
    AttendeeFunction eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (AttendeeFunction)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one AttendeeFunction that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static AttendeeFunction fetchRequiredAttendeeFunction(EOEditingContext editingContext, String keyName, Object value) {
    return _AttendeeFunction.fetchRequiredAttendeeFunction(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static AttendeeFunction fetchRequiredAttendeeFunction(EOEditingContext editingContext, EOQualifier qualifier) {
    AttendeeFunction eoObject = _AttendeeFunction.fetchAttendeeFunction(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no AttendeeFunction that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static AttendeeFunction localInstanceIn(EOEditingContext editingContext, AttendeeFunction eo) {
    AttendeeFunction localInstance = (eo == null) ? null : (AttendeeFunction)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
