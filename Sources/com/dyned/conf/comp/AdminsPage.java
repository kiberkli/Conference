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

import com.dyned.conf.Session;
import com.dyned.conf.eom.Admin;
import com.webobjects.eoaccess.EOUtilities;

import com.webobjects.appserver.WOContext;

import er.extensions.appserver.ERXApplication;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

public class AdminsPage extends CompCommon {

	public String fullName;
	public String username;
	public String password;
	public String passwordConfirm;
	public String emailAddres;
	
	public Admin administrator;
	public Admin administratorInList;
	public NSArray<Admin> administratorList;
	
	public String promptOnPage;
	public String messageOnPage;
	
    public AdminsPage(WOContext context) {
        super(context);
        
		fullName  = new String();
		username = new String();
		password = new String();
		passwordConfirm = new String();
		emailAddres = new String();
		
		promptOnPage = new String("Add a new administrator");
		messageOnPage = new String();

        passwordConfirm  = null;
        
        administrator = null;
        administratorList = fullAdministratorList();
        
    }
    
    public void setFormData(Admin aValue) {
    	if (aValue != null) {
    		fullName  = new String(aValue.fullName());
    		username = new String(aValue.username());
    		password = new String(aValue.password());
    		passwordConfirm = new String(aValue.password());
    		emailAddres = new String(aValue.emailAddress());
    		
    		promptOnPage = "Edit the selected administrator";
    	}
    	administrator = aValue;
    }
    
    public AdminsPage saveFormData() {
    	if (password.compareTo(passwordConfirm) != 0) {
			messageOnPage = "The passwords do not match";
			return null;
    	}
		if (
				(fullName != null && fullName.length() > 0) &&
				(username != null && username.length() > 0) &&
				(password != null && password.length() > 0) && 
				(emailAddres != null && emailAddres.length() > 0)
			) {
			if (administrator == null) {
				if (emailAddressExistInAdmins(emailAddres)) {
					messageOnPage = "The e-mail address has already been added.";
					return null;
				}
				// Assuming this is a new record
				administrator = (Admin)EOUtilities.createAndInsertInstance(ec, Admin.ENTITY_NAME);
				administrator.setDateCreated(new NSTimestamp());
			}
			administrator.setFullName(fullName);
			administrator.setUsername(username);
			administrator.setPassword(password);
			administrator.setEmailAddress(emailAddres);
			administrator.setDateModified(new NSTimestamp());
			
			try {
				ec.saveChanges();
			} catch (RuntimeException ex) {
				ec.revert();
				messageOnPage = "There was a problem saving to the database. Please try again later.";
				ERXApplication.log.error(ex.getMessage());
				return null;
			}
		} else {
			messageOnPage = "Some required form values are invalid.";
			return null;
		}
		return pageWithName(AdminsPage.class);
    }

    public AdminsPage deleteThisAdministrator() {
		ec.deleteObject((Admin)administrator);

		if (saveMyChanges("Failed saving new or edited admin"))
			return pageWithName(AdminsPage.class);
		else {
			messageOnPage = "There was a problem saving to the database. Please try again later.";
			return null;
		}
    }
    
    public boolean pageAdministratorEqualsSessionAdministrator() {
    	Admin sessionAdministrator = ((Session)session()).administrator;
    	if (administrator == null) {
    		return true; 
    	} else {
        	return (administrator.equals(sessionAdministrator));
    	}
    }
        
    public AdminsPage editSelectedAdministrator() {
    	AdminsPage nextPage = (AdminsPage)(pageWithName(AdminsPage.class.getName()));
    	nextPage.setFormData(administratorInList);
    	return nextPage;
    }
}