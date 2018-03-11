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
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.PropsRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class PropsManagerTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingProps_ReturnsBoolean() {
		// Arrange
		uow = new UnitOfWorkFake();

		PropsDTO dto = new PropsDTO();
		Date right_now = new Date();
		dto.setPropsDeadline(right_now);
		dto.setPropsDesc("Opis rekvizita 1");
		String filePath = "C:\\Users\\Nikola\\Desktop\\test.png";
		byte[] byteArray;
		try {
			byteArray = ((DataBufferByte) ImageIO.read(new File(filePath))
					.getData().getDataBuffer()).getData();
			dto.setPropsImage(byteArray);
			dto.setPropsName("Rekvizit 1");
			dto.setPropsPrice(300);
			dto.setPropsUsed(true);

			PropsManager manager = new PropsManager(uow);

			// Act and assert
			Assert.assertNotNull(manager);
			Assert.assertTrue(manager.Create(dto));

			Props props = uow.getPropsRepository().Read(dto.getPropsId());

			Assert.assertEquals(dto.getPropsName(), props.getPropsName());
			Assert.assertEquals(dto.getPropsDesc(), props.getPropsDesc());
			Assert.assertEquals(dto.getPropsPrice(), props.getPropsPrice(),
					0.01);
			Assert.assertEquals(dto.isPropsUsed(), props.isPropsUsed());
			Assert.assertEquals(dto.getPropsDeadline(),
					props.getPropsDeadline());
			Assert.assertArrayEquals(dto.getPropsImage(), props.getPropsImage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void DeletingProps_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(uow).getPropsRepository().Delete(1);
			}
		});

		// Act and assert
		PropsManager manager = new PropsManager(uow);

		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadProps_ReturnsProps() {
		// Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final PropsRepositoryInterface repository = mock
				.mock(PropsRepositoryInterface.class);

		final Props props = new Props();
		Date right_now = new Date();
		props.setPropsDeadline(right_now);
		props.setPropsDesc("Opis rekvizita 1");
		String filePath = "C:\\Users\\Nikola\\Desktop\\test.png";
		byte[] byteArray;
		try {
			byteArray = ((DataBufferByte) ImageIO.read(new File(filePath))
					.getData().getDataBuffer()).getData();
			props.setPropsImage(byteArray);
			props.setPropsName("Rekvizit 1");
			props.setPropsPrice(300);
			props.setPropsUsed(true);

			// expectations
			mock.checking(new Expectations() {
				{
					oneOf(uow).getPropsRepository();
					will(returnValue(repository));
				}
			});

			mock.checking(new Expectations() {
				{
					oneOf(repository).Read(1);
					will(returnValue(props));
				}
			});

			PropsManager manager = new PropsManager(uow);

			// Act
			PropsDTO dto = manager.Read(1);

			// Assert
			Assert.assertNotNull(dto);

			Assert.assertEquals(dto.getPropsId(), props.getPropsId());
			Assert.assertEquals(dto.getPropsName(), props.getPropsName());
			Assert.assertEquals(dto.getPropsDesc(), props.getPropsDesc());
			Assert.assertEquals(dto.getPropsPrice(), props.getPropsPrice(),
					0.01);
			Assert.assertEquals(dto.getPropsDeadline(),
					props.getPropsDeadline());
			Assert.assertArrayEquals(dto.getPropsImage(), props.getPropsImage());

			mock.assertIsSatisfied();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
