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

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.toedter.gwt.demo.contacts.shared.Contact;

public interface IContactDetailsView extends IsWidget {

	void setContact(Contact contact);

	Contact getContact();

	void setPresenter(Presenter presenter);

	void clear();

	String getContactEmail();

	public interface Presenter {
		void goTo(Place place);

		Contact getContact();
	}
}
