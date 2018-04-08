package com.management.controllers;

import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.PropsDTO;
import com.management.entities.Props;
import com.management.fake.PropsRepositoryFake;
import com.management.managers.PropsManager;
import com.management.repositories.PropsRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class PropsControllerTests {

	private PropsRepository propsRepository;

	@Test
	public void AddingNewProps_ReturnsOK() throws IOException {
		// Arrange
		Mockery mock = new Mockery();
		propsRepository = new PropsRepositoryFake();

		PropsDTO dto = new PropsDTO();
		dto.setPropsDeadline(new Date());
		dto.setPropsDesc("Spoderman hat");
		dto.setPropsName("Spoderman");
		dto.setPropsPrice(300);
		dto.setPropsUsed(true);
		byte[] byteArray = ((DataBufferByte) ImageIO
				.read(new File("C:\\Users\\Nikola\\Desktop\\test.png"))
				.getData().getDataBuffer()).getData();
		dto.setPropsImage(byteArray);
		PropsManager manager = new PropsManager(propsRepository);
		PropsController controller = new PropsController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addProps(dto),
				new ResponseEntity<PropsDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void DeletingProps_ReturnsOK() {
		Mockery mock = new Mockery();
		propsRepository = mock.mock(PropsRepository.class);

		// Expectations
		mock.checking(new Expectations() {
			{
				oneOf(propsRepository).delete(1);
			}
		});

		PropsManager manager = new PropsManager(propsRepository);
		// Act and assert
		PropsController controller = new PropsController(manager);
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteProps(1),
				new ResponseEntity<PropsDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadProps_ReturnsOK() throws IOException {
		// Arrange
		Mockery mock = new Mockery();
		propsRepository = mock.mock(PropsRepository.class);

		final Props props = new Props();
		props.setPropsDeadline(new Date());
		props.setPropsDesc("Spoderman hat");
		props.setPropsName("Spoderman");
		props.setPropsPrice(300);
		props.setPropsUsed(true);
		byte[] byteArray = ((DataBufferByte) ImageIO
				.read(new File("C:\\Users\\Nikola\\Desktop\\test.png"))
				.getData().getDataBuffer()).getData();
		props.setPropsImage(byteArray);

		mock.checking(new Expectations() {
			{
				oneOf(propsRepository).findOne(1);
				will(returnValue(props));
			}
		});

		PropsManager manager = new PropsManager(propsRepository);
		PropsController controller = new PropsController(manager);

		// Act
		ResponseEntity<PropsDTO> response = controller.getProps(1);
		PropsDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<PropsDTO>(dto,
				HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllPropses_ReturnsOK() throws IOException {
		// Arrange
		Mockery mock = new Mockery();
		propsRepository = mock.mock(PropsRepository.class);

		final ArrayList<Props> list = new ArrayList<Props>();

		Props props1 = new Props();
		props1.setPropsDeadline(new Date());
		props1.setPropsDesc("Spoderman hat");
		props1.setPropsName("Spoderman");
		props1.setPropsPrice(300);
		props1.setPropsUsed(true);
		byte[] byteArray = ((DataBufferByte) ImageIO
				.read(new File("C:\\Users\\Nikola\\Desktop\\test.png"))
				.getData().getDataBuffer()).getData();
		props1.setPropsImage(byteArray);

		Props props2 = new Props();
		props2.setPropsDeadline(new Date());
		props2.setPropsDesc("Captain Murica shield");
		props2.setPropsName("Captain Murica");
		props2.setPropsPrice(500);
		props2.setPropsUsed(false);
		byteArray = ((DataBufferByte) ImageIO
				.read(new File("C:\\Users\\Nikola\\Desktop\\9cd.jpg"))
				.getData().getDataBuffer()).getData();
		props2.setPropsImage(byteArray);

		list.add(props1);
		list.add(props2);

		mock.checking(new Expectations() {
			{
				oneOf(propsRepository).findAll();
				will(returnValue(list));
			}
		});

		PropsManager manager = new PropsManager(propsRepository);
		PropsController controller = new PropsController(manager);

		// Act
		ResponseEntity<List<PropsDTO>> response = controller.getProps();
		ArrayList<PropsDTO> dtoList = (ArrayList<PropsDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<PropsDTO>>(
				dtoList, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
