// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to Invitee.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Invitee extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Invitee";

	// Attributes
	public static final String EMAIL_ADDRESS_KEY = "emailAddress";
	public static final String FREE_FORM_NAME_KEY = "freeFormName";

	// Relationships
	public static final String ADMIN_KEY = "admin";
	public static final String VENUE_KEY = "venue";

  private static Logger LOG = Logger.getLogger(_Invitee.class);

  public Invitee localInstanceIn(EOEditingContext editingContext) {
    Invitee localInstance = (Invitee)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String emailAddress() {
    return (String) storedValueForKey("emailAddress");
  }

  public void setEmailAddress(String value) {
    if (_Invitee.LOG.isDebugEnabled()) {
    	_Invitee.LOG.debug( "updating emailAddress from " + emailAddress() + " to " + value);
    }
    takeStoredValueForKey(value, "emailAddress");
  }

  public String freeFormName() {
    return (String) storedValueForKey("freeFormName");
  }

  public void setFreeFormName(String value) {
    if (_Invitee.LOG.isDebugEnabled()) {
    	_Invitee.LOG.debug( "updating freeFormName from " + freeFormName() + " to " + value);
    }
    takeStoredValueForKey(value, "freeFormName");
  }

  public com.dyned.conf.eom.Admin admin() {
    return (com.dyned.conf.eom.Admin)storedValueForKey("admin");
  }

  public void setAdminRelationship(com.dyned.conf.eom.Admin value) {
    if (_Invitee.LOG.isDebugEnabled()) {
      _Invitee.LOG.debug("updating admin from " + admin() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.Admin oldValue = admin();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "admin");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "admin");
    }
  }
  
  public com.dyned.conf.eom.Venue venue() {
    return (com.dyned.conf.eom.Venue)storedValueForKey("venue");
  }

  public void setVenueRelationship(com.dyned.conf.eom.Venue value) {
    if (_Invitee.LOG.isDebugEnabled()) {
      _Invitee.LOG.debug("updating venue from " + venue() + " to " + value);
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
  

  public static Invitee createInvitee(EOEditingContext editingContext, String emailAddress
, String freeFormName
, com.dyned.conf.eom.Admin admin, com.dyned.conf.eom.Venue venue) {
    Invitee eo = (Invitee) EOUtilities.createAndInsertInstance(editingContext, _Invitee.ENTITY_NAME);    
		eo.setEmailAddress(emailAddress);
		eo.setFreeFormName(freeFormName);
    eo.setAdminRelationship(admin);
    eo.setVenueRelationship(venue);
    return eo;
  }

  public static NSArray<Invitee> fetchAllInvitees(EOEditingContext editingContext) {
    return _Invitee.fetchAllInvitees(editingContext, null);
  }

  public static NSArray<Invitee> fetchAllInvitees(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Invitee.fetchInvitees(editingContext, null, sortOrderings);
  }

  public static NSArray<Invitee> fetchInvitees(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Invitee.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Invitee> eoObjects = (NSArray<Invitee>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Invitee fetchInvitee(EOEditingContext editingContext, String keyName, Object value) {
    return _Invitee.fetchInvitee(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Invitee fetchInvitee(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Invitee> eoObjects = _Invitee.fetchInvitees(editingContext, qualifier, null);
    Invitee eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Invitee)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Invitee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Invitee fetchRequiredInvitee(EOEditingContext editingContext, String keyName, Object value) {
    return _Invitee.fetchRequiredInvitee(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Invitee fetchRequiredInvitee(EOEditingContext editingContext, EOQualifier qualifier) {
    Invitee eoObject = _Invitee.fetchInvitee(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Invitee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Invitee localInstanceIn(EOEditingContext editingContext, Invitee eo) {
    Invitee localInstance = (eo == null) ? null : (Invitee)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
