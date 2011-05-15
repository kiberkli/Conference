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

package com.dyned.conf;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

import er.calendar.ERCalendarEvent;

public class CalendarEvent implements ERCalendarEvent {

	public final String EVENTSTATUS_TENTATIVE = "TENTATIVE";
	public final String EVENTSTATUS_CONFIRMED = "CONFIRMED";
	public final String EVENTSTATUS_CANCELLED = "CANCELLED";
			
	protected NSTimestamp endTime;
	protected NSTimestamp startTime;
	protected String status;
	protected String summary;
	protected String uniqueId;
	protected boolean wholeDay;

	public CalendarEvent(
			NSTimestamp aStartTime, 
			NSTimestamp anEndTime, 
			String aSummary, 
			String aUniqueId,
			boolean theWholeDay
	) {
		startTime = aStartTime;
		endTime = anEndTime; //anEndTime == null ? aStartTime : anEndTime;
		summary = aSummary;
		uniqueId = aUniqueId;		
		wholeDay = theWholeDay;
	}

	public NSTimestamp endTime() {
		return endTime;
	}

	public int repeatCount() {
		return 0;
	}

	public int repeatDayOfWeek() {
		return 0;
	}

	public int repeatDayOfWeekInMonth() {
		return 0;
	}

	@SuppressWarnings({ "rawtypes" })
	public NSArray repeatDaysOfMonth() {
		return null;
	}

	public int repeatFrequency() {
		return 0;
	}

	public int sequence() {
		return (int)(new NSTimestamp().getTime() / 10000);
	}

	public NSTimestamp startTime() {
		return startTime;
	}

	public String status() {
		return EVENTSTATUS_CONFIRMED;
	}

	public String summary() {
		return summary;
	}

	public String uniqueId() {
		return uniqueId;
	}

	public boolean wholeDay() {
		return wholeDay;
	}

}
