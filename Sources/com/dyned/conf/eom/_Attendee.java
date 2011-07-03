// $LastChangedRevision$ DO NOT EDIT.  Make changes to Attendee.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Attendee extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Attendee";

	// Attributes
	public static final String ADDRESS_CITY_KEY = "addressCity";
	public static final String ADDRESS_COUNTRY_KEY = "addressCountry";
	public static final String ADDRESS_POSTAL_CODE_KEY = "addressPostalCode";
	public static final String ADDRESS_STATE_KEY = "addressState";
	public static final String ADDRESS_STREET1_KEY = "addressStreet1";
	public static final String ADDRESS_STREET2_KEY = "addressStreet2";
	public static final String COMPANY_NAME_KEY = "companyName";
	public static final String DATE_LAST_EDITED_KEY = "dateLastEdited";
	public static final String DATE_LAST_VISIT_KEY = "dateLastVisit";
	public static final String DATE_REGISTERED_KEY = "dateRegistered";
	public static final String FAX_KEY = "fax";
	public static final String JOB_TITLE_KEY = "jobTitle";
	public static final String NAME_FAMILY_KEY = "nameFamily";
	public static final String NAME_GIVEN_KEY = "nameGiven";
	public static final String PHONE_KEY = "phone";
	public static final String PW_HASH_CODE_KEY = "pwHashCode";
	public static final String SALUTATION_KEY = "salutation";
	public static final String SPECIAL_NEEDS_NOTES_KEY = "specialNeedsNotes";
	public static final String USER_EMAIL_ADDRESS_KEY = "userEmailAddress";
	public static final String USER_PASSWORD_KEY = "userPassword";

	// Relationships
	public static final String FUNCTION_KEY = "function";
	public static final String GROUP_MEMBERS_KEY = "groupMembers";
	public static final String HOTEL_INFORMATIONS_KEY = "hotelInformations";
	public static final String MEAL_PREFERENCE_KEY = "mealPreference";
	public static final String PASSPORT_INFORMATION_KEY = "passportInformation";
	public static final String REGISTRANT_KEY = "registrant";
	public static final String SELECTED_EVENTS_KEY = "selectedEvents";
	public static final String SPECIAL_NEEDS_KEY = "specialNeeds";
	public static final String TRAVEL_INFORMATIONS_KEY = "travelInformations";
	public static final String TYPE_KEY = "type";
	public static final String VENUES_KEY = "venues";

  private static Logger LOG = Logger.getLogger(_Attendee.class);

  public Attendee localInstanceIn(EOEditingContext editingContext) {
    Attendee localInstance = (Attendee)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String addressCity() {
    return (String) storedValueForKey("addressCity");
  }

  public void setAddressCity(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating addressCity from " + addressCity() + " to " + value);
    }
    takeStoredValueForKey(value, "addressCity");
  }

  public String addressCountry() {
    return (String) storedValueForKey("addressCountry");
  }

  public void setAddressCountry(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating addressCountry from " + addressCountry() + " to " + value);
    }
    takeStoredValueForKey(value, "addressCountry");
  }

  public String addressPostalCode() {
    return (String) storedValueForKey("addressPostalCode");
  }

  public void setAddressPostalCode(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating addressPostalCode from " + addressPostalCode() + " to " + value);
    }
    takeStoredValueForKey(value, "addressPostalCode");
  }

  public String addressState() {
    return (String) storedValueForKey("addressState");
  }

  public void setAddressState(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating addressState from " + addressState() + " to " + value);
    }
    takeStoredValueForKey(value, "addressState");
  }

  public String addressStreet1() {
    return (String) storedValueForKey("addressStreet1");
  }

  public void setAddressStreet1(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating addressStreet1 from " + addressStreet1() + " to " + value);
    }
    takeStoredValueForKey(value, "addressStreet1");
  }

  public String addressStreet2() {
    return (String) storedValueForKey("addressStreet2");
  }

  public void setAddressStreet2(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating addressStreet2 from " + addressStreet2() + " to " + value);
    }
    takeStoredValueForKey(value, "addressStreet2");
  }

  public String companyName() {
    return (String) storedValueForKey("companyName");
  }

  public void setCompanyName(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating companyName from " + companyName() + " to " + value);
    }
    takeStoredValueForKey(value, "companyName");
  }

  public NSTimestamp dateLastEdited() {
    return (NSTimestamp) storedValueForKey("dateLastEdited");
  }

  public void setDateLastEdited(NSTimestamp value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating dateLastEdited from " + dateLastEdited() + " to " + value);
    }
    takeStoredValueForKey(value, "dateLastEdited");
  }

  public NSTimestamp dateLastVisit() {
    return (NSTimestamp) storedValueForKey("dateLastVisit");
  }

  public void setDateLastVisit(NSTimestamp value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating dateLastVisit from " + dateLastVisit() + " to " + value);
    }
    takeStoredValueForKey(value, "dateLastVisit");
  }

  public NSTimestamp dateRegistered() {
    return (NSTimestamp) storedValueForKey("dateRegistered");
  }

  public void setDateRegistered(NSTimestamp value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating dateRegistered from " + dateRegistered() + " to " + value);
    }
    takeStoredValueForKey(value, "dateRegistered");
  }

  public String fax() {
    return (String) storedValueForKey("fax");
  }

  public void setFax(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating fax from " + fax() + " to " + value);
    }
    takeStoredValueForKey(value, "fax");
  }

  public String jobTitle() {
    return (String) storedValueForKey("jobTitle");
  }

  public void setJobTitle(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating jobTitle from " + jobTitle() + " to " + value);
    }
    takeStoredValueForKey(value, "jobTitle");
  }

  public String nameFamily() {
    return (String) storedValueForKey("nameFamily");
  }

  public void setNameFamily(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating nameFamily from " + nameFamily() + " to " + value);
    }
    takeStoredValueForKey(value, "nameFamily");
  }

  public String nameGiven() {
    return (String) storedValueForKey("nameGiven");
  }

  public void setNameGiven(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating nameGiven from " + nameGiven() + " to " + value);
    }
    takeStoredValueForKey(value, "nameGiven");
  }

  public String phone() {
    return (String) storedValueForKey("phone");
  }

  public void setPhone(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating phone from " + phone() + " to " + value);
    }
    takeStoredValueForKey(value, "phone");
  }

  public Integer pwHashCode() {
    return (Integer) storedValueForKey("pwHashCode");
  }

  public void setPwHashCode(Integer value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating pwHashCode from " + pwHashCode() + " to " + value);
    }
    takeStoredValueForKey(value, "pwHashCode");
  }

  public String salutation() {
    return (String) storedValueForKey("salutation");
  }

  public void setSalutation(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating salutation from " + salutation() + " to " + value);
    }
    takeStoredValueForKey(value, "salutation");
  }

  public String specialNeedsNotes() {
    return (String) storedValueForKey("specialNeedsNotes");
  }

  public void setSpecialNeedsNotes(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating specialNeedsNotes from " + specialNeedsNotes() + " to " + value);
    }
    takeStoredValueForKey(value, "specialNeedsNotes");
  }

  public String userEmailAddress() {
    return (String) storedValueForKey("userEmailAddress");
  }

  public void setUserEmailAddress(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating userEmailAddress from " + userEmailAddress() + " to " + value);
    }
    takeStoredValueForKey(value, "userEmailAddress");
  }

  public String userPassword() {
    return (String) storedValueForKey("userPassword");
  }

  public void setUserPassword(String value) {
    if (_Attendee.LOG.isDebugEnabled()) {
    	_Attendee.LOG.debug( "updating userPassword from " + userPassword() + " to " + value);
    }
    takeStoredValueForKey(value, "userPassword");
  }

  public com.dyned.conf.eom.AttendeeFunction function() {
    return (com.dyned.conf.eom.AttendeeFunction)storedValueForKey("function");
  }

  public void setFunctionRelationship(com.dyned.conf.eom.AttendeeFunction value) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("updating function from " + function() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.AttendeeFunction oldValue = function();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "function");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "function");
    }
  }
  
  public com.dyned.conf.eom.AttendeeMealPreference mealPreference() {
    return (com.dyned.conf.eom.AttendeeMealPreference)storedValueForKey("mealPreference");
  }

  public void setMealPreferenceRelationship(com.dyned.conf.eom.AttendeeMealPreference value) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("updating mealPreference from " + mealPreference() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.AttendeeMealPreference oldValue = mealPreference();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "mealPreference");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "mealPreference");
    }
  }
  
  public com.dyned.conf.eom.PassportInformation passportInformation() {
    return (com.dyned.conf.eom.PassportInformation)storedValueForKey("passportInformation");
  }

  public void setPassportInformationRelationship(com.dyned.conf.eom.PassportInformation value) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("updating passportInformation from " + passportInformation() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.PassportInformation oldValue = passportInformation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "passportInformation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "passportInformation");
    }
  }
  
  public com.dyned.conf.eom.Attendee registrant() {
    return (com.dyned.conf.eom.Attendee)storedValueForKey("registrant");
  }

  public void setRegistrantRelationship(com.dyned.conf.eom.Attendee value) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("updating registrant from " + registrant() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.Attendee oldValue = registrant();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "registrant");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "registrant");
    }
  }
  
  public com.dyned.conf.eom.AttendeeType type() {
    return (com.dyned.conf.eom.AttendeeType)storedValueForKey("type");
  }

  public void setTypeRelationship(com.dyned.conf.eom.AttendeeType value) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("updating type from " + type() + " to " + value);
    }
    if (value == null) {
    	com.dyned.conf.eom.AttendeeType oldValue = type();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "type");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "type");
    }
  }
  
  public NSArray<com.dyned.conf.eom.Attendee> groupMembers() {
    return (NSArray<com.dyned.conf.eom.Attendee>)storedValueForKey("groupMembers");
  }

  public NSArray<com.dyned.conf.eom.Attendee> groupMembers(EOQualifier qualifier) {
    return groupMembers(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.Attendee> groupMembers(EOQualifier qualifier, boolean fetch) {
    return groupMembers(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.Attendee> groupMembers(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.Attendee> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.Attendee.REGISTRANT_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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
      results = groupMembers();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.Attendee>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.Attendee>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToGroupMembersRelationship(com.dyned.conf.eom.Attendee object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("adding " + object + " to groupMembers relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "groupMembers");
  }

  public void removeFromGroupMembersRelationship(com.dyned.conf.eom.Attendee object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("removing " + object + " from groupMembers relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "groupMembers");
  }

  public com.dyned.conf.eom.Attendee createGroupMembersRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Attendee");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "groupMembers");
    return (com.dyned.conf.eom.Attendee) eo;
  }

  public void deleteGroupMembersRelationship(com.dyned.conf.eom.Attendee object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "groupMembers");
    editingContext().deleteObject(object);
  }

  public void deleteAllGroupMembersRelationships() {
    Enumeration objects = groupMembers().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteGroupMembersRelationship((com.dyned.conf.eom.Attendee)objects.nextElement());
    }
  }

  public NSArray<com.dyned.conf.eom.HotelInformation> hotelInformations() {
    return (NSArray<com.dyned.conf.eom.HotelInformation>)storedValueForKey("hotelInformations");
  }

  public NSArray<com.dyned.conf.eom.HotelInformation> hotelInformations(EOQualifier qualifier) {
    return hotelInformations(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.HotelInformation> hotelInformations(EOQualifier qualifier, boolean fetch) {
    return hotelInformations(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.HotelInformation> hotelInformations(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.HotelInformation> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.HotelInformation.ATTENDEE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.dyned.conf.eom.HotelInformation.fetchHotelInformations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = hotelInformations();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.HotelInformation>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.HotelInformation>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToHotelInformationsRelationship(com.dyned.conf.eom.HotelInformation object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("adding " + object + " to hotelInformations relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "hotelInformations");
  }

  public void removeFromHotelInformationsRelationship(com.dyned.conf.eom.HotelInformation object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("removing " + object + " from hotelInformations relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "hotelInformations");
  }

  public com.dyned.conf.eom.HotelInformation createHotelInformationsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("HotelInformation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "hotelInformations");
    return (com.dyned.conf.eom.HotelInformation) eo;
  }

  public void deleteHotelInformationsRelationship(com.dyned.conf.eom.HotelInformation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "hotelInformations");
    editingContext().deleteObject(object);
  }

  public void deleteAllHotelInformationsRelationships() {
    Enumeration objects = hotelInformations().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteHotelInformationsRelationship((com.dyned.conf.eom.HotelInformation)objects.nextElement());
    }
  }

  public NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> selectedEvents() {
    return (NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent>)storedValueForKey("selectedEvents");
  }

  public NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> selectedEvents(EOQualifier qualifier) {
    return selectedEvents(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> selectedEvents(EOQualifier qualifier, boolean fetch) {
    return selectedEvents(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> selectedEvents(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.AttendeeSelectedVEvent.ATTENDEE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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
      results = selectedEvents();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.AttendeeSelectedVEvent>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToSelectedEventsRelationship(com.dyned.conf.eom.AttendeeSelectedVEvent object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("adding " + object + " to selectedEvents relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "selectedEvents");
  }

  public void removeFromSelectedEventsRelationship(com.dyned.conf.eom.AttendeeSelectedVEvent object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("removing " + object + " from selectedEvents relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "selectedEvents");
  }

  public com.dyned.conf.eom.AttendeeSelectedVEvent createSelectedEventsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AttendeeSelectedVEvent");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "selectedEvents");
    return (com.dyned.conf.eom.AttendeeSelectedVEvent) eo;
  }

  public void deleteSelectedEventsRelationship(com.dyned.conf.eom.AttendeeSelectedVEvent object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "selectedEvents");
    editingContext().deleteObject(object);
  }

  public void deleteAllSelectedEventsRelationships() {
    Enumeration objects = selectedEvents().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteSelectedEventsRelationship((com.dyned.conf.eom.AttendeeSelectedVEvent)objects.nextElement());
    }
  }

  public NSArray<com.dyned.conf.eom.SpecialNeed> specialNeeds() {
    return (NSArray<com.dyned.conf.eom.SpecialNeed>)storedValueForKey("specialNeeds");
  }

  public NSArray<com.dyned.conf.eom.SpecialNeed> specialNeeds(EOQualifier qualifier) {
    return specialNeeds(qualifier, null);
  }

  public NSArray<com.dyned.conf.eom.SpecialNeed> specialNeeds(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.dyned.conf.eom.SpecialNeed> results;
      results = specialNeeds();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.SpecialNeed>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.SpecialNeed>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToSpecialNeedsRelationship(com.dyned.conf.eom.SpecialNeed object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("adding " + object + " to specialNeeds relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "specialNeeds");
  }

  public void removeFromSpecialNeedsRelationship(com.dyned.conf.eom.SpecialNeed object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("removing " + object + " from specialNeeds relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "specialNeeds");
  }

  public com.dyned.conf.eom.SpecialNeed createSpecialNeedsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("SpecialNeed");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "specialNeeds");
    return (com.dyned.conf.eom.SpecialNeed) eo;
  }

  public void deleteSpecialNeedsRelationship(com.dyned.conf.eom.SpecialNeed object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "specialNeeds");
    editingContext().deleteObject(object);
  }

  public void deleteAllSpecialNeedsRelationships() {
    Enumeration objects = specialNeeds().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteSpecialNeedsRelationship((com.dyned.conf.eom.SpecialNeed)objects.nextElement());
    }
  }

  public NSArray<com.dyned.conf.eom.TravelInformation> travelInformations() {
    return (NSArray<com.dyned.conf.eom.TravelInformation>)storedValueForKey("travelInformations");
  }

  public NSArray<com.dyned.conf.eom.TravelInformation> travelInformations(EOQualifier qualifier) {
    return travelInformations(qualifier, null, false);
  }

  public NSArray<com.dyned.conf.eom.TravelInformation> travelInformations(EOQualifier qualifier, boolean fetch) {
    return travelInformations(qualifier, null, fetch);
  }

  public NSArray<com.dyned.conf.eom.TravelInformation> travelInformations(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.dyned.conf.eom.TravelInformation> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.dyned.conf.eom.TravelInformation.ATTENDEE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.dyned.conf.eom.TravelInformation.fetchTravelInformations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = travelInformations();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.TravelInformation>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.TravelInformation>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTravelInformationsRelationship(com.dyned.conf.eom.TravelInformation object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("adding " + object + " to travelInformations relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "travelInformations");
  }

  public void removeFromTravelInformationsRelationship(com.dyned.conf.eom.TravelInformation object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("removing " + object + " from travelInformations relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "travelInformations");
  }

  public com.dyned.conf.eom.TravelInformation createTravelInformationsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("TravelInformation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "travelInformations");
    return (com.dyned.conf.eom.TravelInformation) eo;
  }

  public void deleteTravelInformationsRelationship(com.dyned.conf.eom.TravelInformation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "travelInformations");
    editingContext().deleteObject(object);
  }

  public void deleteAllTravelInformationsRelationships() {
    Enumeration objects = travelInformations().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTravelInformationsRelationship((com.dyned.conf.eom.TravelInformation)objects.nextElement());
    }
  }

  public NSArray<com.dyned.conf.eom.Venue> venues() {
    return (NSArray<com.dyned.conf.eom.Venue>)storedValueForKey("venues");
  }

  public NSArray<com.dyned.conf.eom.Venue> venues(EOQualifier qualifier) {
    return venues(qualifier, null);
  }

  public NSArray<com.dyned.conf.eom.Venue> venues(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<com.dyned.conf.eom.Venue> results;
      results = venues();
      if (qualifier != null) {
        results = (NSArray<com.dyned.conf.eom.Venue>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.dyned.conf.eom.Venue>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToVenuesRelationship(com.dyned.conf.eom.Venue object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("adding " + object + " to venues relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "venues");
  }

  public void removeFromVenuesRelationship(com.dyned.conf.eom.Venue object) {
    if (_Attendee.LOG.isDebugEnabled()) {
      _Attendee.LOG.debug("removing " + object + " from venues relationship");
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


  public static Attendee createAttendee(EOEditingContext editingContext, NSTimestamp dateLastVisit
, NSTimestamp dateRegistered
, String nameFamily
, Integer pwHashCode
, String userEmailAddress
, String userPassword
) {
    Attendee eo = (Attendee) EOUtilities.createAndInsertInstance(editingContext, _Attendee.ENTITY_NAME);    
		eo.setDateLastVisit(dateLastVisit);
		eo.setDateRegistered(dateRegistered);
		eo.setNameFamily(nameFamily);
		eo.setPwHashCode(pwHashCode);
		eo.setUserEmailAddress(userEmailAddress);
		eo.setUserPassword(userPassword);
    return eo;
  }

  public static NSArray<Attendee> fetchAllAttendees(EOEditingContext editingContext) {
    return _Attendee.fetchAllAttendees(editingContext, null);
  }

  public static NSArray<Attendee> fetchAllAttendees(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Attendee.fetchAttendees(editingContext, null, sortOrderings);
  }

  public static NSArray<Attendee> fetchAttendees(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Attendee.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Attendee> eoObjects = (NSArray<Attendee>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Attendee fetchAttendee(EOEditingContext editingContext, String keyName, Object value) {
    return _Attendee.fetchAttendee(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Attendee fetchAttendee(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Attendee> eoObjects = _Attendee.fetchAttendees(editingContext, qualifier, null);
    Attendee eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Attendee)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Attendee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Attendee fetchRequiredAttendee(EOEditingContext editingContext, String keyName, Object value) {
    return _Attendee.fetchRequiredAttendee(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Attendee fetchRequiredAttendee(EOEditingContext editingContext, EOQualifier qualifier) {
    Attendee eoObject = _Attendee.fetchAttendee(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Attendee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Attendee localInstanceIn(EOEditingContext editingContext, Attendee eo) {
    Attendee localInstance = (eo == null) ? null : (Attendee)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
