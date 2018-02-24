package com.managment.repositories;

import java.util.Date;

import org.jmock.Mockery;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.User;

/**
 * 
 * @author Zivko Stanisic
 *
 */
public class UnitOfWorkTests {

	@Test
	public void AddingNewUserTest() {
		Mockery mock = new Mockery();

		UnitOfWork uow = new UnitOfWork();

		User user = new User();
		user.setUserActive(true);
		user.setUserName("Pero");
		user.setUserSurname("Peric");
		user.setUserAdmin('O');
		user.setUserCity("Novi Sad");
		user.setUserCreationDate(new Date());
		user.setUserEmail("pero@gmail.com");
		user.setUserPassword("123");
		user.setUserRank(0);
		user.setUserPhone(123456);
		user.setUserStateid((short) 1);

		user.setUserId(1);

		uow.getUserRepository().Add(user);
		mock.assertIsSatisfied();
	}

}
