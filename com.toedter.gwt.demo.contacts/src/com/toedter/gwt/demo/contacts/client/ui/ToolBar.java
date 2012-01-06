package com.toedter.gwt.demo.contacts.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class ToolBar extends Composite {

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	PushButton saveButton;
	@UiField
	PushButton addContactButton;

	interface Binder extends UiBinder<Widget, ToolBar> {
	}

	public ToolBar() {
		initWidget(binder.createAndBindUi(this));
	}

}
