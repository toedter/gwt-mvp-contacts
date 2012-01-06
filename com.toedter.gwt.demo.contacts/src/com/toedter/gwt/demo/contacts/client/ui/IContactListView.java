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

package com.toedter.gwt.demo.contacts.client.ui;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.toedter.gwt.demo.contacts.shared.Contact;

public interface IContactListView extends IsWidget, AcceptsOneWidget {
	public interface Presenter {
		void goTo(Place place);

		void select(int index);

		void select(Contact contact);
	}

	void setPresenter(Presenter presenter);

	void selectInitialRow(int i);

	void selectInitialContact(Contact contact);

	void initialize(List<Contact> contacts);
}
