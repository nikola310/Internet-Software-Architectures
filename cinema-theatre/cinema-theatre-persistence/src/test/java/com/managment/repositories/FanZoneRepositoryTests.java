package com.managment.repositories;

import java.util.ArrayList;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.FanZone;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class FanZoneRepositoryTests {

	private UnitOfWork uow;

	public FanZoneRepositoryTests() {
		uow = new UnitOfWork();
	}
	
	@Test
	public void CRUD_FanZoneTests() {
		Mockery mock = new Mockery();
		
		//Arrange
		int key = 0;
		FanZone fz = new FanZone();
		fz.setFanZoneName("Fan Zone Name");
		fz.setFanZoneId(key);
		
		// Act
		uow.getFanZoneRepository().Add(fz);
		uow.commitChanges();
		
		// Arrange and act
		ArrayList<FanZone> lista = uow.getFanZoneRepository().ReadAll();
		
		for(FanZone tmp : lista){
			if(tmp.getFanZoneName().equals("Fan Zone Name")){
				key = tmp.getFanZoneId();
				break;
			}
		}
		
		fz = uow.getFanZoneRepository().Read(key);
		
		// Assert
		Assert.assertNotNull(fz);
		
		Assert.assertEquals("Fan Zone Name", fz.getFanZoneName());
		
		// Arrange
		fz = uow.getFanZoneRepository().Read(key);
		uow.commitChanges();
		
		Assert.assertNotNull(fz);
		
		fz.setFanZoneName("Naziv Fan Zone");
		
		// Act
		uow.getFanZoneRepository().Update();
		uow.commitChanges();
		
		uow.getFanZoneRepository().Delete(key);
		uow.commitChanges();
		
		// Assert
		mock.assertIsSatisfied();
	}
}
