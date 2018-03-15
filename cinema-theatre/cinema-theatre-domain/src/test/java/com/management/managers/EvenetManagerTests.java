package com.management.managers;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.EventDTO;
import com.management.entities.Event;
import com.management.fake.EventRepositoryFake;
import com.management.repositories.EventRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class EvenetManagerTests {
	private EventRepository eventRepository;

	@Test
	public void AddingNewEvent_ReturnsBoolean() {
		//Arrange
		eventRepository = new EventRepositoryFake();
		
		EventDTO dto = new EventDTO();
		dto.setEventDate(new Date());
		EventManager manager = new EventManager(eventRepository);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Event event = eventRepository.findOne(0);
		
		Assert.assertEquals(dto.getEventId(), event.getEventId());
	}
	
	@Test
	public void DeletingEvent_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		eventRepository = mock.mock(EventRepository.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (eventRepository).delete(1);
        }});
		
        //Act and assert
        EventManager manager = new EventManager(eventRepository);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadEvent_ReturnsEvent() {
		//Arrange
		Mockery mock = new Mockery();
		eventRepository = mock.mock(EventRepository.class);
		
		final Event event = new Event();
		event.setEventDate(new Date());
        
        mock.checking(new Expectations() {{
            oneOf (eventRepository).findOne(1);
            will(returnValue(event));
        }});
        
        EventManager manager = new EventManager(eventRepository);
        
        //Act
        EventDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getEventId(), event.getEventId());
        
        mock.assertIsSatisfied();
	}
}
