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

package com.dyned.conf.comp;

import org.apache.log4j.Logger;

import com.dyned.conf.Session;
import com.dyned.conf.eom.Attendee;
import com.dyned.conf.eom.PassportInformation;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXApplication;
import er.extensions.components.ERXComponent;

public class AttendeePassportInfoPage extends CompCommon {
	
	private static Logger log = Logger.getLogger(AttendeePassportInfoPage.class);
			
	public ERXComponent returnToPage;
	public String messageOnScreen;

	public NSArray<String> genderList;
	public String genderItemInList;
	
	public Attendee attendee;
	public PassportInformation passportInformation;
	
	public NSTimestamp dateRequested;
	
//	public NSTimestamp dateArrival;
//	public NSTimestamp dateDeparture;

	public NSTimestamp birthDate;
	public String gender;
	public String nameFamily;
	public String nameGiven;
	public String nameMiddle;
	public String nationality;

	public NSTimestamp passportDateExpire;
	public NSTimestamp passportDateIssued;
	public String passportNumber;
	
    public AttendeePassportInfoPage(WOContext context) {
		super(context);
		messageOnScreen = new String();

		attendee = ((Session)session()).attendee;
    	passportInformation = attendee.passportInformation();
    	fillFormData();
    	
    	genderList = PassportInformation.genders; //new NSArray<String>("Male","Female");
    }
        
    public void setReturnToPage(ERXComponent aValue) {
    	returnToPage = aValue;
    }
    
    public ERXComponent cancelPassportInformationForm() {
    	ec.revert();
    	return returnToPage;
    }
    
    public ERXComponent savePassportInformationForm() {
    	if (
//    			(dateArrival != null) &&
//    			(dateDeparture != null) &&
    			(birthDate != null) &&
    			(gender != null && gender.length()>0) &&
    			(nameFamily != null && nameFamily.length()>0) &&
    			(nameGiven != null && nameGiven.length()>0) &&
    			(nameMiddle != null && nameMiddle.length()>0) &&
    			(nationality != null && nationality.length()>0) &&
    			(passportDateExpire != null) &&
    			(passportDateIssued != null) &&
    			(passportNumber != null && passportNumber.length()>0)
    	) {
    		try {
    			//passportInformation.setDateArrival(dateArrival);
    			//passportInformation.setDateDeparture(dateDeparture);
    			passportInformation.setBirthDate(birthDate);
    			passportInformation.setGender(gender);
    			passportInformation.setNameFamily(nameFamily);
    			passportInformation.setNameGiven(nameGiven);
    			passportInformation.setNameMiddle(nameMiddle);
    			passportInformation.setNationality(nationality);
    			passportInformation.setPassportDateExpire(passportDateExpire);
    			passportInformation.setPassportDateIssued(passportDateIssued);
    			passportInformation.setPassportNumber(passportNumber);
    		} catch (RuntimeException ex) {
    			log.error("Something is wrong with the form data for " + attendee.nameFamily());
    			messageOnScreen = "Some of the information in the form is not valid.";
    			return null;
    		}

    		if (saveMyChanges("Failed to save passport information for "+attendee.nameFamily())) {
    			return returnToPage;
    		} else {
    			messageOnScreen = "Unable to save your information at this time, please try again later.";
    			return null;
    		}
    	}
		messageOnScreen = "Some of the information in the form is not valid or empty.";
    	return null;
    }

    private void fillFormData() {

    	if (passportInformation != null) {
    		//dateArrival = passportInformation.dateArrival();
    		//dateDeparture = passportInformation.dateDeparture();

    		birthDate = passportInformation.birthDate();
    		gender = passportInformation.gender();
    		nameFamily = passportInformation.nameFamily();
    		nameGiven = passportInformation.nameGiven();
    		nameMiddle = passportInformation.nameMiddle();
    		nationality = passportInformation.nationality();

    		passportDateExpire = passportInformation.passportDateExpire();
    		passportDateIssued = passportInformation.passportDateIssued();
    		passportNumber = passportInformation.passportNumber();
    	}
    }
   
}



/*

<tr>
<th class="group" colspan="4">Travel Information</th>
</tr>
<tr>
<td colspan="2">Arrival Date</td>
<td colspan="2">Departure Date</td>
</tr>
<tr>
<td colspan="2"><webobject name = "DateArrival"/> (mm/dd/yyyy)</td>
<td colspan="2"><webobject name = "DateDeparture"/> (mm/dd/yyyy)</td>
</tr>

*/