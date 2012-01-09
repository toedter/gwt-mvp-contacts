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

public class ContactEditPlace extends Place {

	private final String token;

	public ContactEditPlace(String token) {
		this.token = token == null ? "" : token;
	}

	public String getToken() {
		return token;
	}

	public static class Tokenizer implements PlaceTokenizer<ContactEditPlace> {

		@Override
		public String getToken(ContactEditPlace place) {
			return place.getToken();
		}

		@Override
		public ContactEditPlace getPlace(String token) {
			return new ContactEditPlace(token);
		}

	}
}
