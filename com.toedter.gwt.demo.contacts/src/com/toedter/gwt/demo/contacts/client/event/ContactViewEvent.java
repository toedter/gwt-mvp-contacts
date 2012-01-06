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

package com.toedter.gwt.demo.contacts.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactViewEvent extends GwtEvent<ContactViewEvent.Handler> {
	public interface Handler extends EventHandler {
		void onContactView(ContactViewEvent event);
	}

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
