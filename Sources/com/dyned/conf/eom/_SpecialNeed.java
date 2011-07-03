// $LastChangedRevision$ DO NOT EDIT.  Make changes to SpecialNeed.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _SpecialNeed extends  EOGenericRecord {
	public static final String ENTITY_NAME = "SpecialNeed";

	// Attributes
	public static final String LABLE_KEY = "lable";

	// Relationships
	public static final String ATTENDEES_KEY = "attendees";

  private static Logger LOG = Logger.getLogger(_SpecialNeed.class);

  public SpecialNeed localInstanceIn(EOEditingContext editingContext) {
    SpecialNeed localInstance = (SpecialNeed)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String lable() {
    return (String) storedValueForKey("lable");
  }

  public void setLable(String value) {
    if (_SpecialNeed.LOG.isDebugEnabled()) {
    	_SpecialNeed.LOG.debug( "updating lable from " + lable() + " to " + value);
    }
    takeStoredValueForKey(value, "lable");
  }

  public NSArray<com.dyned.conf.eom.Attendee> attendees() {
    return (NSArray<com.dyned.conf.eom.Attendee>)storedValueForKey("attendees");
  }

  public NSArray<com.dyned.conf.eom.Attendee> attendees(EOQualifier qualifier) {
    return attendees(qualifier, null);
  }

  public NSArray<com.dyned.conf.eom.Attendee> attendees(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.dyned.conf.eom.Attendee> results;
      results = attendees();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.Attendee>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.Attendee>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToAttendeesRelationship(com.dyned.conf.eom.Attendee object) {
    if (_SpecialNeed.LOG.isDebugEnabled()) {
      _SpecialNeed.LOG.debug("adding " + object + " to attendees relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "attendees");
  }

  public void removeFromAttendeesRelationship(com.dyned.conf.eom.Attendee object) {
    if (_SpecialNeed.LOG.isDebugEnabled()) {
      _SpecialNeed.LOG.debug("removing " + object + " from attendees relationship");
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


  public static SpecialNeed createSpecialNeed(EOEditingContext editingContext, String lable
) {
    SpecialNeed eo = (SpecialNeed) EOUtilities.createAndInsertInstance(editingContext, _SpecialNeed.ENTITY_NAME);    
		eo.setLable(lable);
    return eo;
  }

  public static NSArray<SpecialNeed> fetchAllSpecialNeeds(EOEditingContext editingContext) {
    return _SpecialNeed.fetchAllSpecialNeeds(editingContext, null);
  }

  public static NSArray<SpecialNeed> fetchAllSpecialNeeds(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _SpecialNeed.fetchSpecialNeeds(editingContext, null, sortOrderings);
  }

  public static NSArray<SpecialNeed> fetchSpecialNeeds(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_SpecialNeed.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<SpecialNeed> eoObjects = (NSArray<SpecialNeed>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static SpecialNeed fetchSpecialNeed(EOEditingContext editingContext, String keyName, Object value) {
    return _SpecialNeed.fetchSpecialNeed(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static SpecialNeed fetchSpecialNeed(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<SpecialNeed> eoObjects = _SpecialNeed.fetchSpecialNeeds(editingContext, qualifier, null);
    SpecialNeed eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (SpecialNeed)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one SpecialNeed that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static SpecialNeed fetchRequiredSpecialNeed(EOEditingContext editingContext, String keyName, Object value) {
    return _SpecialNeed.fetchRequiredSpecialNeed(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static SpecialNeed fetchRequiredSpecialNeed(EOEditingContext editingContext, EOQualifier qualifier) {
    SpecialNeed eoObject = _SpecialNeed.fetchSpecialNeed(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no SpecialNeed that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static SpecialNeed localInstanceIn(EOEditingContext editingContext, SpecialNeed eo) {
    SpecialNeed localInstance = (eo == null) ? null : (SpecialNeed)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
