package com.toedter.gwt.demo.contacts.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.toedter.gwt.demo.contacts.client.ui.IContactDetailsView;
import com.toedter.gwt.demo.contacts.client.ui.IContactListView;

public interface IClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	IContactListView getMainView();

	IContactDetailsView getContactDetailsView();

	IContactServiceAsync getContactService();
}
