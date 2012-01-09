package com.toedter.gwt.demo.contacts.client.ui;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.CellTable;
import com.octo.gwt.test.GwtCreateHandler;
import com.octo.gwt.test.GwtTest;
import com.octo.gwt.test.utils.events.Browser;
import com.toedter.gwt.demo.contacts.client.ContactsDemo;
import com.toedter.gwt.demo.contacts.client.IClientFactory;
import com.toedter.gwt.demo.contacts.client.mvp.AppPlaceHistoryMapper;
import com.toedter.gwt.demo.contacts.client.place.ContactEditPlace;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ListViewTest extends GwtTest {

	private AppPlaceHistoryMapper appPlaceHistoryMapper = new AppPlaceHistoryMapper() {

		@Override
		public String getToken(Place place) {
			if (place instanceof ContactPlace) {
				String token = ((ContactPlace) place).getToken();
				return token;
			} else if (place instanceof ContactEditPlace) {
				String token = ((ContactEditPlace) place).getToken();
				return token;
			}
			return null;
		}

		@Override
		public Place getPlace(String token) {
			System.out.println("AppPlaceHistoryMapper getPlace: " + token);
			return new ContactPlace(token);
		}
	};

	private class MyGwtCreateHandler implements GwtCreateHandler {

		@Override
		public Object create(Class<?> arg0) throws Exception {
			if (arg0 == AppPlaceHistoryMapper.class) {
				return appPlaceHistoryMapper;
			}
			return null;
		}

	}

	private IClientFactory clientFactory;

	@Override
	public String getModuleName() {
		return "com.toedter.gwt.demo.contacts.ContactsDemo";
	}

	@Before
	public void setupGwtTestSample() {
		addGwtCreateHandler(new MyGwtCreateHandler());

		ContactsDemo contactsDemo = new ContactsDemo();
		contactsDemo.onModuleLoad();
		clientFactory = contactsDemo.getClientFactory();
	}

	@Test
	public void checkClickOnFirstContact() {

		Browser.click(((ToolBarView) clientFactory.getToolBarView()).saveButton);
		IContactListView contactListView = clientFactory.getContactListView();
		CellTable<Contact> table = ((ContactListView2) contactListView).cellTable;

		Browser.click(table, clientFactory.getContacts().get(0));

		String email = ((IContactDetailsView) clientFactory
				.getContactDetailsView()).getContactEmail();

		// After having clicked on the first contact in the list view, the email
		// should be displayed in the email field of the details view
		Assert.assertEquals(clientFactory.getContacts().get(0).getEmail(),
				email);
	}
}
