package com.management.managers;

import java.util.ArrayList;

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
		// Arrange
		cinemaTheatreRepository = new CinemaTheatreRepositoryFake();

		CinemaTheatreDTO dto = new CinemaTheatreDTO();
		dto.setCtAdress("Bulevar oslobodjenja 11");
		dto.setCtDescription("Ovo je opis.");
		dto.setCtName("Bioskop");
		dto.setCtPhone(1234567890);

		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		CinemaTheatre cinemaTheatre = cinemaTheatreRepository.findOne(0);

		Assert.assertEquals(dto.getCtAdress(), cinemaTheatre.getCtAdress());
		Assert.assertEquals(dto.getCtDescription(), cinemaTheatre.getCtDescription());
		Assert.assertEquals(dto.getCtName(), cinemaTheatre.getCtName());
		Assert.assertEquals((int)dto.getCtPhone(), cinemaTheatre.getCtPhone());

	}

	@Test
	public void DeletingCinemaTheatre_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		cinemaTheatreRepository = mock.mock(CinemaTheatreRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(cinemaTheatreRepository).delete(1);
			}
		});

		// Act and assert
		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadCinemaTheatre_ReturnsUser() {
		// Arrange
		Mockery mock = new Mockery();
		cinemaTheatreRepository = mock.mock(CinemaTheatreRepository.class);

		final CinemaTheatre cinemaTheatre = new CinemaTheatre();
		cinemaTheatre.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre.setCtDescription("Ovo je opis.");
		cinemaTheatre.setCtName("Bioskop");
		cinemaTheatre.setCtPhone(1234567890);
		cinemaTheatre.setCtStateid("381");

		mock.checking(new Expectations() {
			{
				oneOf(cinemaTheatreRepository).findOne(1);
				will(returnValue(cinemaTheatre));
			}
		});

		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);

		// Act
		CinemaTheatreDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getCtAdress(), cinemaTheatre.getCtAdress());
		Assert.assertEquals(dto.getCtDescription(), cinemaTheatre.getCtDescription());
		Assert.assertEquals(dto.getCtName(), cinemaTheatre.getCtName());
		Assert.assertEquals((int)dto.getCtPhone(), cinemaTheatre.getCtPhone());

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllCinemaTheatres_ReturnsAllCinemaTheatres() {
		// Arrange
		Mockery mock = new Mockery();
		cinemaTheatreRepository = mock.mock(CinemaTheatreRepository.class);

		final ArrayList<CinemaTheatre> list = new ArrayList<CinemaTheatre>();

		CinemaTheatre cinemaTheatre1 = new CinemaTheatre();
		cinemaTheatre1.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre1.setCtDescription("Ovo je opis.");
		cinemaTheatre1.setCtName("Bioskop");
		cinemaTheatre1.setCtPhone(1234567890);
		cinemaTheatre1.setCtStateid("381");

		CinemaTheatre cinemaTheatre2 = new CinemaTheatre();
		cinemaTheatre2.setCtAdress("Bulevar oslobodjenja 9");
		cinemaTheatre2.setCtDescription("Takodje opis.");
		cinemaTheatre2.setCtName("Kazaliste");
		cinemaTheatre2.setCtPhone(987654321);
		cinemaTheatre2.setCtStateid("386");

		list.add(cinemaTheatre1);
		list.add(cinemaTheatre2);

		mock.checking(new Expectations() {
			{
				oneOf(cinemaTheatreRepository).findAll();
				will(returnValue(list));
			}
		});

		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);

		// Act
		ArrayList<CinemaTheatreDTO> listDTO = manager.ReadAll();

		// Assert
		Assert.assertNotNull(listDTO);

		Assert.assertEquals(list.get(0).getCtAdress(), listDTO.get(0).getCtAdress());
		Assert.assertEquals(list.get(0).getCtDescription(), listDTO.get(0).getCtDescription());
		Assert.assertEquals(list.get(0).getCtName(), listDTO.get(0).getCtName());
		Assert.assertEquals(list.get(0).getCtPhone(), (int)listDTO.get(0).getCtPhone());

		Assert.assertEquals(list.get(1).getCtAdress(), listDTO.get(1).getCtAdress());
		Assert.assertEquals(list.get(1).getCtDescription(), listDTO.get(1).getCtDescription());
		Assert.assertEquals(list.get(1).getCtName(), listDTO.get(1).getCtName());
		Assert.assertEquals(list.get(1).getCtPhone(), (int)listDTO.get(1).getCtPhone());

		mock.assertIsSatisfied();
	}
}
