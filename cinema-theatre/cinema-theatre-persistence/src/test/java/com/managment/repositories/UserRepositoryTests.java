package com.managment.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.User;

/**
 * 
 * @author Zivko Stanisic
 *
 */
public class UserRepositoryTests {
	private UnitOfWork uow;

	public UserRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_UserTest() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		User user = new User();
		user.setUserActive(true);
		user.setUserName("Zixxx");
		user.setUserSurname("Stanisic");
		user.setUserAdmin('N');
		user.setUserCity("Novi Sad");
		user.setUserCreationDate(new Date());
		user.setUserEmail("zivko@gmail.com");
		user.setUserPassword("123");
		user.setUserRank(0);
		user.setUserPhone(123456);
		user.setUserStateid("381");

		// Act
		uow.getUserRepository().Add(user);
		uow.commitChanges();

		// Arrange and act
		ArrayList<User> list = uow.getUserRepository().ReadAll();

		for (User tmp : list) {
			if (tmp.getUserName().equals("Zixxx") && tmp.getUserSurname().equals("Stanisic")) {
				key = tmp.getUserId();
				break;
			}
		}

		user = uow.getUserRepository().Read(key);

		// Assert
		Assert.assertNotNull(user);

		Assert.assertTrue(user.isUserActive());
		Assert.assertEquals("Zixxx", user.getUserName());
		Assert.assertEquals("Stanisic", user.getUserSurname());
		Assert.assertEquals('N', user.getUserAdmin());
		Assert.assertEquals("Novi Sad", user.getUserCity());
		Assert.assertEquals("zivko@gmail.com", user.getUserEmail());
		Assert.assertEquals("123", user.getUserPassword());
		Assert.assertEquals(new Integer(0), user.getUserRank());
		Assert.assertEquals(new Integer(123456), user.getUserPhone());
		Assert.assertEquals("381", user.getUserStateid());

		// Arrange
		user = uow.getUserRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(user);

		user.setUserRank(11);

		// Act
		uow.getUserRepository().Update();
		uow.commitChanges();

		uow.getUserRepository().Delete(key);
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
