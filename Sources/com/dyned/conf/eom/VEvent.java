/*

Copyright (c) 2011, DynEd International, Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

	* Redistributions of source code must retain the above copyright notice, 
	  this list of conditions and the following disclaimer.

	* Redistributions in binary form must reproduce the above copyright notice, 
	  this list of conditions and the following disclaimer in the documentation 
	  and/or other materials provided with the distribution.

	* Neither the name of DynEd International, Inc. nor the names of its 
	  contributors may be used to endorse or promote products derived from this 
	  software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR 
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

package com.dyned.conf.eom;

import java.text.NumberFormat;

import org.apache.log4j.Logger;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSLog;
import com.webobjects.foundation.NSNumberFormatter;
import com.webobjects.foundation.NSTimeZone;
import com.webobjects.foundation.NSTimestamp;

public class VEvent extends _VEvent {
	public static final String ID_KEY = "id";
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(VEvent.class);

	public String colorStyle() {
		if (this.color() == null) {
			return "background-color: #ffffff;";
		} else
			return "background-color: " + this.color() + ";";
	}
	
	public String descriptionForHTML() {
		if (this.description() != null)
			return this.description().replaceAll("\\r\\n", "<br>");
		else
			return null;
	}
	
	public int uniqueID() {
		Integer result = null;
		
		try {
			NSDictionary dict = EOUtilities.primaryKeyForObject(this.editingContext(), this);
			result = (Integer)(dict.objectForKey(ID_KEY));
        } catch (RuntimeException ex) {
        	NSLog.err.appendln(ex.getMessage());
            NSLog.err.appendln("Failed to get primary key for EOGenericRecord, Object not saved yet?.");
		}
		
		return result.intValue();
	}
	
	public void setStartTime(Integer hour, Integer minute) {
		this.setStartHour(hour);
		this.setStartMinute(minute);
	}
		
	public void setEndTime(Integer hour, Integer minute) {
		this.setEndHour(hour);
		this.setEndMinute(minute);
	}
	
	public String startTime() {
		return new String(this.startHour()+":"+this.startMinute());
	}

	public String endTime() {
		return new String(this.endHour()+":"+this.endMinute());
	}
	
	public String startHourString() {
		return this.startHour().toString();
	}
	
	public String startMinuteString() {
		NSNumberFormatter numberFormatter = new NSNumberFormatter("0#");
		return numberFormatter.stringForObjectValue(this.startMinute());
	}

	public String endHourString() {
		return this.endHour().toString();
	}
	
	public String endMinuteString() {
		NSNumberFormatter numberFormatter = new NSNumberFormatter("0#");
		return numberFormatter.stringForObjectValue(this.endMinute());
	}

	public NSTimestamp dateTimeStart() {
		NSTimestamp results = new NSTimestamp(
				this.sectionDate().yearOfCommonEra(),
				this.sectionDate().monthOfYear(),
				this.sectionDate().dayOfMonth(),
				this.startHour() != null ? this.startHour() : 0,
				this.startMinute() != null ? this.startMinute() : 0,
				0,
				NSTimeZone.getGMT()
				);
		return results;
	}

	public NSTimestamp dateTimeEnd() {
		NSTimestamp results = new NSTimestamp(
				this.sectionDate().yearOfCommonEra(),
				this.sectionDate().monthOfYear(),
				this.sectionDate().dayOfMonth(),
				this.endHour() != null ? this.endHour() : 0,
				this.endMinute() != null ? this.endMinute() : 0,
				0,
				NSTimeZone.getGMT()
				);
		return results;
	}
	
}


//public void setDateTimeStart(NSTimestamp dateStart, String timeStart) {
//
//	NSTimestamp combinedNSTimespamp = null;
//
//	if (dateStart != null && timeStart != null) {
//
//		// timeStart should be formatted as 
//		//	hh:mm [am|pm]
//		NSArray<String> timeStartArray = new NSArray<String>(timeStart.split(":"));
//
//		String hourStartString = timeStartArray.objectAtIndex(0); 
//		String minuteStartString = timeStartArray.objectAtIndex(1);
//		
//		log.info("1.hourStartString   :"+hourStartString);
//		log.info("2.minuteStartString :"+minuteStartString);
//
//		String AMorPM = new String(); 
//		if ( minuteStartString.indexOf("AM") > 0 || minuteStartString.indexOf("am") > 0) {
//			AMorPM = "AM";
//			minuteStartString = minuteStartString.replaceAll("[am|AM]", "");
//		} else if ( minuteStartString.indexOf("PM") > 0 || minuteStartString.indexOf("pm") > 0) {
//			AMorPM = "PM";
//			minuteStartString = minuteStartString.replaceAll("[pm|PM]", "");
//		}
//
//		log.info("3.minuteStartString :"+minuteStartString);
//
//		int hourStart = 0;
//		int minuteStart = 0;
//		try {
//			hourStart = Integer.parseInt(hourStartString);
//			minuteStart = Integer.parseInt(minuteStartString);
//
//			log.info("4.hourStart         :"+minuteStartString);
//			log.info("5.minuteStart       :"+minuteStartString);
//
//			GregorianCalendar myCalendar = new GregorianCalendar();
//			myCalendar.setTime(dateStart);
//
//			if (AMorPM.equalsIgnoreCase("PM"))
//    			myCalendar.add(GregorianCalendar.HOUR_OF_DAY, hourStart+12);
//			else
//    			myCalendar.add(GregorianCalendar.HOUR_OF_DAY, hourStart);
//			
//			myCalendar.add(GregorianCalendar.MINUTE, minuteStart);
//
//			int year = myCalendar.get(GregorianCalendar.YEAR);
//			int month = myCalendar.get(GregorianCalendar.MONTH);
//			int day = myCalendar.get(GregorianCalendar.DAY_OF_MONTH);
//			int hour = myCalendar.get(GregorianCalendar.HOUR_OF_DAY);
//			int minute = myCalendar.get(GregorianCalendar.MINUTE);
//			
//			combinedNSTimespamp = new NSTimestamp(year, month+1, day, hour, minute, 0, null);
//			log.info("6.combinedNSTimespamp :"+minuteStartString);
//
//		} catch (NumberFormatException ex) {
//			log.error("The timeStart " + timeStart + " can't be parsed for hour or minute value.");
//			log.error(ex.getMessage());
//		} catch (RuntimeException ex) {
//			log.error("The timeStart " + timeStart + " can't be parsed.");
//			log.error(ex.getMessage());
//		}
//	}		
//	this.setDateTimeStart(combinedNSTimespamp);
//}
