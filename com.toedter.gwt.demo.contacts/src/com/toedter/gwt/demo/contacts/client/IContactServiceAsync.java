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

package com.toedter.gwt.demo.contacts.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.toedter.gwt.demo.contacts.shared.Contact;

public interface IContactServiceAsync {

	void getTest(AsyncCallback<String> callback);

	void getAllContacts(AsyncCallback<List<Contact>> callback);

	void getContact(String email, AsyncCallback<Contact> callback);

}
