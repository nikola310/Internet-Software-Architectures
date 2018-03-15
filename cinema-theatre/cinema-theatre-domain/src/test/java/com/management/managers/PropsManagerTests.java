package com.management.managers;

import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.PropsDTO;
import com.management.entities.Props;
import com.management.fake.PropsRepositoryFake;
import com.management.repositories.PropsRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class PropsManagerTests {

	private PropsRepository propsRepository;

	@Test
	public void AddingNewProps_ReturnsBoolean() throws IOException {
		// Arrange
		propsRepository = new PropsRepositoryFake();

		PropsDTO dto = new PropsDTO();
		dto.setPropsDeadline(new Date());
		dto.setPropsDesc("Spoderman props description");
		dto.setPropsName("Spoderman props");
		dto.setPropsPrice(300);
		dto.setPropsUsed(true);
		String filePath = "C:\\Users\\Nikola\\Desktop\\test.png";
		byte[] byteArray;
		byteArray = ((DataBufferByte) ImageIO.read(new File(filePath))
				.getData().getDataBuffer()).getData();
		dto.setPropsImage(byteArray);
		PropsManager manager = new PropsManager(propsRepository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		Props props = propsRepository.findOne(0);

		Assert.assertEquals(dto.getPropsId(), props.getPropsId());
	}

	@Test
	public void DeletingProps_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		propsRepository = mock.mock(PropsRepository.class);

		// Expectations
		mock.checking(new Expectations() {
			{
				oneOf(propsRepository).delete(1);
			}
		});

		// Act and assert
		PropsManager manager = new PropsManager(propsRepository);
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadProps_ReturnsProps() throws IOException {
		// Arrange
		Mockery mock = new Mockery();
		propsRepository = mock.mock(PropsRepository.class);

		final Props props = new Props();
		props.setPropsDeadline(new Date());
		props.setPropsDesc("Spoderman props description");
		props.setPropsName("Spoderman props");
		props.setPropsPrice(300);
		props.setPropsUsed(true);
		String filePath = "C:\\Users\\Nikola\\Desktop\\test.png";
		byte[] byteArray;
		byteArray = ((DataBufferByte) ImageIO.read(new File(filePath))
				.getData().getDataBuffer()).getData();
		props.setPropsImage(byteArray);

		mock.checking(new Expectations() {
			{
				oneOf(propsRepository).findOne(1);
				will(returnValue(props));
			}
		});

		PropsManager manager = new PropsManager(propsRepository);

		// Act
		PropsDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getPropsId(), props.getPropsId());

		mock.assertIsSatisfied();
	}
}
