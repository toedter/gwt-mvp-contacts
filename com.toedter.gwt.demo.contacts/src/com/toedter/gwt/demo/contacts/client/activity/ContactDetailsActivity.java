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

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.toedter.gwt.demo.contacts.client.IClientFactory;
import com.toedter.gwt.demo.contacts.client.event.ContactViewEvent;
import com.toedter.gwt.demo.contacts.client.place.ContactEditPlace;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;
import com.toedter.gwt.demo.contacts.client.ui.IContactDetailsView;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactDetailsActivity extends AbstractActivity implements IContactDetailsView.Presenter {

	private final IClientFactory clientFactory;
	private EventBus eventBus;
	private ContactViewEvent.Handler handler;
	private final String token;
	private IContactDetailsView contactDetailsView;
	private final Place place;

	public ContactDetailsActivity(ContactPlace place, IClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		token = place.getToken();
		this.place = place;
		System.out.println("ContactDetailsActivity.ContactDetailsActivity() token: " + token);
	}

	public ContactDetailsActivity(ContactEditPlace place, IClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		token = place.getToken();
		this.place = place;
		System.out.println("ContactDetailsActivity.ContactDetailsActivity() token: " + token);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		System.out.println("ContactDetailsActivity.start()");
		ActivityRegistry.setContactDetailsActivity(this);
		contactDetailsView = clientFactory.getContactDetailsView();
		this.eventBus = eventBus;
		contactDetailsView.setPresenter(this);
		if (place instanceof ContactEditPlace || (place instanceof ContactPlace && token.length() == 0)) {
			contactDetailsView.clear();
		}
		handler = new ContactViewEvent.Handler() {

			@Override
			public void onContactView(ContactViewEvent event) {
				System.out.println("ContactDetailsActivity onContactView()");
				contactDetailsView.setContact(event.getContact());
			}
		};
		this.eventBus.addHandler(ContactViewEvent.TYPE, handler);

		containerWidget.setWidget(contactDetailsView.asWidget());
	}

	@Override
	public String mayStop() {
		contactDetailsView.setPresenter(null);
		ActivityRegistry.setContactDetailsActivity(null);
		return null;
	}

	/**
	 * @see IContactDetailsView.Presenter#goTo(Place)
	 */
	@Override
	public void goTo(Place place) {
		System.out.println("ContactDetailsActivity.goTo()");
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public Contact getContact() {
		return contactDetailsView.getContact();
	}
}
