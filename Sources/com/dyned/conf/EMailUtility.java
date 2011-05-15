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

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;

import er.javamail.ERMailDeliveryHTML;

public class EMailUtility extends Object {

	private static Logger log = Logger.getLogger(EMailUtility.class);

	// Created by Kieran Kelleher on 1/28/05.
	// Free to use as you wish. Use at your own risk.
	//

	@SuppressWarnings("unchecked")
	public static void composeAndSendComponentMail(WOComponent emailPage, NSDictionary emailHeaders ) {
		// Requires no null values

		composeAndSendComponentMail(emailPage,
				(String)emailHeaders.valueForKey("fromAddress"),
				(String)emailHeaders.valueForKey("fromPersonalName"),
				(String)emailHeaders.valueForKey("toAddress"),
				(String)emailHeaders.valueForKey("toPersonalName"),
				(NSArray)emailHeaders.valueForKey("toAddresses"),
				(String)emailHeaders.valueForKey("toPersonalName"),
				(String)emailHeaders.valueForKey("toPersonalName") );
	}

	public static String composeAndSendComponentMail( WOComponent emailPage,
			String fromAddress,
			String fromPersonalName,
			String toAddress,
			String toPersonalName,
			NSArray<String> toAddresses,
			String replyToAddress,
			String subject ) {

		if (log.isDebugEnabled()) log.debug("Sending email with subject '" 
				+ subject + "' and addressed to '" 
				+ (toAddress == null ? "" : toAddress.toString() )
				+ (toAddresses == null ? "" : toAddresses.toString() ) );

		// Create a new mail delivery instance
		ERMailDeliveryHTML eMail = new ERMailDeliveryHTML();

		// Set the WOComponent to be used for rendering the mail
		eMail.setComponent( emailPage );

		try {
			eMail.newMail();

			// fromAddress with optional fromPersonalName
			if ( fromAddress != null && fromPersonalName != null ) {
				eMail.setFromAddress( fromAddress, fromPersonalName );
			} else if (fromAddress != null) {
				eMail.setFromAddress( fromAddress );
			}

			// optional toAddress and optional toPersonalName
			if ( toAddress != null && toPersonalName != null ) {
				eMail.setToAddress( toAddress, toPersonalName );
			} else if (toAddress != null) {
				eMail.setToAddress( toAddress );
			}

			// optional toAddresses (NSArray)
			if ( toAddresses != null ) eMail.setToAddresses( toAddresses );

			// reply to address
			if ( replyToAddress != null ) eMail.setReplyToAddress( replyToAddress );

			eMail.setSubject( subject );

			eMail.sendMail(true);

			return null;
			
		} catch (Exception e) {
			log.error("Exception sending email: " + e);
			return e.getMessage();
		}
	}

}
