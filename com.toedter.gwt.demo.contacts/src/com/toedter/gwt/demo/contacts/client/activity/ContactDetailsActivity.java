/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toedter.gwt.demo.contacts.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.toedter.gwt.demo.contacts.client.IClientFactory;
import com.toedter.gwt.demo.contacts.client.IContactServiceAsync;
import com.toedter.gwt.demo.contacts.client.event.ContactViewEvent;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;
import com.toedter.gwt.demo.contacts.client.ui.IContactDetailsView;
import com.toedter.gwt.demo.contacts.shared.Contact;

/**
 * Activities are started and stopped by an ActivityManager associated with a
 * container Widget.
 */
public class ContactDetailsActivity extends AbstractActivity implements
		IContactDetailsView.Presenter {

	/**
	 * Used to obtain views, eventBus, placeController. Alternatively, could be
	 * injected via GIN.
	 */
	private final IClientFactory clientFactory;
	private EventBus eventBus;
	private ContactViewEvent.Handler handler;
	private final String token;

	public ContactDetailsActivity(ContactPlace place,
			IClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.token = place.getToken();
		System.out
				.println("ContactDetailsActivity.ContactDetailsActivity() token: "
						+ token);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		System.out.println("ContactDetailsActivity.start(): "
				+ clientFactory.getEventBus() + ":" + eventBus);
		final IContactDetailsView contactDetailsView = clientFactory
				.getContactDetailsView();
		this.eventBus = eventBus;

		contactDetailsView.setPresenter(this);
		handler = new ContactViewEvent.Handler() {

			@Override
			public void onContactView(ContactViewEvent event) {
				System.out.println("ContactDetailsActivity onContactView(): "
						+ event.getContact());
				contactDetailsView.setContact(event.getContact());
			}
		};
		this.eventBus.addHandler(ContactViewEvent.TYPE, handler);

		containerWidget.setWidget(contactDetailsView.asWidget());

		if (token != null) {
			IContactServiceAsync contactService = clientFactory
					.getContactService();
			contactService.getContact(token, new AsyncCallback<Contact>() {

				@Override
				public void onSuccess(Contact result) {
					contactDetailsView.setContact(result);
				}

				@Override
				public void onFailure(Throwable caught) {
					System.err
							.println("Error in getting contacts form contact service");
				}
			});
		}

	}

	// @Override
	// public String mayStop() {
	// return "Please hold on. This activity is stopping.";
	// }

	/**
	 * @see IContactDetailsView.Presenter#goTo(Place)
	 */
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
