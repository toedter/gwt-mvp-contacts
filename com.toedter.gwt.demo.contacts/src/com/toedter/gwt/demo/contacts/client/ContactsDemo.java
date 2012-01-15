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

package com.toedter.gwt.demo.contacts.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.toedter.gwt.demo.contacts.client.mvp.AppPlaceHistoryMapper;
import com.toedter.gwt.demo.contacts.client.mvp.CenterActivityMapper;
import com.toedter.gwt.demo.contacts.client.mvp.NorthActivityMapper;
import com.toedter.gwt.demo.contacts.client.mvp.WestActivityMapper;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;

public class ContactsDemo implements EntryPoint {
	private final Place defaultPlace = new ContactPlace("");

	private final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
	private final SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
	private final SimplePanel centerPanel = new SimplePanel();
	private final SimplePanel westPanel = new SimplePanel();
	private final SimplePanel northPanel = new SimplePanel();

	AcceptsOneWidget northDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			northPanel.setVisible(widget != null);
			northPanel.setWidget(widget);
		}
	};

	AcceptsOneWidget westDisplay = new AcceptsOneWidget() {
		double oldSize = 223.0; // TODO store real size dynamically

		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			if (widget == null) {
				splitLayoutPanel.setWidgetSize(westPanel, 0);
			} else {
				splitLayoutPanel.setWidgetSize(westPanel, oldSize);
			}
			westPanel.setVisible(widget != null);
			westPanel.setWidget(widget);
		}
	};

	AcceptsOneWidget centerDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			centerPanel.setVisible(widget != null);
			centerPanel.setWidget(widget);
		}
	};

	private IClientFactory clientFactory;

	@Override
	public void onModuleLoad() {
		northPanel.setStyleName("darkGreyBackground");
		westPanel.setStyleName("greyBackground");
		centerPanel.setStyleName("greyBackground");

		splitLayoutPanel.addWest(westPanel, 223);
		splitLayoutPanel.add(centerPanel);
		splitLayoutPanel.setStyleName("gwt-SplitLayoutPanel");

		dockLayoutPanel.addNorth(northPanel, 4.5);
		dockLayoutPanel.add(splitLayoutPanel);

		clientFactory = GWT.create(IClientFactory.class);

		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		// Start NorthActivityManager for the north widget with the
		// NorthActivityMapper
		ActivityMapper northActivityMapper = new NorthActivityMapper(clientFactory);
		ActivityManager northActivityManager = new ActivityManager(northActivityMapper, eventBus);
		northActivityManager.setDisplay(northDisplay);

		// Start CenterActivityManager for the center widget with the
		// CenterActivityMapper
		ActivityMapper centerActivityMapper = new CenterActivityMapper(clientFactory);
		ActivityManager centerActivityManager = new ActivityManager(centerActivityMapper, eventBus);
		centerActivityManager.setDisplay(centerDisplay);

		// Start WestActivityManager for the west widget with the
		// WestActivityMapper
		ActivityMapper westActivityMapper = new WestActivityMapper(clientFactory);
		ActivityManager westActivityManager = new ActivityManager(westActivityMapper, eventBus);
		westActivityManager.setDisplay(westDisplay);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootLayoutPanel.get().add(dockLayoutPanel);

		// Goes to place represented on URL or default place
		historyHandler.handleCurrentHistory();
	}

	/*
	 * For better UI testability with gwt-test-utils
	 */
	public IClientFactory getClientFactory() {
		return clientFactory;
	}
}
