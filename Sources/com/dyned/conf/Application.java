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

import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.dyned.conf.comp.ExceptionPage;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOMessage;
import com.webobjects.appserver.WORedirect;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOHostUtilities;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSTimeZone;

import er.extensions.appserver.ERXApplication;

public class Application extends ERXApplication {
	
	private static Logger log = Logger.getLogger(Application.class);
			
	public boolean makeSecureLinks;
	
	public String emailSenderAddress = null;
	public String emailSenderFullName = null;
	public String conferenceSiteName = null;

	
	public static void main(String[] argv) {
		main(argv, Application.class);
	}

	public Application() {
		log.info("Welcome to " + name() + " !");

		this.setDefaultEncoding("UTF-8");
		//WOMessage.setDefaultEncoding("UTF-8");
		WOMessage.setDefaultURLEncoding("UTF-8");

		TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
		NSTimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));

		setDefaultRequestHandler(requestHandlerForKey (directActionRequestHandlerKey()));
				
		makeSecureLinks = false;
		
		emailSenderAddress = System.getProperty("emailSenderAddress");
		emailSenderFullName = System.getProperty("emailSenderFullName");
		conferenceSiteName = System.getProperty("conferenceSiteName");
		
		log.info("Local hosts: " + WOHostUtilities.getLocalHosts().toString());
	}

//	public String emailSenderAddress() {
//		return emailSenderAddress;
//	}
//	
//	public String emailSenderFullName() {
//		return emailSenderFullName;
//	}
//
//	public String conferenceSiteName() {
//		return conferenceSiteName;
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public WOResponse handleSessionRestorationErrorInContext(WOContext aContext) {
		
		WORedirect newLoginPage = (WORedirect)pageWithName("WORedirect", aContext);
		newLoginPage.setUrl(aContext.directActionURLForActionNamed(
				"default", 
				new NSDictionary(Boolean.FALSE, "wosid")));

		return newLoginPage.generateResponse();
	}

	public WOResponse handleException (java.lang.Exception ex, WOContext aContext) {
		log.error(ex.getMessage());
		ex.printStackTrace();
		
		ExceptionPage nextPage = (ExceptionPage)pageWithName(ExceptionPage.class.getName(), aContext);
		nextPage.setMessageOnScreen(ex.getMessage());
		return nextPage.generateResponse();
	}
}



//NSArray timezones = new NSArray(NSTimeZone.getAvailableIDs());
//for (int i=0; i < timezones.count(); i++) {
//	log.info(timezones.objectAtIndex(i).toString());
//}


//if ( ((Session)(aContext.session())).administrator != null ) {
//AdminsLogin nextPage = (AdminsLogin)pageWithName(AdminsLogin.class.getName(), aContext);
//return nextPage.generateResponse();
//} else {
//Main nextPage = (Main)pageWithName(Main.class.getName(), aContext);
//nextPage.setMessageOnScreen("You session has expired.");
//return nextPage.generateResponse();
//} 
