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
import com.toedter.gwt.demo.contacts.client.mvp.WestActivityMapper;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;
import com.toedter.gwt.demo.contacts.client.ui.ToolBar;

public class ContactsDemo implements EntryPoint {
	private final Place defaultPlace = new ContactPlace(null);

	private final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
	private final SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
	private final SimplePanel centerPanel = new SimplePanel();
	private final SimplePanel westPanel = new SimplePanel();
	private final SimplePanel northPanel = new SimplePanel();

	AcceptsOneWidget westDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
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

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		// northPanel.setStyleName("redBackground");
		// westPanel.setStyleName("yellowBackground");
		centerPanel.setStyleName("greyBackground");

		splitLayoutPanel.addWest(westPanel, 225);
		splitLayoutPanel.add(centerPanel);

		northPanel.add(new ToolBar());
		dockLayoutPanel.addNorth(northPanel, 4.5);
		dockLayoutPanel.add(splitLayoutPanel);

		// Create IClientFactory using deferred binding so it can be replaced
		// with different implementations in gwt.xml
		IClientFactory clientFactory = GWT.create(IClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

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
}
