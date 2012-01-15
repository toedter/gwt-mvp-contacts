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

package com.toedter.gwt.demo.contacts.server;

import java.util.List;

import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactManager {
	private List<Contact> contacts;
	private final IContactsRepository contactsRepository;

	public ContactManager() {
		contactsRepository = ContactsRepositoryFactory.getContactsRepository();
		contacts = contactsRepository.getAllContacts();
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void addContact(Contact contact) {
		contactsRepository.addContact(contact);
		saveContact(contact);
	}

	public void saveContact(Contact contact) {
		contactsRepository.saveContact(contact);
		contacts = contactsRepository.getAllContacts();
	}

	public void deleteContact(Contact contact) {
		Contact originalContact = getContactByFileName(contact.getFileName());
		if (originalContact != null) {
			contactsRepository.removeContact(originalContact);
			contacts = contactsRepository.getAllContacts();
		}
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

	public Contact getContactByEmail(String email) {
		for (Contact contact : contacts) {
			if (email.equals(contact.getEmail())) {
				return contact;
			}
		}
		return null;
	}

	public Contact getContactByFileName(String fileName) {
		for (Contact contact : contacts) {
			if (fileName.equals(contact.getFileName())) {
				return contact;
			}
		}
		return null;
	}

}
