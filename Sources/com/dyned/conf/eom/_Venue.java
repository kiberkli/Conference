// $LastChangedRevision$ DO NOT EDIT.  Make changes to Venue.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Venue extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Venue";

	// Attributes
	public static final String DATE_CREATED_KEY = "dateCreated";
	public static final String DATE_END_KEY = "dateEnd";
	public static final String DATE_MODIFIED_KEY = "dateModified";
	public static final String DATE_START_KEY = "dateStart";
	public static final String DESCRIPTION_KEY = "description";
	public static final String DOCUMENTS_URL_KEY = "documentsURL";
	public static final String DOCUMENTS_URL_PASSWORD_KEY = "documentsURLPassword";
	public static final String DOCUMENTS_URL_USERNAME_KEY = "documentsURLUsername";
	public static final String FACILITY_NAME_KEY = "facilityName";
	public static final String FACILITY_PHONE_KEY = "facilityPhone";
	public static final String INACTIVE_KEY = "inactive";
	public static final String LABLE_KEY = "lable";
	public static final String MAP_ADDRESS_KEY = "mapAddress";
	public static final String SECRET_CODE_KEY = "secretCode";
	public static final String WEBPAGE_URL_KEY = "webpageURL";

	// Relationships
	public static final String ADMIN_KEY = "admin";
	public static final String ATTENDEES_KEY = "attendees";
	public static final String EVENTS_KEY = "events";
	public static final String INVITEES_KEY = "invitees";

  private static Logger LOG = Logger.getLogger(_Venue.class);

  public Venue localInstanceIn(EOEditingContext editingContext) {
    Venue localInstance = (Venue)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp dateCreated() {
    return (NSTimestamp) storedValueForKey("dateCreated");
  }

  public void setDateCreated(NSTimestamp value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating dateCreated from " + dateCreated() + " to " + value);
    }
    takeStoredValueForKey(value, "dateCreated");
  }

  public NSTimestamp dateEnd() {
    return (NSTimestamp) storedValueForKey("dateEnd");
  }

  public void setDateEnd(NSTimestamp value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating dateEnd from " + dateEnd() + " to " + value);
    }
    takeStoredValueForKey(value, "dateEnd");
  }

  public NSTimestamp dateModified() {
    return (NSTimestamp) storedValueForKey("dateModified");
  }

  public void setDateModified(NSTimestamp value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating dateModified from " + dateModified() + " to " + value);
    }
    takeStoredValueForKey(value, "dateModified");
  }

  public NSTimestamp dateStart() {
    return (NSTimestamp) storedValueForKey("dateStart");
  }

  public void setDateStart(NSTimestamp value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating dateStart from " + dateStart() + " to " + value);
    }
    takeStoredValueForKey(value, "dateStart");
  }

  public String description() {
    return (String) storedValueForKey("description");
  }

  public void setDescription(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating description from " + description() + " to " + value);
    }
    takeStoredValueForKey(value, "description");
  }

  public String documentsURL() {
    return (String) storedValueForKey("documentsURL");
  }

  public void setDocumentsURL(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating documentsURL from " + documentsURL() + " to " + value);
    }
    takeStoredValueForKey(value, "documentsURL");
  }

  public String documentsURLPassword() {
    return (String) storedValueForKey("documentsURLPassword");
  }

  public void setDocumentsURLPassword(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating documentsURLPassword from " + documentsURLPassword() + " to " + value);
    }
    takeStoredValueForKey(value, "documentsURLPassword");
  }

  public String documentsURLUsername() {
    return (String) storedValueForKey("documentsURLUsername");
  }

  public void setDocumentsURLUsername(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating documentsURLUsername from " + documentsURLUsername() + " to " + value);
    }
    takeStoredValueForKey(value, "documentsURLUsername");
  }

  public String facilityName() {
    return (String) storedValueForKey("facilityName");
  }

  public void setFacilityName(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating facilityName from " + facilityName() + " to " + value);
    }
    takeStoredValueForKey(value, "facilityName");
  }

  public String facilityPhone() {
    return (String) storedValueForKey("facilityPhone");
  }

  public void setFacilityPhone(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating facilityPhone from " + facilityPhone() + " to " + value);
    }
    takeStoredValueForKey(value, "facilityPhone");
  }

  public Boolean inactive() {
    return (Boolean) storedValueForKey("inactive");
  }

  public void setInactive(Boolean value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating inactive from " + inactive() + " to " + value);
    }
    takeStoredValueForKey(value, "inactive");
  }

  public String lable() {
    return (String) storedValueForKey("lable");
  }

  public void setLable(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating lable from " + lable() + " to " + value);
    }
    takeStoredValueForKey(value, "lable");
  }

  public String mapAddress() {
    return (String) storedValueForKey("mapAddress");
  }

  public void setMapAddress(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating mapAddress from " + mapAddress() + " to " + value);
    }
    takeStoredValueForKey(value, "mapAddress");
  }

  public String secretCode() {
    return (String) storedValueForKey("secretCode");
  }

  public void setSecretCode(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating secretCode from " + secretCode() + " to " + value);
    }
    takeStoredValueForKey(value, "secretCode");
  }

  public String webpageURL() {
    return (String) storedValueForKey("webpageURL");
  }

  public void setWebpageURL(String value) {
    if (_Venue.LOG.isDebugEnabled()) {
    	_Venue.LOG.debug( "updating webpageURL from " + webpageURL() + " to " + value);
    }
    takeStoredValueForKey(value, "webpageURL");
  }

  public com.dyned.conf.eom.Admin admin() {
    return (com.dyned.conf.eom.Admin)storedValueForKey("admin");
  }

  public void setAdminRelationship(com.dyned.conf.eom.Admin value) {
    if (_Venue.LOG.isDebugEnabled()) {
      _Venue.LOG.debug("updating admin from " + admin() + " to " + value);
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
    if (_Venue.LOG.isDebugEnabled()) {
      _Venue.LOG.debug("adding " + object + " to attendees relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "attendees");
  }

  public void removeFromAttendeesRelationship(com.dyned.conf.eom.Attendee object) {
    if (_Venue.LOG.isDebugEnabled()) {
      _Venue.LOG.debug("removing " + object + " from attendees relationship");
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

  public NSArray<com.dyned.conf.eom.VEvent> events() {
    return (NSArray<com.dyned.conf.eom.VEvent>)storedValueForKey("events");
  }

  public NSArray<com.dyned.conf.eom.VEvent> events(EOQualifier qualifier) {
    return events(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.VEvent> events(EOQualifier qualifier, boolean fetch) {
    return events(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.VEvent> events(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.VEvent> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.VEvent.VENUE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.dyned.conf.eom.VEvent.fetchVEvents(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = events();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.VEvent>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.VEvent>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToEventsRelationship(com.dyned.conf.eom.VEvent object) {
    if (_Venue.LOG.isDebugEnabled()) {
      _Venue.LOG.debug("adding " + object + " to events relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "events");
  }

  public void removeFromEventsRelationship(com.dyned.conf.eom.VEvent object) {
    if (_Venue.LOG.isDebugEnabled()) {
      _Venue.LOG.debug("removing " + object + " from events relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "events");
  }

  public com.dyned.conf.eom.VEvent createEventsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("VEvent");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "events");
    return (com.dyned.conf.eom.VEvent) eo;
  }

  public void deleteEventsRelationship(com.dyned.conf.eom.VEvent object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "events");
    editingContext().deleteObject(object);
  }

  public void deleteAllEventsRelationships() {
    Enumeration objects = events().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteEventsRelationship((com.dyned.conf.eom.VEvent)objects.nextElement());
    }
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
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.Invitee.VENUE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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
    if (_Venue.LOG.isDebugEnabled()) {
      _Venue.LOG.debug("adding " + object + " to invitees relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "invitees");
  }

  public void removeFromInviteesRelationship(com.dyned.conf.eom.Invitee object) {
    if (_Venue.LOG.isDebugEnabled()) {
      _Venue.LOG.debug("removing " + object + " from invitees relationship");
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


  public static Venue createVenue(EOEditingContext editingContext, NSTimestamp dateCreated
, NSTimestamp dateEnd
, NSTimestamp dateModified
, NSTimestamp dateStart
, String lable
, String mapAddress
, com.dyned.conf.eom.Admin admin) {
    Venue eo = (Venue) EOUtilities.createAndInsertInstance(editingContext, _Venue.ENTITY_NAME);    
		eo.setDateCreated(dateCreated);
		eo.setDateEnd(dateEnd);
		eo.setDateModified(dateModified);
		eo.setDateStart(dateStart);
		eo.setLable(lable);
		eo.setMapAddress(mapAddress);
    eo.setAdminRelationship(admin);
    return eo;
  }

  public static NSArray<Venue> fetchAllVenues(EOEditingContext editingContext) {
    return _Venue.fetchAllVenues(editingContext, null);
  }

  public static NSArray<Venue> fetchAllVenues(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Venue.fetchVenues(editingContext, null, sortOrderings);
  }

  public static NSArray<Venue> fetchVenues(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Venue.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Venue> eoObjects = (NSArray<Venue>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Venue fetchVenue(EOEditingContext editingContext, String keyName, Object value) {
    return _Venue.fetchVenue(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Venue fetchVenue(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Venue> eoObjects = _Venue.fetchVenues(editingContext, qualifier, null);
    Venue eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Venue)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Venue that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Venue fetchRequiredVenue(EOEditingContext editingContext, String keyName, Object value) {
    return _Venue.fetchRequiredVenue(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Venue fetchRequiredVenue(EOEditingContext editingContext, EOQualifier qualifier) {
    Venue eoObject = _Venue.fetchVenue(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Venue that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Venue localInstanceIn(EOEditingContext editingContext, Venue eo) {
    Venue localInstance = (eo == null) ? null : (Venue)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
