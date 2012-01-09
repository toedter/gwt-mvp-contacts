package com.toedter.gwt.demo.contacts.client.ui;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.place.shared.Place;
import com.octo.gwt.test.GwtCreateHandler;
import com.octo.gwt.test.GwtTest;
import com.octo.gwt.test.utils.events.Browser;
import com.toedter.gwt.demo.contacts.client.ContactsDemo;
import com.toedter.gwt.demo.contacts.client.IClientFactory;
import com.toedter.gwt.demo.contacts.client.mvp.AppPlaceHistoryMapper;
import com.toedter.gwt.demo.contacts.client.place.ContactEditPlace;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;

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
	public void checkClickOnSave() {

		Browser.click(((ToolBarView) clientFactory.getToolBarView()).saveButton);

		// Assert
		// Assert.assertTrue(app.dialogBox.isShowing());
		// Assert.assertEquals("", app.errorLabel.getText());
		// Assert.assertEquals("Hello, World!",
		// app.serverResponseLabel.getHTML());
		// Assert.assertEquals("Remote Procedure Call",
		// app.dialogBox.getText());
	}

}
