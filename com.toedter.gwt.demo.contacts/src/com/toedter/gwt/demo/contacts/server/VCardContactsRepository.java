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

package com.toedter.gwt.demo.contacts.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.toedter.gwt.demo.contacts.shared.Contact;

public class VCardContactsRepository implements IContactsRepository {

	private final List<Contact> contacts;

	public VCardContactsRepository() {
		contacts = new ArrayList<Contact>();
		try {
			for (File file : getContacts()) {
				Contact contact = readFromVCard(file.getAbsolutePath());
				contacts.add(contact);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private File[] getContacts() throws Exception {
		File directory = new File("vcards");
		System.out.println("VCardContactsRepository.getContacts(): " + directory.getCanonicalPath());
		return directory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".vcf"); //$NON-NLS-1$
			}
		});
	}

	@Override
	public void addContact(final Contact contact) {
		contacts.add(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		return contacts;
	}

	@Override
	public void removeContact(final Contact contact) {
		contacts.remove(contact);
	}

	/**
	 * Reads a Contact from a VCard. This method cannot parse a generic VCard,
	 * but can only parse VCards created with Microsoft Outlook. The intention
	 * is not to provide a generic VCard reader but an easy way to get contact
	 * data (including pictures) in the repository.
	 * 
	 * @param fileName
	 *            the vcard file
	 * @return the created Contact
	 */
	public Contact readFromVCard(String fileName) {
		Contact contact = new Contact();
		BufferedReader bufferedReader = null;
		String charSet = "Cp1252";

		// parse the vCard
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName), charSet);
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			String value;
			while ((line = bufferedReader.readLine()) != null) {
				value = getVCardValue(line, "N");
				if (value != null) {
					String[] result = value.split(";");

					if (result.length > 0) {
						contact.setLastName(result[0]);
					}
					if (result.length > 1) {
						contact.setFirstName(result[1]);
					}
					if (result.length > 2) {
						contact.setMiddleName(result[2]);
					}
					if (result.length > 3) {
						contact.setTitle(result[3]);
					}
					continue;
				}
				value = getVCardValue(line, "TEL;WORK");
				if (value != null) {
					contact.setPhone(value);
					continue;
				}
				value = getVCardValue(line, "TEL;CELL");
				if (value != null) {
					contact.setMobile(value);
					continue;
				}
				value = getVCardValue(line, "ADR;WORK");
				if (value != null) {
					String[] result = value.split(";");

					if (result.length > 2) {
						contact.setStreet(result[2]);
					}
					if (result.length > 3) {
						contact.setCity(result[3]);
					}
					if (result.length > 4) {
						contact.setState(result[4]);
					}
					if (result.length > 5) {
						contact.setZip(result[5]);
					}
					if (result.length > 6) {
						contact.setCountry(result[6]);
					}
					continue;
				}
				value = getVCardValue(line, "EMAIL;PREF;INTERNET");
				if (value != null) {
					contact.setEmail(value);
					continue;
				}
				value = getVCardValue(line, "URL;WORK");
				if (value != null) {
					contact.setWebPage(value);
					continue;
				}
				value = getVCardValue(line, "ORG");
				if (value != null) {
					contact.setCompany(value);
					continue;
				}
				value = getVCardValue(line, "TITLE");
				if (value != null) {
					contact.setJobTitle(value);
					continue;
				}
				value = getVCardValue(line, "NOTE");
				if (value != null) {
					contact.setNote(value);
					continue;
				}
				value = getVCardValue(line, "PHOTO;TYPE=JPEG;ENCODING=BASE64");
				if (value != null) {
					line = bufferedReader.readLine();
					StringBuilder builder = new StringBuilder();
					while (line != null && line.length() > 0 && line.charAt(0) == ' ') {
						builder.append(line.trim());
						line = bufferedReader.readLine();
					}
					String jpegString = builder.toString();

					String base64 = "data:image/png;base64," + jpegString;
					contact.setJpegString(base64);
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contact;
	}

	private String getVCardValue(String line, String token) {
		if (line.startsWith(token + ":") || line.startsWith(token + ";")) {
			String value = line.substring(line.indexOf(":") + 1);
			return value;
		}
		return null;
	}
}
