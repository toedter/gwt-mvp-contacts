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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactDetailsView extends Composite implements
		IContactDetailsView {

	interface Binder extends UiBinder<Widget, ContactDetailsView> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	Grid grid;
	@UiField
	TextBox nameText;
	@UiField
	TextBox companyText;
	@UiField
	TextBox jobTitleText;
	@UiField
	TextBox countryText;
	@UiField
	TextBox zipText;
	@UiField
	TextBox cityText;
	@UiField
	TextBox streetText;

	private Presenter presenter;

	public ContactDetailsView() {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setContact(Contact contact) {
		nameText.setText(contact.getFirstName() + " " + contact.getLastName());
	}

	@Override
	public String getContactEmail() {
		return "";
	}
}
