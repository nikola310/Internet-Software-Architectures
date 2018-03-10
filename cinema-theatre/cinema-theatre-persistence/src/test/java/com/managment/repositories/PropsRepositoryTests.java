package com.managment.repositories;

import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.FanZone;
import com.management.entities.Props;
import com.management.entities.User;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class PropsRepositoryTests {

	private UnitOfWork uow;

	public PropsRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_PropsTests() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		Props props = new Props();
		// ===============================
		FanZone fz = new FanZone();
		fz.setFanZoneName("Naziv fan zone");

		User user = new User();
		user.setUserActive(true);
		user.setUserName("Zivko");
		user.setUserSurname("Stanisic");
		user.setUserAdmin('N');
		user.setUserCity("Novi Sad");
		user.setUserCreationDate(new Date());
		user.setUserEmail("zivko@gmail.com");
		user.setUserPassword("123");
		user.setUserRank(0);
		user.setUserPhone(123456);
		user.setUserStateid("381");
		// ================================
		props.setUser(user);
		Date datum = new Date();
		props.setPropsDeadline(datum);
		props.setPropsDesc("Opis rekvizita");
		props.setPropsUsed(false);
		float t = 200;
		props.setPropsPrice(t);

		String filePath = "C:\\Users\\Nikola\\Desktop\\test.png";
		try {
			byte[] byteArray = ((DataBufferByte) ImageIO
					.read(new File(filePath)).getData().getDataBuffer())
					.getData();
			System.out.println(byteArray);
			props.setPropsImage(byteArray);
			props.setPropsName("Ime rekvizita");
			props.setFanZone(fz);

			// Act
			uow.getFanZoneRepository().Add(fz);
			uow.getUserRepository().Add(user);
			uow.getPropsRepository().Add(props);
			uow.commitChanges();

			// Arrange and act
			ArrayList<Props> lista = uow.getPropsRepository().ReadAll();

			for (Props tmp : lista) {
				if (tmp.getPropsName().equals("Ime rekvizita")
						&& props.getUser().equals(user)
						&& props.getFanZone().equals(fz)
						&& props.getPropsId() == tmp.getPropsId()) {
					key = tmp.getPropsId();
					break;
				}
			}

			props = uow.getPropsRepository().Read(key);

			// Assert
			Assert.assertNotNull(props);

			Assert.assertEquals(t, props.getPropsPrice(), 0.1);
			Assert.assertEquals("Opis rekvizita", props.getPropsDesc());
			Assert.assertEquals("Ime rekvizita", props.getPropsName());
			Assert.assertArrayEquals(byteArray, props.getPropsImage());

			// Arrange
			props = uow.getPropsRepository().Read(key);
			uow.commitChanges();

			Assert.assertNotNull(props);

			props.setPropsPrice(500);

			// Act
			uow.getPropsRepository().Update();
			uow.commitChanges();

			uow.getPropsRepository().Delete(key);
			uow.getUserRepository().Delete(user.getUserId());
			uow.getFanZoneRepository().Delete(fz.getFanZoneId());
			uow.commitChanges();

			// Assert
			mock.assertIsSatisfied();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Image file not found.");
			e.printStackTrace();
		}

	}
}
