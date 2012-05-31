// $LastChangedRevision$ DO NOT EDIT.  Make changes to TimeZoneInfo.java instead.
package com.dyned.conf.eom;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _TimeZoneInfo extends  EOGenericRecord {
	public static final String ENTITY_NAME = "TimeZoneInfo";

	// Attributes
	public static final String COUNTRY_CODE_KEY = "countryCode";
	public static final String SUMMER_TIME_KEY = "summerTime";
	public static final String UTC_OFFSET_KEY = "utcOffset";
	public static final String ZONE_ID_KEY = "zoneId";

	// Relationships

  private static Logger LOG = Logger.getLogger(_TimeZoneInfo.class);

  public TimeZoneInfo localInstanceIn(EOEditingContext editingContext) {
    TimeZoneInfo localInstance = (TimeZoneInfo)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String countryCode() {
    return (String) storedValueForKey("countryCode");
  }

  public void setCountryCode(String value) {
    if (_TimeZoneInfo.LOG.isDebugEnabled()) {
    	_TimeZoneInfo.LOG.debug( "updating countryCode from " + countryCode() + " to " + value);
    }
    takeStoredValueForKey(value, "countryCode");
  }

  public String summerTime() {
    return (String) storedValueForKey("summerTime");
  }

  public void setSummerTime(String value) {
    if (_TimeZoneInfo.LOG.isDebugEnabled()) {
    	_TimeZoneInfo.LOG.debug( "updating summerTime from " + summerTime() + " to " + value);
    }
    takeStoredValueForKey(value, "summerTime");
  }

  public String utcOffset() {
    return (String) storedValueForKey("utcOffset");
  }

  public void setUtcOffset(String value) {
    if (_TimeZoneInfo.LOG.isDebugEnabled()) {
    	_TimeZoneInfo.LOG.debug( "updating utcOffset from " + utcOffset() + " to " + value);
    }
    takeStoredValueForKey(value, "utcOffset");
  }

  public String zoneId() {
    return (String) storedValueForKey("zoneId");
  }

  public void setZoneId(String value) {
    if (_TimeZoneInfo.LOG.isDebugEnabled()) {
    	_TimeZoneInfo.LOG.debug( "updating zoneId from " + zoneId() + " to " + value);
    }
    takeStoredValueForKey(value, "zoneId");
  }


  public static TimeZoneInfo createTimeZoneInfo(EOEditingContext editingContext, String countryCode
, String summerTime
, String utcOffset
, String zoneId
) {
    TimeZoneInfo eo = (TimeZoneInfo) EOUtilities.createAndInsertInstance(editingContext, _TimeZoneInfo.ENTITY_NAME);    
		eo.setCountryCode(countryCode);
		eo.setSummerTime(summerTime);
		eo.setUtcOffset(utcOffset);
		eo.setZoneId(zoneId);
    return eo;
  }

  public static NSArray<TimeZoneInfo> fetchAllTimeZoneInfos(EOEditingContext editingContext) {
    return _TimeZoneInfo.fetchAllTimeZoneInfos(editingContext, null);
  }

  public static NSArray<TimeZoneInfo> fetchAllTimeZoneInfos(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _TimeZoneInfo.fetchTimeZoneInfos(editingContext, null, sortOrderings);
  }

  public static NSArray<TimeZoneInfo> fetchTimeZoneInfos(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_TimeZoneInfo.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<TimeZoneInfo> eoObjects = (NSArray<TimeZoneInfo>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static TimeZoneInfo fetchTimeZoneInfo(EOEditingContext editingContext, String keyName, Object value) {
    return _TimeZoneInfo.fetchTimeZoneInfo(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static TimeZoneInfo fetchTimeZoneInfo(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<TimeZoneInfo> eoObjects = _TimeZoneInfo.fetchTimeZoneInfos(editingContext, qualifier, null);
    TimeZoneInfo eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (TimeZoneInfo)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TimeZoneInfo that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static TimeZoneInfo fetchRequiredTimeZoneInfo(EOEditingContext editingContext, String keyName, Object value) {
    return _TimeZoneInfo.fetchRequiredTimeZoneInfo(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static TimeZoneInfo fetchRequiredTimeZoneInfo(EOEditingContext editingContext, EOQualifier qualifier) {
    TimeZoneInfo eoObject = _TimeZoneInfo.fetchTimeZoneInfo(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TimeZoneInfo that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static TimeZoneInfo localInstanceIn(EOEditingContext editingContext, TimeZoneInfo eo) {
    TimeZoneInfo localInstance = (eo == null) ? null : (TimeZoneInfo)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
