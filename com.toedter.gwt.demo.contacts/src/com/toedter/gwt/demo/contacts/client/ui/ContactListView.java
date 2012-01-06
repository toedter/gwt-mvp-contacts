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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactListView extends ResizeComposite implements IContactListView {

	private static ContactListViewUiBinder uiBinder = GWT.create(ContactListViewUiBinder.class);

	interface SelectionStyle extends CssResource {
		String selectedRow();
	}

	private Presenter presenter;

	@UiField
	FlexTable header;

	@UiField
	FlexTable table;

	@UiField
	SelectionStyle selectionStyle;

	private int selectedRow = -1;

	interface ContactListViewUiBinder extends UiBinder<Widget, ContactListView> {
	}

	public ContactListView() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	private void initTable() {
		// Initialize the header.
		header.getColumnFormatter().setWidth(0, "100px");
		header.getColumnFormatter().setWidth(1, "100px");

		header.setText(0, 0, "First Name");
		header.setText(0, 1, "Last Name");
		header.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_RIGHT);

		// Initialize the table.
		table.getColumnFormatter().setWidth(0, "100px");
		table.getColumnFormatter().setWidth(1, "100px");
	}

	@UiHandler("table")
	void onTableClicked(ClickEvent event) {
		// Select the row that was clicked (-1 to account for header row).
		Cell cell = table.getCellForEvent(event);
		if (cell != null) {
			int row = cell.getRowIndex();
			selectRow(row, true);
		}
	}

	@Override
	public void selectInitialRow(int row) {
		selectRow(row, false);
	}

	private void selectRow(int row, boolean notifyPresenter) {
		System.out.println("ContactListView.selectRow(): " + row);
		styleRow(selectedRow, false);
		styleRow(row, true);

		selectedRow = row;
		if (notifyPresenter) {
			presenter.select(row);
		}
	}

	private void styleRow(int row, boolean selected) {
		if (row != -1) {
			String style = selectionStyle.selectedRow();

			if (selected) {
				table.getRowFormatter().addStyleName(row, style);
			} else {
				table.getRowFormatter().removeStyleName(row, style);
			}
		}
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setWidget(IsWidget w) {
		System.out.println("ContactListView.setWidget()");
	}

	@Override
	public void initialize(List<Contact> contacts) {
		int i = 0;
		for (Contact contact : contacts) {
			table.setText(i, 0, contact.getFirstName());
			table.setText(i, 1, contact.getLastName());
			i++;
		}
	}

	@Override
	public void selectInitialContact(Contact contact) {
		// Not used, use setInitialRow instead
	}
}
