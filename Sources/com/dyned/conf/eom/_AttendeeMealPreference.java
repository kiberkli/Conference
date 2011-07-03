// $LastChangedRevision$ DO NOT EDIT.  Make changes to AttendeeMealPreference.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _AttendeeMealPreference extends  EOGenericRecord {
	public static final String ENTITY_NAME = "AttendeeMealPreference";

	// Attributes
	public static final String LABLE_KEY = "lable";

	// Relationships
	public static final String ATTENDEES_KEY = "attendees";

  private static Logger LOG = Logger.getLogger(_AttendeeMealPreference.class);

  public AttendeeMealPreference localInstanceIn(EOEditingContext editingContext) {
    AttendeeMealPreference localInstance = (AttendeeMealPreference)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String lable() {
    return (String) storedValueForKey("lable");
  }

  public void setLable(String value) {
    if (_AttendeeMealPreference.LOG.isDebugEnabled()) {
    	_AttendeeMealPreference.LOG.debug( "updating lable from " + lable() + " to " + value);
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
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.Attendee.MEAL_PREFERENCE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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
    if (_AttendeeMealPreference.LOG.isDebugEnabled()) {
      _AttendeeMealPreference.LOG.debug("adding " + object + " to attendees relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "attendees");
  }

  public void removeFromAttendeesRelationship(com.dyned.conf.eom.Attendee object) {
    if (_AttendeeMealPreference.LOG.isDebugEnabled()) {
      _AttendeeMealPreference.LOG.debug("removing " + object + " from attendees relationship");
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


  public static AttendeeMealPreference createAttendeeMealPreference(EOEditingContext editingContext, String lable
) {
    AttendeeMealPreference eo = (AttendeeMealPreference) EOUtilities.createAndInsertInstance(editingContext, _AttendeeMealPreference.ENTITY_NAME);    
		eo.setLable(lable);
    return eo;
  }

  public static NSArray<AttendeeMealPreference> fetchAllAttendeeMealPreferences(EOEditingContext editingContext) {
    return _AttendeeMealPreference.fetchAllAttendeeMealPreferences(editingContext, null);
  }

  public static NSArray<AttendeeMealPreference> fetchAllAttendeeMealPreferences(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _AttendeeMealPreference.fetchAttendeeMealPreferences(editingContext, null, sortOrderings);
  }

  public static NSArray<AttendeeMealPreference> fetchAttendeeMealPreferences(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_AttendeeMealPreference.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<AttendeeMealPreference> eoObjects = (NSArray<AttendeeMealPreference>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static AttendeeMealPreference fetchAttendeeMealPreference(EOEditingContext editingContext, String keyName, Object value) {
    return _AttendeeMealPreference.fetchAttendeeMealPreference(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static AttendeeMealPreference fetchAttendeeMealPreference(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<AttendeeMealPreference> eoObjects = _AttendeeMealPreference.fetchAttendeeMealPreferences(editingContext, qualifier, null);
    AttendeeMealPreference eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (AttendeeMealPreference)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one AttendeeMealPreference that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static AttendeeMealPreference fetchRequiredAttendeeMealPreference(EOEditingContext editingContext, String keyName, Object value) {
    return _AttendeeMealPreference.fetchRequiredAttendeeMealPreference(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static AttendeeMealPreference fetchRequiredAttendeeMealPreference(EOEditingContext editingContext, EOQualifier qualifier) {
    AttendeeMealPreference eoObject = _AttendeeMealPreference.fetchAttendeeMealPreference(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no AttendeeMealPreference that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static AttendeeMealPreference localInstanceIn(EOEditingContext editingContext, AttendeeMealPreference eo) {
    AttendeeMealPreference localInstance = (eo == null) ? null : (AttendeeMealPreference)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
