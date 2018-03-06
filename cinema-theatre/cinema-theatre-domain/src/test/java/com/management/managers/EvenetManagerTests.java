package com.management.managers;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.EventDTO;
import com.management.entities.Event;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.EventRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class EvenetManagerTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewEvent_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		EventDTO dto = new EventDTO();
		dto.setEventDate(new Date());
		EventManager manager = new EventManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Event event = uow.getEventRepository().Read(dto.getEventId());
		
		Assert.assertEquals(dto.getEventId(), event.getEventId());
	}
	
	@Test
	public void DeletingEvent_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getEventRepository().Delete(1);
        }});
		
        //Act and assert
        EventManager manager = new EventManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadEvent_ReturnsEvent() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final EventRepositoryInterface repository = mock.mock(EventRepositoryInterface.class);
		
		final Event event = new Event();
		event.setEventDate(new Date());
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getEventRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(event));
        }});
        
        EventManager manager = new EventManager(uow);
        
        //Act
        EventDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getEventId(), event.getEventId());
        
        mock.assertIsSatisfied();
	}
}
