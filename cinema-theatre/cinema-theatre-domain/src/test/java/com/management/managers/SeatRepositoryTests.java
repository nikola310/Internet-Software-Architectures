package com.management.managers;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.SeatDTO;
import com.management.entities.Seat;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.SeatRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatRepositoryTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewSeat_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		SeatDTO dto = new SeatDTO();
		dto.setSeatModified(new Date());
		dto.setSeatTaken(true);
		SeatManager manager = new SeatManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Seat seat = uow.getSeatRepository().Read(dto.getSeatId());
		
		Assert.assertEquals(dto.getSeatId(), seat.getSeatId());
		Assert.assertEquals(dto.getSeatModified(), seat.getSeatModified());
		Assert.assertEquals(dto.isSeatTaken(), seat.isSeatTaken());
	}
	
	@Test
	public void DeletingSeat_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getSeatRepository().Delete(1);
        }});
		
        //Act and assert
        SeatManager manager = new SeatManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadSeat_ReturnsSeat() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final SeatRepositoryInterface repository = mock.mock(SeatRepositoryInterface.class);
		
		final Seat seat = new Seat();
		seat.setSeatModified(new Date());
		seat.setSeatTaken(true);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getSeatRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(seat));
        }});
        
        SeatManager manager = new SeatManager(uow);
        
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
