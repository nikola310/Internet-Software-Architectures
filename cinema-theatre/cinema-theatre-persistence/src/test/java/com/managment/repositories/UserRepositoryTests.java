package com.managment.repositories;

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
	public void AddingNewUserTest() {
		Mockery mock = new Mockery();

		User user = new User();
		user.setUserActive(true);
		user.setUserName("Zivko");
		user.setUserSurname("Stanisic");
		user.setUserAdmin('O');
		user.setUserCity("Novi Sad");
		user.setUserCreationDate(new Date());
		user.setUserEmail("zivko@gmail.com");
		user.setUserPassword("123");
		user.setUserRank(0);
		user.setUserPhone(123456);
		user.setUserStateid((short) 1);

		uow.getUserRepository().Add(user);
		uow.commitChanges();
		mock.assertIsSatisfied();
	}

	@Test
	public void ReadExistingUserTest() {
		Mockery mock = new Mockery();

		User user = uow.getUserRepository().Read(1);
		uow.commitChanges();

		Assert.assertNotNull(user);

		Assert.assertTrue(user.isUserActive());
		Assert.assertEquals("Zivko", user.getUserName());
		Assert.assertEquals("Stanisic", user.getUserSurname());
		Assert.assertEquals('O', user.getUserAdmin());
		Assert.assertEquals("Novi Sad", user.getUserCity());
		Assert.assertEquals("zivko@gmail.com", user.getUserEmail());
		Assert.assertEquals("123", user.getUserPassword());
		Assert.assertEquals(new Integer(0), user.getUserRank());
		Assert.assertEquals(new Integer(123456), user.getUserPhone());
		Assert.assertEquals(new Short((short) 1), user.getUserStateid());

		mock.assertIsSatisfied();
	}

	@Test
	public void UpdateAndDeleteExistingUserTest() {
		Mockery mock = new Mockery();

		User user = uow.getUserRepository().Read(1);
		uow.commitChanges();

		Assert.assertNotNull(user);

		user.setUserRank(11);

		uow.getUserRepository().Update();
		uow.commitChanges();
		
		uow.getUserRepository().Delete(1);
		uow.commitChanges();

		mock.assertIsSatisfied();
	}
}
