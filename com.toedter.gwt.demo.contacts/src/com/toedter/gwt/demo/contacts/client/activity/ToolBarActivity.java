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

package com.toedter.gwt.demo.contacts.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.toedter.gwt.demo.contacts.client.IClientFactory;
import com.toedter.gwt.demo.contacts.client.place.ContactEditPlace;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;
import com.toedter.gwt.demo.contacts.client.ui.IToolBarView;
import com.toedter.gwt.demo.contacts.client.ui.IToolBarView.Presenter;

public class ToolBarActivity extends AbstractActivity implements Presenter {

	private EventBus eventBus;
	private final String token;
	private final IClientFactory clientFactory;
	private IToolBarView toolBarView;

	public ToolBarActivity(ContactPlace place, IClientFactory clientFactory) {
		System.out.println("ToolBarActivity.ToolBarActivity(): "
				+ place.getToken());
		token = place.getToken();
		this.clientFactory = clientFactory;
	}

	public ToolBarActivity(ContactEditPlace place, IClientFactory clientFactory) {
		System.out.println("ToolBarActivity.ToolBarActivity(): "
				+ place.getToken());
		token = place.getToken();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		System.out.println("ToolBarActivity.start()");
		this.eventBus = eventBus;
		toolBarView = clientFactory.getToolBarView();
		toolBarView.setPresenter(this);
		containerWidget.setWidget(toolBarView.asWidget());
	}

	@Override
	public String mayStop() {
		toolBarView.setPresenter(null);
		return null;
	}

	@Override
	public void addContact() {
		clientFactory.getPlaceController().goTo(new ContactEditPlace(null));
	}

	@Override
	public void save() {
		System.out.println("ToolBarActivity.save(): Not implemented yet");
		clientFactory.getPlaceController().goTo(new ContactPlace(""));
	}

}
