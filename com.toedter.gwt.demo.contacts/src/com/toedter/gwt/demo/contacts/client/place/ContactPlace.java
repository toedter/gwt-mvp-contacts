package com.toedter.gwt.demo.contacts.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ContactPlace extends Place {
	private final String token;

	public ContactPlace(String token) {
		this.token = token;
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