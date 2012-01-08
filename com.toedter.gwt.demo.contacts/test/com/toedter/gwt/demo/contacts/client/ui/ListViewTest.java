package com.toedter.gwt.demo.contacts.client.ui;

import org.junit.Before;
import org.junit.Test;

import com.octo.gwt.test.GwtTest;
import com.octo.gwt.test.utils.events.Browser;
import com.toedter.gwt.demo.contacts.client.ContactsDemo;
import com.toedter.gwt.demo.contacts.client.IClientFactory;

public class ListViewTest extends GwtTest {

	private IClientFactory clientFactory;

	@Override
	public String getModuleName() {
		return "com.toedter.gwt.demo.contacts.ContactsDemo";
	}

	@Before
	public void setupGwtTestSample() {
		ContactsDemo contactsDemo = new ContactsDemo();
		contactsDemo.onModuleLoad();
		clientFactory = contactsDemo.getClientFactory();

		// Some pre-assertions
	}

	@Test
	public void checkClickOnSendMoreThan4chars() {
		// Act
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
