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

package com.toedter.gwt.demo.contacts.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.toedter.gwt.demo.contacts.client.ui.ContactDetailsView2;
import com.toedter.gwt.demo.contacts.client.ui.ContactListView2;
import com.toedter.gwt.demo.contacts.client.ui.IContactDetailsView;
import com.toedter.gwt.demo.contacts.client.ui.IContactListView;
import com.toedter.gwt.demo.contacts.client.ui.IToolBarView;
import com.toedter.gwt.demo.contacts.client.ui.ToolBarView;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ClientFactory implements IClientFactory {
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);

	private static final IContactListView contactListView = new ContactListView2();

	private static final IContactDetailsView contactDetailsView = new ContactDetailsView2();

	private static final IToolBarView toolBarView = new ToolBarView();

	private final IContactServiceAsync contactService = GWT.create(IContactService.class);
	private List<Contact> contacts;

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public IToolBarView getToolBarView() {
		return toolBarView;
	}

	@Override
	public IContactListView getContactListView() {
		return contactListView;
	}

	@Override
	public IContactDetailsView getContactDetailsView() {
		return contactDetailsView;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public IContactServiceAsync getContactService() {
		return contactService;
	}

	@Override
	public List<Contact> getContacts() {
		return contacts;
	}

	@Override
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}
