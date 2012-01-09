package com.toedter.gwt.demo.contacts.client.activity;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.toedter.gwt.demo.contacts.client.IClientFactory;
import com.toedter.gwt.demo.contacts.client.IContactServiceAsync;
import com.toedter.gwt.demo.contacts.client.event.ContactViewEvent;
import com.toedter.gwt.demo.contacts.client.place.ContactPlace;
import com.toedter.gwt.demo.contacts.client.ui.IContactListView;
import com.toedter.gwt.demo.contacts.shared.Contact;

@RunWith(MockitoJUnitRunner.class)
public class ContactListActivityTest {

	@Mock
	private IClientFactory clientFactoryMock;

	@Mock
	private PlaceController placeControllerMock;

	@Mock
	private IContactListView contactListViewMock;

	@Mock
	private AcceptsOneWidget acceptsOneWidgetMock;

	@Mock
	private IContactServiceAsync contactServiceAsyncMock;

	@Mock
	private EventBus eventBusMock;

	private List<Contact> contacts;
	private Contact contact1;
	private Contact contact2;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		when(clientFactoryMock.getPlaceController()).thenReturn(
				placeControllerMock);
		when(clientFactoryMock.getContactListView()).thenReturn(
				contactListViewMock);
		when(clientFactoryMock.getContactService()).thenReturn(
				contactServiceAsyncMock);

		contact1 = new Contact();
		contact1.setEmail("kai@toedter.com");
		contact2 = new Contact();
		contact2.setEmail("test@toedter.com");
		contacts = new ArrayList<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);

		Answer<Void> answer = new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				AsyncCallback<List<Contact>> asyncCallback = (AsyncCallback<List<Contact>>) args[0];
				asyncCallback.onSuccess(contacts);
				return null;
			}
		};

		doAnswer(answer).when(contactServiceAsyncMock).getAllContacts(
				any(AsyncCallback.class));
	}

	@Test
	public void testGotoPlace() {
		ContactListActivity contactListActivity = new ContactListActivity(
				new ContactPlace(null), clientFactoryMock);

		ContactPlace contactPlace = new ContactPlace("kai@toedter.com");
		contactListActivity.goTo(contactPlace);

		verify(placeControllerMock).goTo(contactPlace);
	}

	@Test
	public void testStartWithEmptyToken() {
		ContactListActivity contactListActivity = new ContactListActivity(
				new ContactPlace(""), clientFactoryMock);
		contactListActivity.resetContacts(); // force RPC
		contactListActivity.start(acceptsOneWidgetMock, eventBusMock);

		verify(contactListViewMock).setPresenter(contactListActivity);
		verify(contactListViewMock).initialize(contacts);
	}

	@Test
	public void testStartWithToken() {
		String token = "kai@toedter.com";
		ContactListActivity contactListActivity = new ContactListActivity(
				new ContactPlace(token), clientFactoryMock);
		contactListActivity.resetContacts(); // force RPC
		contactListActivity.start(acceptsOneWidgetMock, eventBusMock);

		verify(contactListViewMock).setPresenter(contactListActivity);
		verify(contactListViewMock).initialize(contacts);
		verify(contactListViewMock).selectInitialContact(contact1);
		verify(eventBusMock).fireEvent(any(ContactViewEvent.class));
	}

	@Test
	public void testMayStop() {
		ContactListActivity contactListActivity = new ContactListActivity(
				new ContactPlace(null), clientFactoryMock);
		contactListActivity.start(acceptsOneWidgetMock, eventBusMock);
		contactListActivity.mayStop();

		verify(contactListViewMock).setPresenter(null);
	}

}
