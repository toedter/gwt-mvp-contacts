/*******************************************************************************
 * Copyright (c) 2012 Kai Toedter and others.
 * 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Kai Toedter - initial API and implementation
 ******************************************************************************/

package com.toedter.gwt.demo.contacts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ContactPlace extends Place {
	private final String token;

	public ContactPlace(String token) {
		this.token = token == null ? "" : token;
	}

	public String getToken() {
		return token;
	}

	public static class Tokenizer implements PlaceTokenizer<ContactPlace> {

		@Override
		public String getToken(ContactPlace place) {
			return place.getToken();
		}

		@Override
		public ContactPlace getPlace(String token) {
			return new ContactPlace(token);
		}

	}
}