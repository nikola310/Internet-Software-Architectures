package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.FanZoneDTO;
import com.management.entities.FanZone;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.FanZoneRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class FanZoneManagerTest {
	private UnitOfWorkInterface uow;
	
	@Test
	public void AddingFanZone_ReturnsBoolean(){
		// Arrange
		uow = new UnitOfWorkFake();
		
		FanZoneDTO dto = new FanZoneDTO();
		dto.setFanZoneName("Fan Zona 1");
		
		FanZoneManager manager = new FanZoneManager(uow);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		FanZone fanzone = uow.getFanZoneRepository().Read(dto.getFanZoneId());
		
		Assert.assertEquals(dto.getFanZoneName(), fanzone.getFanZoneName());
	}
	
	@Test
	public void DeletingFanZone_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getFanZoneRepository().Delete(1);
        }});
		
        //Act and assert
        FanZoneManager manager = new FanZoneManager(uow);
        
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadFanZone_ReturnsFanZone() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final FanZoneRepositoryInterface repository = mock.mock(FanZoneRepositoryInterface.class);
		
		final FanZone fz = new FanZone();
		fz.setFanZoneName("Fan Zona 1");
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getFanZoneRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(fz));
        }});
        
        FanZoneManager manager = new FanZoneManager(uow);
        
        //Act
        FanZoneDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getFanZoneId(), fz.getFanZoneId());
        Assert.assertEquals(dto.getFanZoneName(), fz.getFanZoneName());
        
        mock.assertIsSatisfied();
	}
}
