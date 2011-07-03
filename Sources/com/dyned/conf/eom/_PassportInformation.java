// $LastChangedRevision$ DO NOT EDIT.  Make changes to PassportInformation.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _PassportInformation extends  EOGenericRecord {
	public static final String ENTITY_NAME = "PassportInformation";

	// Attributes
	public static final String BIRTH_DATE_KEY = "birthDate";
	public static final String BIRTH_PLACE_KEY = "birthPlace";
	public static final String DATE_REQUESTED_KEY = "dateRequested";
	public static final String GENDER_KEY = "gender";
	public static final String NAME_FAMILY_KEY = "nameFamily";
	public static final String NAME_GIVEN_KEY = "nameGiven";
	public static final String NAME_MIDDLE_KEY = "nameMiddle";
	public static final String NATIONALITY_KEY = "nationality";
	public static final String PASSPORT_DATE_EXPIRE_KEY = "passportDateExpire";
	public static final String PASSPORT_DATE_ISSUED_KEY = "passportDateIssued";
	public static final String PASSPORT_NUMBER_KEY = "passportNumber";

	// Relationships

  private static Logger LOG = Logger.getLogger(_PassportInformation.class);

  public PassportInformation localInstanceIn(EOEditingContext editingContext) {
    PassportInformation localInstance = (PassportInformation)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp birthDate() {
    return (NSTimestamp) storedValueForKey("birthDate");
  }

  public void setBirthDate(NSTimestamp value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating birthDate from " + birthDate() + " to " + value);
    }
    takeStoredValueForKey(value, "birthDate");
  }

  public String birthPlace() {
    return (String) storedValueForKey("birthPlace");
  }

  public void setBirthPlace(String value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating birthPlace from " + birthPlace() + " to " + value);
    }
    takeStoredValueForKey(value, "birthPlace");
  }

  public NSTimestamp dateRequested() {
    return (NSTimestamp) storedValueForKey("dateRequested");
  }

  public void setDateRequested(NSTimestamp value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating dateRequested from " + dateRequested() + " to " + value);
    }
    takeStoredValueForKey(value, "dateRequested");
  }

  public String gender() {
    return (String) storedValueForKey("gender");
  }

  public void setGender(String value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating gender from " + gender() + " to " + value);
    }
    takeStoredValueForKey(value, "gender");
  }

  public String nameFamily() {
    return (String) storedValueForKey("nameFamily");
  }

  public void setNameFamily(String value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating nameFamily from " + nameFamily() + " to " + value);
    }
    takeStoredValueForKey(value, "nameFamily");
  }

  public String nameGiven() {
    return (String) storedValueForKey("nameGiven");
  }

  public void setNameGiven(String value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating nameGiven from " + nameGiven() + " to " + value);
    }
    takeStoredValueForKey(value, "nameGiven");
  }

  public String nameMiddle() {
    return (String) storedValueForKey("nameMiddle");
  }

  public void setNameMiddle(String value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating nameMiddle from " + nameMiddle() + " to " + value);
    }
    takeStoredValueForKey(value, "nameMiddle");
  }

  public String nationality() {
    return (String) storedValueForKey("nationality");
  }

  public void setNationality(String value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating nationality from " + nationality() + " to " + value);
    }
    takeStoredValueForKey(value, "nationality");
  }

  public NSTimestamp passportDateExpire() {
    return (NSTimestamp) storedValueForKey("passportDateExpire");
  }

  public void setPassportDateExpire(NSTimestamp value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating passportDateExpire from " + passportDateExpire() + " to " + value);
    }
    takeStoredValueForKey(value, "passportDateExpire");
  }

  public NSTimestamp passportDateIssued() {
    return (NSTimestamp) storedValueForKey("passportDateIssued");
  }

  public void setPassportDateIssued(NSTimestamp value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating passportDateIssued from " + passportDateIssued() + " to " + value);
    }
    takeStoredValueForKey(value, "passportDateIssued");
  }

  public String passportNumber() {
    return (String) storedValueForKey("passportNumber");
  }

  public void setPassportNumber(String value) {
    if (_PassportInformation.LOG.isDebugEnabled()) {
    	_PassportInformation.LOG.debug( "updating passportNumber from " + passportNumber() + " to " + value);
    }
    takeStoredValueForKey(value, "passportNumber");
  }


  public static PassportInformation createPassportInformation(EOEditingContext editingContext, NSTimestamp birthDate
, NSTimestamp dateRequested
, String gender
, String nameFamily
, String nameGiven
, String nameMiddle
, String nationality
, NSTimestamp passportDateExpire
, NSTimestamp passportDateIssued
, String passportNumber
) {
    PassportInformation eo = (PassportInformation) EOUtilities.createAndInsertInstance(editingContext, _PassportInformation.ENTITY_NAME);    
		eo.setBirthDate(birthDate);
		eo.setDateRequested(dateRequested);
		eo.setGender(gender);
		eo.setNameFamily(nameFamily);
		eo.setNameGiven(nameGiven);
		eo.setNameMiddle(nameMiddle);
		eo.setNationality(nationality);
		eo.setPassportDateExpire(passportDateExpire);
		eo.setPassportDateIssued(passportDateIssued);
		eo.setPassportNumber(passportNumber);
    return eo;
  }

  public static NSArray<PassportInformation> fetchAllPassportInformations(EOEditingContext editingContext) {
    return _PassportInformation.fetchAllPassportInformations(editingContext, null);
  }

  public static NSArray<PassportInformation> fetchAllPassportInformations(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _PassportInformation.fetchPassportInformations(editingContext, null, sortOrderings);
  }

  public static NSArray<PassportInformation> fetchPassportInformations(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_PassportInformation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<PassportInformation> eoObjects = (NSArray<PassportInformation>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static PassportInformation fetchPassportInformation(EOEditingContext editingContext, String keyName, Object value) {
    return _PassportInformation.fetchPassportInformation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static PassportInformation fetchPassportInformation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<PassportInformation> eoObjects = _PassportInformation.fetchPassportInformations(editingContext, qualifier, null);
    PassportInformation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (PassportInformation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one PassportInformation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static PassportInformation fetchRequiredPassportInformation(EOEditingContext editingContext, String keyName, Object value) {
    return _PassportInformation.fetchRequiredPassportInformation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static PassportInformation fetchRequiredPassportInformation(EOEditingContext editingContext, EOQualifier qualifier) {
    PassportInformation eoObject = _PassportInformation.fetchPassportInformation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no PassportInformation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static PassportInformation localInstanceIn(EOEditingContext editingContext, PassportInformation eo) {
    PassportInformation localInstance = (eo == null) ? null : (PassportInformation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
