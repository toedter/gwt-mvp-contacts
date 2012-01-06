/*
 * Copyright 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.toedter.gwt.demo.contacts.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.toedter.gwt.demo.contacts.shared.Contact;

/**
 * Fired when the user wants to view a contact.
 */
public class ContactViewEvent extends GwtEvent<ContactViewEvent.Handler> {
	/**
	 * Implemented by objects that handle {@link ContactViewEvent}.
	 */
	public interface Handler extends EventHandler {
		void onContactView(ContactViewEvent event);
	}

	/**
	 * The event type.
	 */
	public static final Type<ContactViewEvent.Handler> TYPE = new Type<ContactViewEvent.Handler>();

	private final Contact contact;

	public ContactViewEvent(Contact contact) {
		this.contact = contact;
	}

	@Override
	public final Type<ContactViewEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	public Contact getContact() {
		return contact;
	}

	@Override
	protected void dispatch(ContactViewEvent.Handler handler) {
		handler.onContactView(this);
	}
}
