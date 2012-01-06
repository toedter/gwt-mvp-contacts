package com.toedter.gwt.demo.contacts.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.toedter.gwt.demo.contacts.shared.Contact;

public interface IContactServiceAsync {

	void getTest(AsyncCallback<String> callback);

	void getAllContacts(AsyncCallback<List<Contact>> callback);

	void getContact(String email, AsyncCallback<Contact> callback);

}
