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

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	@Source("images/document-save-3.png")
	ImageResource save();

	@Source("images/user-new-3.png")
	ImageResource addContact();

	@Source("images/user-delete-2.png")
	ImageResource deleteContact();

	@Source("images/preferences-desktop-user-2.png")
	ImageResource dummyContact();

	@Source("images/5x5.png")
	ImageResource fiveXfive();
}