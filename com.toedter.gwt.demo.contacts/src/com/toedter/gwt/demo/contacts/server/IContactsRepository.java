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

public interface IContactsRepository {
	List<Contact> getAllContacts();

	void addContact(Contact contact);

	void removeContact(Contact contact);

	void saveContact(Contact contact);
}
