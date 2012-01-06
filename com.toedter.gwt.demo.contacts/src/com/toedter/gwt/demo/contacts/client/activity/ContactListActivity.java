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

package com.toedter.gwt.demo.contacts.client.activity;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.toedter.gwt.demo.contacts.client.IClientFactory;
import com.toedter.gwt.demo.contacts.client.IContactServiceAsync;
import com.toedter.gwt.demo.contacts.client.event.ContactViewEvent;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;
import com.toedter.gwt.demo.contacts.client.ui.IContactListView;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactListActivity extends AbstractActivity implements IContactListView.Presenter {
	private final IClientFactory clientFactory;
	private final String token;
	private EventBus eventBus;
	private static List<Contact> contacts;

	public ContactListActivity(ContactPlace place, IClientFactory clientFactory) {
		System.out.println("ContactListActivity.ContactListActivity(): " + place.getToken());
		token = place.getToken();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		this.eventBus = eventBus;
		System.out.println("ContactListActivity.start(): " + clientFactory.getEventBus() + ":" + eventBus);
		final IContactListView contactListView = clientFactory.getContactListView();
		contactListView.setPresenter(this);
		containerWidget.setWidget(contactListView.asWidget());

		if (contacts == null) {
			final long startTime = System.currentTimeMillis();
			IContactServiceAsync contactService = clientFactory.getContactService();
			contactService.getAllContacts(new AsyncCallback<List<Contact>>() {

				@Override
				public void onSuccess(List<Contact> result) {
					System.out.println("Time: " + (System.currentTimeMillis() - startTime));
					contacts = result;
					contactListView.initialize(result);
					if (token != null) {
						// views either deal with domain objects (Contact) or
						// just row indices
						int index = getContactIndex(token);
						if (index != -1) {
							contactListView.selectInitialRow(index);
						}

						Contact contact = getContact(token);
						if (contact != null) {
							contactListView.selectInitialContact(contact);
						}
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					System.err.println("Error in getting contacts form contact service");
				}
			});
		} else if (token != null) {
			Contact contact = getContact(token);
			contactListView.selectInitialContact(contact);
		}
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void select(Contact contact) {
		// eventBus.fireEvent(new ContactViewEvent(contact));
		goTo(new ContactPlace(contact.getEmail()));
	}

	@Override
	public void select(int index) {
		System.out.println("ContactListActivity.select(): " + index);
		Contact contact = contacts.get(index);
		eventBus.fireEvent(new ContactViewEvent(contact));
		goTo(new ContactPlace(contact.getEmail()));
	}

	private int getContactIndex(String email) {
		int i = 0;
		for (Contact contact : contacts) {
			if (email.equals(contact.getEmail())) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Contact getContact(String email) {
		for (Contact contact : contacts) {
			if (email.equals(contact.getEmail())) {
				return contact;
			}
		}
		return null;
	}

}