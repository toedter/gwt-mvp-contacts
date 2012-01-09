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

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.toedter.gwt.demo.contacts.client.ui.IContactDetailsView;
import com.toedter.gwt.demo.contacts.client.ui.IContactListView;
import com.toedter.gwt.demo.contacts.client.ui.IToolBarView;
import com.toedter.gwt.demo.contacts.shared.Contact;

public interface IClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	IContactListView getContactListView();

	IContactDetailsView getContactDetailsView();

	IToolBarView getToolBarView();

	IContactServiceAsync getContactService();

	List<Contact> getContacts();

	void setContacts(List<Contact> contacts);
}
