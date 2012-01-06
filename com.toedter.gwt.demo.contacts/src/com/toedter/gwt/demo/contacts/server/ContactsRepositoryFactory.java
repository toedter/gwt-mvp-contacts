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

public class ContactsRepositoryFactory {

	private static final IContactsRepository CONTACTS_REPOSITORY = new VCardContactsRepository();

	public static IContactsRepository getContactsRepository() {
		return CONTACTS_REPOSITORY;
	}
}
