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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactDetailsView2 implements IContactDetailsView {

	private final FlexTable flexTable;
	private int row;
	private int colSpan;
	private final TextBox titleText;
	private final TextBox nameText;
	private final TextBox companyText;
	private final TextBox jobTitleText;
	private final TextBox streetText;
	private final TextBox zipText;
	private final TextBox cityText;
	private final TextBox countryText;
	private final TextBox emailText;
	private final TextBox webText;
	private final Image image;

	public ContactDetailsView2() {
		Resources resources = GWT.create(Resources.class);
		colSpan = 2;
		flexTable = new FlexTable();
		// flexTable.setStyleName("greyBackground");
		flexTable.setWidget(1, 4, new Image(resources.fiveXfive()));
		flexTable.getFlexCellFormatter().setWidth(1, 4, "5px");
		flexTable.setWidget(1, 6, new Image(resources.fiveXfive()));
		flexTable.getFlexCellFormatter().setWidth(1, 6, "5px");

		image = new Image(resources.dummyContact());

		flexTable.setWidget(1, 5, image);
		flexTable.getFlexCellFormatter().setRowSpan(1, 5, 4);
		flexTable.getFlexCellFormatter().setVerticalAlignment(1, 5, HasVerticalAlignment.ALIGN_TOP);

		createSeparator("General");
		titleText = createTextBox("Title:");
		nameText = createTextBox("Name:");
		companyText = createTextBox("Company:");
		jobTitleText = createTextBox("Job Title:");

		colSpan = 4;
		createSeparator("Business Address");
		streetText = createTextBox("Street:");
		cityText = createTextBox("City:");
		zipText = createTextBox("Zip:");
		countryText = createTextBox("Country:");

		createSeparator("Business Internet");
		emailText = createTextBox("Email:");
		webText = createTextBox("Web:");

	}

	private TextBox createTextBox(String text) {
		flexTable.setText(row, 2, text);
		flexTable.getFlexCellFormatter().setHorizontalAlignment(row, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		TextBox textBox = new TextBox();
		textBox.setWidth("100%");
		flexTable.setWidget(row, 3, textBox);
		flexTable.getFlexCellFormatter().setColSpan(row, 3, colSpan);
		flexTable.getFlexCellFormatter().setWidth(row++, 3, "100%");

		return textBox;
	}

	private void createSeparator(String text) {
		flexTable.setText(row, 1, text);
		flexTable.getFlexCellFormatter().setStyleName(row, 1, "separatorLabel");
		flexTable.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		flexTable.getFlexCellFormatter().setHeight(row, 1, "25px");
		flexTable.getFlexCellFormatter().setColSpan(row++, 1, 3);
	}

	@Override
	public Widget asWidget() {
		return flexTable;
	}

	@Override
	public void setContact(Contact contact) {
		if (contact == null) {
			return;
		}

		titleText.setText(contact.getTitle());
		nameText.setText(contact.getFirstName() + " " + contact.getLastName());
		companyText.setText(contact.getCompany());
		jobTitleText.setText(contact.getJobTitle());
		streetText.setText(contact.getStreet());
		cityText.setText(contact.getCity());
		zipText.setText(contact.getZip());
		countryText.setText(contact.getCountry());
		emailText.setText(contact.getEmail());
		webText.setText(contact.getWebPage());

		Image image = new Image(contact.getJpegString());
		flexTable.setWidget(1, 5, image);
	}

	@Override
	public void setPresenter(Presenter listener) {
	}

	@Override
	public String getContactEmail() {
		return emailText.getText();
	}

}
