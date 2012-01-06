package com.toedter.gwt.demo.contacts.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.toedter.gwt.demo.contacts.client.IContactService;
import com.toedter.gwt.demo.contacts.shared.Contact;

@SuppressWarnings("serial")
public class ContactService extends RemoteServiceServlet implements
		IContactService {

	private final ContactManager contactManager;

	public ContactService() {
		contactManager = new ContactManager();
	}

	// @Override
	public List<Contact> getAllContacts() throws IllegalArgumentException {
		return contactManager.getContacts();
	}

	@Override
	public Contact getContact(String email) throws IllegalArgumentException {
		return contactManager.getContact(email);
	}

	@Override
	public String getTest() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return "hello";
	}

}
