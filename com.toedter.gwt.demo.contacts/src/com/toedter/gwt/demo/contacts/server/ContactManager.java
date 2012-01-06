package com.toedter.gwt.demo.contacts.server;

import java.util.List;

import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactManager {
	private final List<Contact> contacts;

	public ContactManager() {
		// contacts = new ArrayList<Contact>();
		// contacts.add(new Contact("Kai", "Toedter", "kai@toedter.com"));
		// contacts.add(new Contact("Darren", "Cissell",
		// "darren.cissell@gmail.com"));
		// contacts.add(new Contact("Peter", "Coldwell",
		// "peter.coldwell@gmail.com"));
		// contacts.add(new Contact("Susan", "Maiers",
		// "susan.maiers@gmail.com"));
		contacts = ContactsRepositoryFactory.getContactsRepository()
				.getAllContacts();
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public int getContactIndex(Contact contact) {
		int i = 0;
		for (Contact contactInList : contacts) {
			if (contactInList == contact) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Contact getContact(String email) {
		for (Contact contact : contacts) {
			if (email.equals(contact.getEmail())) {
				return contact;
			}
		}
		return null;
	}
}
