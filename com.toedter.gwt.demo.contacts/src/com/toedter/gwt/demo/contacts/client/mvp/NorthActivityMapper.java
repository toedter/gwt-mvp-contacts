package com.toedter.gwt.demo.contacts.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.toedter.gwt.demo.contacts.client.IClientFactory;
import com.toedter.gwt.demo.contacts.client.activity.ToolBarActivity;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;

public class NorthActivityMapper implements ActivityMapper {

	private final IClientFactory clientFactory;

	public NorthActivityMapper(IClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		System.out.println("NorthActivityMapper.getActivity(): " + place);
		if (place instanceof ContactPlace) {
			return new ToolBarActivity((ContactPlace) place, clientFactory);
		}

		return null;
	}

}
