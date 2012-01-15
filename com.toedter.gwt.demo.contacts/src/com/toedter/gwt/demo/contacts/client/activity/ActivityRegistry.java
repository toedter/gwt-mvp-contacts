package com.toedter.gwt.demo.contacts.client.activity;

public class ActivityRegistry {
	private static ContactDetailsActivity contactDetailsActivity;
	private static ContactListActivity contactListActivity;
	private static ToolBarActivity ToolbarActivity;

	public static ContactDetailsActivity getContactDetailsActivity() {
		return contactDetailsActivity;
	}

	public static void setContactDetailsActivity(ContactDetailsActivity contactDetailsActivity) {
		ActivityRegistry.contactDetailsActivity = contactDetailsActivity;
	}

	public static ContactListActivity getContactListActivity() {
		return contactListActivity;
	}

	public static void setContactListActivity(ContactListActivity contactListActivity) {
		ActivityRegistry.contactListActivity = contactListActivity;
	}

	public static ToolBarActivity getToolbarActivity() {
		return ToolbarActivity;
	}

	public static void setToolbarActivity(ToolBarActivity toolbarActivity) {
		ToolbarActivity = toolbarActivity;
	}
}
