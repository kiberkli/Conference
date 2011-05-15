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

import java.util.GregorianCalendar;

import com.webobjects.foundation.NSTimeZone;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.foundation.ERXTimestampUtilities;

public class TimestampUtilities extends ERXTimestampUtilities {

	/*
	 * Adds the days to the first timestamp. Does not change the time.
	 * Parameters:
	 * 	ts - timestamp to have the time added too.
	 * 	dayToAdd - number of days to add to ts
	 * Returns:
	 * 	the first timestamp with the days added to it.
	 * 
	 */
	
	public static NSTimestamp timestampByAddingDays(NSTimestamp ts, int dayToAdd) {
		GregorianCalendar myCalendar = new GregorianCalendar();
		myCalendar.setTime(ts);
		
		myCalendar.add(GregorianCalendar.DAY_OF_MONTH, dayToAdd);

		int year = myCalendar.get(GregorianCalendar.YEAR);
		int month = myCalendar.get(GregorianCalendar.MONTH);
		int day = myCalendar.get(GregorianCalendar.DAY_OF_MONTH);
		int hour = myCalendar.get(GregorianCalendar.HOUR_OF_DAY);
		int minute = myCalendar.get(GregorianCalendar.MINUTE);
		int seconds = myCalendar.get(GregorianCalendar.SECOND);

		NSTimestamp results = new NSTimestamp(
				year, 
				month+1, 
				day, 
				hour, 
				minute, 
				seconds, 
				NSTimeZone.getDefault()
		);

		return results;
	}

}