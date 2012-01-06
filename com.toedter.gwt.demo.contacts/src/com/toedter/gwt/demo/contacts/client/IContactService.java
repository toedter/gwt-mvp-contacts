package com.toedter.gwt.demo.contacts.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.toedter.gwt.demo.contacts.shared.Contact;

@RemoteServiceRelativePath("contact")
public interface IContactService extends RemoteService {
	List<Contact> getAllContacts() throws IllegalArgumentException;

	Contact getContact(String email) throws IllegalArgumentException;

	String getTest() throws IllegalArgumentException;
}
