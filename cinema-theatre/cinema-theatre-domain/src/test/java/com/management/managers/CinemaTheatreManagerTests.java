/**
 * 
 */
package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.CinemaTheatreDTO;
import com.management.entities.CinemaTheatre;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.CinemaTheatreRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreManagerTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewCinemaTheatre_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		CinemaTheatreDTO dto = new CinemaTheatreDTO();
		dto.setCtAdress("Bulevar oslobodjenja 11");
		dto.setCtDescription("Ovo je opis.");
		dto.setCtName("Bioskop");
		dto.setCtPhone("1234567890");

		CinemaTheatreManager manager = new CinemaTheatreManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		CinemaTheatre cinemaTheatre = uow.getCinemaTheatreRepository().Read(dto.getCtId());
		
		Assert.assertEquals(dto.getCtAdress(), cinemaTheatre.getCtAdress());
		Assert.assertEquals(dto.getCtDescription(), cinemaTheatre.getCtDescription());
		Assert.assertEquals(dto.getCtName(), cinemaTheatre.getCtName());
		Assert.assertEquals(dto.getCtPhone(), cinemaTheatre.getCtPhone());
		
	}
	
	@Test
	public void DeletingCinemaTheatre_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getCinemaTheatreRepository().Delete(1);
        }});
		
        //Act and assert
        CinemaTheatreManager manager = new CinemaTheatreManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadCinemaTheatre_ReturnsUser() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final CinemaTheatreRepositoryInterface repository = mock.mock(CinemaTheatreRepositoryInterface.class);
		
		final CinemaTheatre cinemaTheatre = new CinemaTheatre();
		cinemaTheatre.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre.setCtDescription("Ovo je opis.");
		cinemaTheatre.setCtName("Bioskop");
		cinemaTheatre.setCtPhone(1234567890);
		cinemaTheatre.setCtStateid("381");
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getCinemaTheatreRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(cinemaTheatre));
        }});
        
        CinemaTheatreManager manager = new CinemaTheatreManager(uow);
        
        //Act
        CinemaTheatreDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getCtAdress(), cinemaTheatre.getCtAdress());
		Assert.assertEquals(dto.getCtDescription(), cinemaTheatre.getCtDescription());
		Assert.assertEquals(dto.getCtName(), cinemaTheatre.getCtName());
		Assert.assertEquals(dto.getCtPhone(), cinemaTheatre.getCtPhone());
		
        mock.assertIsSatisfied();
	}
}
