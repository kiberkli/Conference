// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to Admin.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Admin extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Admin";

	// Attributes
	public static final String DATE_CREATED_KEY = "dateCreated";
	public static final String DATE_LAST_LOGIN_KEY = "dateLastLogin";
	public static final String DATE_MODIFIED_KEY = "dateModified";
	public static final String EMAIL_ADDRESS_KEY = "emailAddress";
	public static final String FULL_NAME_KEY = "fullName";
	public static final String PASSWORD_KEY = "password";
	public static final String USERNAME_KEY = "username";

	// Relationships
	public static final String INVITEES_KEY = "invitees";
	public static final String VENUES_KEY = "venues";

  private static Logger LOG = Logger.getLogger(_Admin.class);

  public Admin localInstanceIn(EOEditingContext editingContext) {
    Admin localInstance = (Admin)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp dateCreated() {
    return (NSTimestamp) storedValueForKey("dateCreated");
  }

  public void setDateCreated(NSTimestamp value) {
    if (_Admin.LOG.isDebugEnabled()) {
    	_Admin.LOG.debug( "updating dateCreated from " + dateCreated() + " to " + value);
    }
    takeStoredValueForKey(value, "dateCreated");
  }

  public NSTimestamp dateLastLogin() {
    return (NSTimestamp) storedValueForKey("dateLastLogin");
  }

  public void setDateLastLogin(NSTimestamp value) {
    if (_Admin.LOG.isDebugEnabled()) {
    	_Admin.LOG.debug( "updating dateLastLogin from " + dateLastLogin() + " to " + value);
    }
    takeStoredValueForKey(value, "dateLastLogin");
  }

  public NSTimestamp dateModified() {
    return (NSTimestamp) storedValueForKey("dateModified");
  }

  public void setDateModified(NSTimestamp value) {
    if (_Admin.LOG.isDebugEnabled()) {
    	_Admin.LOG.debug( "updating dateModified from " + dateModified() + " to " + value);
    }
    takeStoredValueForKey(value, "dateModified");
  }

  public String emailAddress() {
    return (String) storedValueForKey("emailAddress");
  }

  public void setEmailAddress(String value) {
    if (_Admin.LOG.isDebugEnabled()) {
    	_Admin.LOG.debug( "updating emailAddress from " + emailAddress() + " to " + value);
    }
    takeStoredValueForKey(value, "emailAddress");
  }

  public String fullName() {
    return (String) storedValueForKey("fullName");
  }

  public void setFullName(String value) {
    if (_Admin.LOG.isDebugEnabled()) {
    	_Admin.LOG.debug( "updating fullName from " + fullName() + " to " + value);
    }
    takeStoredValueForKey(value, "fullName");
  }

  public String password() {
    return (String) storedValueForKey("password");
  }

  public void setPassword(String value) {
    if (_Admin.LOG.isDebugEnabled()) {
    	_Admin.LOG.debug( "updating password from " + password() + " to " + value);
    }
    takeStoredValueForKey(value, "password");
  }

  public String username() {
    return (String) storedValueForKey("username");
  }

  public void setUsername(String value) {
    if (_Admin.LOG.isDebugEnabled()) {
    	_Admin.LOG.debug( "updating username from " + username() + " to " + value);
    }
    takeStoredValueForKey(value, "username");
  }

  public NSArray<com.dyned.conf.eom.Invitee> invitees() {
    return (NSArray<com.dyned.conf.eom.Invitee>)storedValueForKey("invitees");
  }

  public NSArray<com.dyned.conf.eom.Invitee> invitees(EOQualifier qualifier) {
    return invitees(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.Invitee> invitees(EOQualifier qualifier, boolean fetch) {
    return invitees(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.Invitee> invitees(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.Invitee> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.Invitee.ADMIN_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.dyned.conf.eom.Invitee.fetchInvitees(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = invitees();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.Invitee>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.Invitee>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToInviteesRelationship(com.dyned.conf.eom.Invitee object) {
    if (_Admin.LOG.isDebugEnabled()) {
      _Admin.LOG.debug("adding " + object + " to invitees relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "invitees");
  }

  public void removeFromInviteesRelationship(com.dyned.conf.eom.Invitee object) {
    if (_Admin.LOG.isDebugEnabled()) {
      _Admin.LOG.debug("removing " + object + " from invitees relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "invitees");
  }

  public com.dyned.conf.eom.Invitee createInviteesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Invitee");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "invitees");
    return (com.dyned.conf.eom.Invitee) eo;
  }

  public void deleteInviteesRelationship(com.dyned.conf.eom.Invitee object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "invitees");
    editingContext().deleteObject(object);
  }

  public void deleteAllInviteesRelationships() {
    Enumeration objects = invitees().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteInviteesRelationship((com.dyned.conf.eom.Invitee)objects.nextElement());
    }
  }

  public NSArray<com.dyned.conf.eom.Venue> venues() {
    return (NSArray<com.dyned.conf.eom.Venue>)storedValueForKey("venues");
  }

  public NSArray<com.dyned.conf.eom.Venue> venues(EOQualifier qualifier) {
    return venues(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.Venue> venues(EOQualifier qualifier, boolean fetch) {
    return venues(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.Venue> venues(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.Venue> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.Venue.ADMIN_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.dyned.conf.eom.Venue.fetchVenues(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = venues();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.Venue>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.Venue>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToVenuesRelationship(com.dyned.conf.eom.Venue object) {
    if (_Admin.LOG.isDebugEnabled()) {
      _Admin.LOG.debug("adding " + object + " to venues relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "venues");
  }

  public void removeFromVenuesRelationship(com.dyned.conf.eom.Venue object) {
    if (_Admin.LOG.isDebugEnabled()) {
      _Admin.LOG.debug("removing " + object + " from venues relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "venues");
  }

  public com.dyned.conf.eom.Venue createVenuesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Venue");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "venues");
    return (com.dyned.conf.eom.Venue) eo;
  }

  public void deleteVenuesRelationship(com.dyned.conf.eom.Venue object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "venues");
    editingContext().deleteObject(object);
  }

  public void deleteAllVenuesRelationships() {
    Enumeration objects = venues().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteVenuesRelationship((com.dyned.conf.eom.Venue)objects.nextElement());
    }
  }


  public static Admin createAdmin(EOEditingContext editingContext, NSTimestamp dateCreated
, NSTimestamp dateModified
, String emailAddress
, String fullName
, String password
, String username
) {
    Admin eo = (Admin) EOUtilities.createAndInsertInstance(editingContext, _Admin.ENTITY_NAME);    
		eo.setDateCreated(dateCreated);
		eo.setDateModified(dateModified);
		eo.setEmailAddress(emailAddress);
		eo.setFullName(fullName);
		eo.setPassword(password);
		eo.setUsername(username);
    return eo;
  }

  public static NSArray<Admin> fetchAllAdmins(EOEditingContext editingContext) {
    return _Admin.fetchAllAdmins(editingContext, null);
  }

  public static NSArray<Admin> fetchAllAdmins(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Admin.fetchAdmins(editingContext, null, sortOrderings);
  }

  public static NSArray<Admin> fetchAdmins(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Admin.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Admin> eoObjects = (NSArray<Admin>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Admin fetchAdmin(EOEditingContext editingContext, String keyName, Object value) {
    return _Admin.fetchAdmin(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Admin fetchAdmin(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Admin> eoObjects = _Admin.fetchAdmins(editingContext, qualifier, null);
    Admin eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Admin)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Admin that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Admin fetchRequiredAdmin(EOEditingContext editingContext, String keyName, Object value) {
    return _Admin.fetchRequiredAdmin(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Admin fetchRequiredAdmin(EOEditingContext editingContext, EOQualifier qualifier) {
    Admin eoObject = _Admin.fetchAdmin(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Admin that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Admin localInstanceIn(EOEditingContext editingContext, Admin eo) {
    Admin localInstance = (eo == null) ? null : (Admin)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
