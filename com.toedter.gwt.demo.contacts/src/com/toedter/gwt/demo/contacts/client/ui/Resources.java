package com.toedter.gwt.demo.contacts.client.ui;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Resources used by the entire application.
 */
public interface Resources extends ClientBundle {
	@Source("images/document-save-3.png")
	ImageResource save();

	@Source("images/user-new-3.png")
	ImageResource addContact();

	@Source("images/user-delete-2.png")
	ImageResource deleteContact();

	@Source("images/5x5.png")
	ImageResource fiveXfive();
}