package com.toedter.gwt.demo.contacts.client.ui;

import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactListView2 extends Composite implements IContactListView {

	private static ContactListView2UiBinder uiBinder = GWT.create(ContactListView2UiBinder.class);

	@UiField(provided = true)
	CellTable<Contact> cellTable = new CellTable<Contact>(10, (Resources) GWT.create(TableResources.class));

	private List<Contact> list;

	private Presenter presenter;

	interface ContactListView2UiBinder extends UiBinder<Widget, ContactListView2> {
	}

	public ContactListView2() {
		initWidget(uiBinder.createAndBindUi(this));

		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Create first name column.
		TextColumn<Contact> firstNameColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact contact) {
				return contact.getFirstName();
			}
		};

		// Make the first name column sortable.
		firstNameColumn.setSortable(true);

		// Create last name column.
		TextColumn<Contact> lastNameColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact contact) {
				return contact.getLastName();
			}
		};

		// Make the last name column sortable.
		lastNameColumn.setSortable(true);

		// Add the columns.
		cellTable.addColumn(firstNameColumn, "First Name");
		cellTable.addColumn(lastNameColumn, "Last Name");

		// Create a data provider.
		ListDataProvider<Contact> dataProvider = new ListDataProvider<Contact>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(cellTable);

		list = dataProvider.getList();

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Contact> columnSortHandler = new ListHandler<Contact>(list);
		columnSortHandler.setComparator(firstNameColumn, new Comparator<Contact>() {
			@Override
			public int compare(Contact o1, Contact o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the first name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getFirstName().compareTo(o2.getFirstName()) : 1;
				}
				return -1;
			}
		});
		columnSortHandler.setComparator(lastNameColumn, new Comparator<Contact>() {
			@Override
			public int compare(Contact o1, Contact o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the last name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getLastName().compareTo(o2.getLastName()) : 1;
				}
				return -1;
			}
		});
		cellTable.addColumnSortHandler(columnSortHandler);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Contact> selectionModel = new SingleSelectionModel<Contact>();
		cellTable.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Contact selectedContact = selectionModel.getSelectedObject();
				presenter.select(selectedContact);
			}
		});

		System.out.println("STYLE: " + CellTable.Style.DEFAULT_CSS);
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
		for (Contact contact : contacts) {
			list.add(contact);
		}
		// sort first names
		cellTable.getColumnSortList().push(cellTable.getColumn(0));

	}

	@Override
	public void selectInitialContact(Contact contact) {
		cellTable.getSelectionModel().setSelected(contact, true);
	}

}
