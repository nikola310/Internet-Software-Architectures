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
import com.management.fake.CinemaTheatreRepositoryFake;
import com.management.repositories.CinemaTheatreRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreManagerTests {
	private CinemaTheatreRepository cinemaTheatreRepository;

	@Test
	public void AddingNewCinemaTheatre_ReturnsBoolean() {
		//Arrange
		cinemaTheatreRepository = new CinemaTheatreRepositoryFake();
		
		CinemaTheatreDTO dto = new CinemaTheatreDTO();
		dto.setCtAdress("Bulevar oslobodjenja 11");
		dto.setCtDescription("Ovo je opis.");
		dto.setCtName("Bioskop");
		dto.setCtPhone(1234567890);

		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		CinemaTheatre cinemaTheatre = cinemaTheatreRepository.findOne(0);
		
		Assert.assertEquals(dto.getCtAdress(), cinemaTheatre.getCtAdress());
		Assert.assertEquals(dto.getCtDescription(), cinemaTheatre.getCtDescription());
		Assert.assertEquals(dto.getCtName(), cinemaTheatre.getCtName());
		Assert.assertEquals(dto.getCtPhone(), cinemaTheatre.getCtPhone());
		
	}
	
	@Test
	public void DeletingCinemaTheatre_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		cinemaTheatreRepository = mock.mock(CinemaTheatreRepository.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (cinemaTheatreRepository).delete(1);
        }});
		
        //Act and assert
        CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadCinemaTheatre_ReturnsUser() {
		//Arrange
		Mockery mock = new Mockery();
		cinemaTheatreRepository = mock.mock(CinemaTheatreRepository.class);
		
		final CinemaTheatre cinemaTheatre = new CinemaTheatre();
		cinemaTheatre.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre.setCtDescription("Ovo je opis.");
		cinemaTheatre.setCtName("Bioskop");
		cinemaTheatre.setCtPhone(1234567890);
		cinemaTheatre.setCtStateid("381");
        
        mock.checking(new Expectations() {{
            oneOf (cinemaTheatreRepository).findOne(1);
            will(returnValue(cinemaTheatre));
        }});
        
        CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);
        
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
