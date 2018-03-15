package com.management.managers;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.SeatDTO;
import com.management.entities.Seat;
import com.management.fake.SeatRepositoryFake;
import com.management.repositories.SeatRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatManagerTests {
	
	private SeatRepository seatRepository;

	@Test
	public void AddingNewSeat_ReturnsBoolean() {
		//Arrange
		seatRepository = new SeatRepositoryFake();
		
		SeatDTO dto = new SeatDTO();
		dto.setSeatModified(new Date());
		dto.setSeatTaken(true);
		SeatManager manager = new SeatManager(seatRepository);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Seat seat = seatRepository.findOne(0);
		
		Assert.assertEquals(dto.getSeatId(), seat.getSeatId());
		Assert.assertEquals(dto.getSeatModified(), seat.getSeatModified());
		Assert.assertEquals(dto.isSeatTaken(), seat.isSeatTaken());
	}
	
	@Test
	public void DeletingSeat_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		seatRepository = mock.mock(SeatRepository.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (seatRepository).delete(1);
        }});
		
        //Act and assert
        SeatManager manager = new SeatManager(seatRepository);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadSeat_ReturnsSeat() {
		//Arrange
		Mockery mock = new Mockery();
		seatRepository = mock.mock(SeatRepository.class);
		
		final Seat seat = new Seat();
		seat.setSeatModified(new Date());
		seat.setSeatTaken(true);
        
        mock.checking(new Expectations() {{
            oneOf (seatRepository).findOne(1);
            will(returnValue(seat));
        }});
        
        SeatManager manager = new SeatManager(seatRepository);
        
        //Act
        SeatDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getSeatId(), seat.getSeatId());
        Assert.assertEquals(dto.getSeatModified(), seat.getSeatModified());
        Assert.assertEquals(dto.isSeatTaken(), seat.isSeatTaken());
        
        mock.assertIsSatisfied();
	}
}
