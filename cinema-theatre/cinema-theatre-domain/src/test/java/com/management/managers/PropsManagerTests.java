package com.management.managers;

import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
		dto.setPropsApproved(false);
		byte[] byteArray = ((DataBufferByte) ImageIO
				.read(new File("C:\\Users\\Nikola\\Desktop\\test.png"))
				.getData().getDataBuffer()).getData();
		dto.setPropsImage(byteArray);
		dto.setUserId(1);
		PropsManager manager = new PropsManager(propsRepository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		Props props = propsRepository.findOne(0);

		Assert.assertEquals(dto.getPropsId(), props.getPropsId());
		Assert.assertEquals(dto.getPropsName(), props.getPropsName());
		Assert.assertEquals(dto.getPropsPrice(), props.getPropsPrice(), 0.1);
		Assert.assertEquals(dto.getPropsDeadline(), props.getPropsDeadline());
		Assert.assertEquals(dto.getPropsDesc(), props.getPropsDesc());
		Assert.assertArrayEquals(dto.getPropsImage(), props.getPropsImage());
		Assert.assertEquals(dto.isPropsUsed(), props.isPropsUsed());
		Assert.assertEquals(dto.isPropsApproved(), props.getPropsApproved());
		Assert.assertEquals(dto.getUserId(), props.getUser().getUserId());
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
		props.setPropsApproved(true);
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
		Assert.assertEquals(dto.getPropsName(), props.getPropsName());
		Assert.assertEquals(dto.getPropsPrice(), props.getPropsPrice(), 0.1);
		Assert.assertEquals(dto.getPropsDeadline(), props.getPropsDeadline());
		Assert.assertEquals(dto.getPropsDesc(), props.getPropsDesc());
		Assert.assertArrayEquals(dto.getPropsImage(), props.getPropsImage());
		Assert.assertEquals(dto.isPropsUsed(), props.isPropsUsed());
		Assert.assertEquals(dto.isPropsApproved(), props.getPropsApproved());
		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllProps_ReturnsAllProps() throws IOException {
		// Arrange
		Mockery mock = new Mockery();
		propsRepository = mock.mock(PropsRepository.class);

		final ArrayList<Props> list = new ArrayList<Props>();

		Props p1 = new Props();
		p1.setPropsDeadline(new Date());
		p1.setPropsDesc("Spoderman props");
		p1.setPropsName("Spoderman suit");
		p1.setPropsPrice(300);
		p1.setPropsUsed(true);
		p1.setPropsApproved(true);
		byte[] propsImage = ((DataBufferByte) ImageIO
				.read(new File("C:\\Users\\Nikola\\Desktop\\test.png"))
				.getData().getDataBuffer()).getData();
		p1.setPropsImage(propsImage);

		Props p2 = new Props();
		p2.setPropsDeadline(new Date());
		p2.setPropsDesc("Captain Murica props");
		p2.setPropsName("Captain Murica suit");
		p2.setPropsPrice(500);
		p2.setPropsUsed(false);
		p2.setPropsApproved(false);
		byte[] propsImage2 = ((DataBufferByte) ImageIO
				.read(new File("C:\\Users\\Nikola\\Desktop\\9cd.jpg"))
				.getData().getDataBuffer()).getData();
		p2.setPropsImage(propsImage2);

		list.add(p1);
		list.add(p2);

		mock.checking(new Expectations() {
			{
				oneOf(propsRepository).findAll();
				will(returnValue(list));
			}
		});

		PropsManager manager = new PropsManager(propsRepository);

		// Act
		ArrayList<PropsDTO> dtoList = manager.ReadAll();

		// Assert
		Assert.assertNotNull(dtoList);

		Assert.assertEquals(dtoList.get(0).getPropsId(), list.get(0)
				.getPropsId());
		Assert.assertEquals(dtoList.get(0).getPropsDesc(), list.get(0)
				.getPropsDesc());
		Assert.assertEquals(dtoList.get(0).getPropsName(), list.get(0)
				.getPropsName());
		Assert.assertEquals(dtoList.get(0).getPropsPrice(), list.get(0)
				.getPropsPrice(), 0.1);
		Assert.assertEquals(dtoList.get(0).getPropsDeadline(), list.get(0)
				.getPropsDeadline());
		Assert.assertArrayEquals(dtoList.get(0).getPropsImage(), list.get(0)
				.getPropsImage());
		Assert.assertEquals(dtoList.get(0).isPropsUsed(), list.get(0)
				.isPropsUsed());
		Assert.assertEquals(dtoList.get(0).isPropsApproved(), list.get(0)
				.getPropsApproved());

		Assert.assertEquals(dtoList.get(1).getPropsId(), list.get(1)
				.getPropsId());
		Assert.assertEquals(dtoList.get(1).getPropsDesc(), list.get(1)
				.getPropsDesc());
		Assert.assertEquals(dtoList.get(1).getPropsName(), list.get(1)
				.getPropsName());
		Assert.assertEquals(dtoList.get(1).getPropsPrice(), list.get(1)
				.getPropsPrice(), 0.1);
		Assert.assertEquals(dtoList.get(1).getPropsDeadline(), list.get(1)
				.getPropsDeadline());
		Assert.assertArrayEquals(dtoList.get(1).getPropsImage(), list.get(1)
				.getPropsImage());
		Assert.assertEquals(dtoList.get(1).isPropsUsed(), list.get(1)
				.isPropsUsed());
		Assert.assertEquals(dtoList.get(1).isPropsApproved(), list.get(1)
				.getPropsApproved());
		
		mock.assertIsSatisfied();
	}
}
