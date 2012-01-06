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
	}

	void setPresenter(Presenter listener);

	void selectInitialRow(int i);

	void initialize(List<Contact> contacts);
}
