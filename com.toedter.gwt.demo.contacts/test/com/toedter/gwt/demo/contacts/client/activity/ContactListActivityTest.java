package com.toedter.gwt.demo.contacts.client.activity;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
		when(clientFactoryMock.getPlaceController()).thenReturn(placeControllerMock);
		when(clientFactoryMock.getContactListView()).thenReturn(contactListViewMock);
		when(clientFactoryMock.getContactService()).thenReturn(contactServiceAsyncMock);

		Answer<Void> answer = new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				AsyncCallback<List<Contact>> asyncCallback = (AsyncCallback<List<Contact>>) args[0];
				contact1 = new Contact();
				contact1.setFirstName("Kai");
				contact1.setLastName("Toedter");
				contact1.setEmail("kai@toedter.com");
				contact2 = new Contact();
				contact2.setFirstName("Kai2");
				contact2.setLastName("Toedter2");
				contact2.setEmail("kai2@toedter.com");
				final List<Contact> contacts2 = new ArrayList<Contact>();
				contacts2.add(contact1);
				contacts2.add(contact2);
				asyncCallback.onSuccess(contacts2);
				return null;
			}
		};

		doAnswer(answer).when(contactServiceAsyncMock).getAllContacts(any(AsyncCallback.class));

		// set the real contacts object, when clientFactory.setContacts is
		// called
		Answer<Void> setContactsAnswer = new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				contacts = (List<Contact>) invocation.getArguments()[0];
				// System.out.println("answer() to setContacts(): " + contacts);
				return null;
			}
		};

		doAnswer(setContactsAnswer).when(clientFactoryMock).setContacts(any(List.class));

		// Return the real contacts object, when clientFactory.getContacts is
		// called
		Answer<List<Contact>> getContactsAnswer = new Answer<List<Contact>>() {
			@Override
			public List<Contact> answer(InvocationOnMock invocation) throws Throwable {
				return contacts;
			}
		};

		doAnswer(getContactsAnswer).when(clientFactoryMock).getContacts();
	}

	@Test
	public void testGotoPlace() {
		ContactListActivity contactListActivity = new ContactListActivity(new ContactPlace(null), clientFactoryMock);

		ContactPlace contactPlace = new ContactPlace("kai@toedter.com");
		contactListActivity.goTo(contactPlace);

		verify(placeControllerMock).goTo(contactPlace);
	}

	@Test
	public void testStartWithEmptyToken() {
		clientFactoryMock.setContacts(null); // force RCP
		ContactListActivity contactListActivity = new ContactListActivity(new ContactPlace(""), clientFactoryMock);
		contactListActivity.start(acceptsOneWidgetMock, eventBusMock);

		verify(contactListViewMock).setPresenter(contactListActivity);
		verify(contactListViewMock).initialize(contacts);
	}

	@Test
	public void testStartWithToken() {
		String token = "kai@toedter.com";
		clientFactoryMock.setContacts(null); // force RCP

		ContactListActivity contactListActivity = new ContactListActivity(new ContactPlace(token), clientFactoryMock);
		contactListActivity.start(acceptsOneWidgetMock, eventBusMock);

		verify(contactListViewMock).setPresenter(contactListActivity);
		verify(contactListViewMock).initialize(contacts);
		verify(contactListViewMock).selectInitialContact(contact1);
		verify(eventBusMock).fireEvent(any(ContactViewEvent.class));
	}

	@Test
	public void testMayStop() {
		ContactListActivity contactListActivity = new ContactListActivity(new ContactPlace(null), clientFactoryMock);
		contactListActivity.start(acceptsOneWidgetMock, eventBusMock);
		contactListActivity.mayStop();

		verify(contactListViewMock).setPresenter(null);
	}

	@Test
	public void clientFactoryTest() {
		List<Contact> testList = new ArrayList<Contact>();
		clientFactoryMock.setContacts(testList);
		Assert.assertNotNull(clientFactoryMock.getContacts());
	}
}
