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

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.toedter.gwt.demo.contacts.shared.Contact;

class ContactCell extends AbstractCell<Contact> {
	@Override
	public void render(Context context, Contact value, SafeHtmlBuilder sb) {
		if (value != null) {
			sb.appendEscaped(value.getFirstName() + " " + value.getLastName());
		}
	}
}

public class ContactListView3 extends Composite implements IContactListView {

	private final CellList<Contact> cellList;

	private Presenter presenter;

	public ContactListView3() {
		ContactCell contactCell = new ContactCell();
		cellList = new CellList<Contact>(contactCell);

		cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Contact> selectionModel = new SingleSelectionModel<Contact>();
		cellList.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Contact selectedContact = selectionModel.getSelectedObject();
				System.out.println("ContactListView2 .onSelectionChange()");
				presenter.select(selectedContact);
			}
		});
		initWidget(cellList);
	}

	@Override
	public void setWidget(IsWidget w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void selectInitialRow(int i) {
		// not used, use setInitialContact instead
	}

	@Override
	public void initialize(List<Contact> contacts) {
		cellList.setRowCount(contacts.size(), true);
		cellList.setRowData(0, contacts);
	}

	@Override
	public void selectInitialContact(Contact contact) {
		cellList.getSelectionModel().setSelected(contact, true);
	}

}
