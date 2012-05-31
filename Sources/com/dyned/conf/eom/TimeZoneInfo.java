package com.dyned.conf.eom;

import org.apache.log4j.Logger;

import com.webobjects.foundation.NSTimeZone;

public class TimeZoneInfo extends _TimeZoneInfo {
	private static Logger log = Logger.getLogger(TimeZoneInfo.class);
	
	public NSTimeZone tz() {
		return NSTimeZone.timeZoneWithName(this.zoneId(), false);
	}

}
