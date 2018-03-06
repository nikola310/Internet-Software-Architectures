package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.HallDTO;
import com.management.entities.Hall;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.HallRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class HallManagerTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewHall_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		HallDTO dto = new HallDTO();
		dto.setHallName("A1");
		HallManager manager = new HallManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Hall hall = uow.getHallRepository().Read(dto.getHallId());
		
		Assert.assertEquals(dto.getHallId(), hall.getHallId());
		Assert.assertEquals(dto.getHallName(), hall.getHallName());
	}
	
	@Test
	public void DeletingHall_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getHallRepository().Delete(1);
        }});
		
        //Act and assert
        HallManager manager = new HallManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadHall_ReturnsHall() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final HallRepositoryInterface repository = mock.mock(HallRepositoryInterface.class);
		
		final Hall hall = new Hall();
		hall.setHallName("A1");
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getHallRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(hall));
        }});
        
        HallManager manager = new HallManager(uow);
        
        //Act
        HallDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getHallId(), hall.getHallId());
        Assert.assertEquals(dto.getHallName(), hall.getHallName());
        
        mock.assertIsSatisfied();
	}
}
