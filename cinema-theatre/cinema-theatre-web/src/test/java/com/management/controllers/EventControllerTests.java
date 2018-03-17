package com.management.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.EventDTO;
import com.management.entities.Event;
import com.management.fake.EventRepositoryFake;
import com.management.managers.EventManager;
import com.management.repositories.EventRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class EventControllerTests {

	private EventRepository eventRepository;

	@Test
	public void AddingNewEvent_ReturnsOK() {
		// Arrange
		eventRepository = new EventRepositoryFake();

		EventDTO dto = new EventDTO();
		dto.setEventDate(new Date());
		EventManager manager = new EventManager(eventRepository);
		EventController controller = new EventController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addEvent(dto), new ResponseEntity<EventDTO>(dto, HttpStatus.OK));
	}

	@Test
	public void DeletingEvent_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		eventRepository = mock.mock(EventRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(eventRepository).delete(1);
			}
		});

		// Act and assert
		EventManager manager = new EventManager(eventRepository);
		EventController controller = new EventController(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteEvent(1), new ResponseEntity<EventDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadEvent_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		eventRepository = mock.mock(EventRepository.class);

		final Event event = new Event();
		event.setEventDate(new Date());

		mock.checking(new Expectations() {
			{
				oneOf(eventRepository).findOne(1);
				will(returnValue(event));
			}
		});

		EventManager manager = new EventManager(eventRepository);
		EventController controller = new EventController(manager);

		// Act
		ResponseEntity<EventDTO> response = controller.getEvent(1);
		EventDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<EventDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllEvents_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		eventRepository = mock.mock(EventRepository.class);

		final ArrayList<Event> list = new ArrayList<Event>();

		Event event1 = new Event();
		event1.setEventDate(new Date());

		Event event2 = new Event();
		event2.setEventDate(new Date());

		list.add(event1);
		list.add(event2);

		mock.checking(new Expectations() {
			{
				oneOf(eventRepository).findAll();
				will(returnValue(list));
			}
		});

		EventManager manager = new EventManager(eventRepository);
		EventController controller = new EventController(manager);

		// Act
		ResponseEntity<List<EventDTO>> response = controller.getEvents();
		ArrayList<EventDTO> listDTO = (ArrayList<EventDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<EventDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
