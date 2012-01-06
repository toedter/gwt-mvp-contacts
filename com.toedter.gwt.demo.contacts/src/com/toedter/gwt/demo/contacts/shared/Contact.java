/*******************************************************************************
 * Copyright (c) 2009 Siemens AG and others.
 * 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Kai Tödter - initial implementation
 ******************************************************************************/

package com.toedter.gwt.demo.contacts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Contact implements Serializable {

	private String sourceFile = null;
	private String firstName = ""; //$NON-NLS-1$
	private String middleName = ""; //$NON-NLS-1$
	private String lastName = ""; //$NON-NLS-1$
	private String title = ""; //$NON-NLS-1$
	private String company = ""; //$NON-NLS-1$
	private String jobTitle = ""; //$NON-NLS-1$
	private String street = ""; //$NON-NLS-1$
	private String city = ""; //$NON-NLS-1$
	private String zip = ""; //$NON-NLS-1$
	private String state = ""; //$NON-NLS-1$
	private String country = ""; //$NON-NLS-1$
	private String email = ""; //$NON-NLS-1$
	private String webPage = ""; //$NON-NLS-1$
	private String phone = ""; //$NON-NLS-1$
	private String mobile = ""; //$NON-NLS-1$
	private String note = ""; //$NON-NLS-1$
	private String jpegString = ""; //$NON-NLS-1$

	public Contact() {
	}

	public Contact(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String businessCity) {
		city = businessCity;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String businessZip) {
		zip = businessZip;
	}

	public String getState() {
		return state;
	}

	public void setState(String businessState) {
		state = businessState;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String businessCountry) {
		country = businessCountry;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String businessEmail) {
		email = businessEmail;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String businessWebPage) {
		webPage = businessWebPage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String businessPhone) {
		phone = businessPhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String businessMobile) {
		mobile = businessMobile;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String comment) {
		note = comment;
	}

	public void setJpegString(String jpegString) {
		this.jpegString = jpegString;
	}

	public String getJpegString() {
		return jpegString;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
