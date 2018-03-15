package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.HallDTO;
import com.management.entities.Hall;
import com.management.fake.HallRepositoryFake;
import com.management.repositories.HallRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class HallManagerTests {
	private HallRepository hallRepository;

	@Test
	public void AddingNewHall_ReturnsBoolean() {
		//Arrange
		hallRepository = new HallRepositoryFake();
		
		HallDTO dto = new HallDTO();
		dto.setHallName("A1");
		HallManager manager = new HallManager(hallRepository);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Hall hall = hallRepository.findOne(0);
		
		Assert.assertEquals(dto.getHallId(), hall.getHallId());
		Assert.assertEquals(dto.getHallName(), hall.getHallName());
	}
	
	@Test
	public void DeletingHall_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		hallRepository = mock.mock(HallRepository.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (hallRepository).delete(1);
        }});
		
        //Act and assert
        HallManager manager = new HallManager(hallRepository);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadHall_ReturnsHall() {
		//Arrange
		Mockery mock = new Mockery();
		hallRepository = mock.mock(HallRepository.class);
		
		final Hall hall = new Hall();
		hall.setHallName("A1");
        
        mock.checking(new Expectations() {{
            oneOf (hallRepository).findOne(1);
            will(returnValue(hall));
        }});
        
        HallManager manager = new HallManager(hallRepository);
        
        //Act
        HallDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getHallId(), hall.getHallId());
        Assert.assertEquals(dto.getHallName(), hall.getHallName());
        
        mock.assertIsSatisfied();
	}
}