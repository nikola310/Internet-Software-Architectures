package com.managment.repositories;

import java.util.Date;

import org.jmock.Mockery;
import org.junit.Test;

import com.management.entities.User;
import com.management.repositories.UserRepository;
/**
 * 
 * @author Zivko Stanisic
 *
 */
public class BaseRepositoryTests {
	
	@Test
	public void BaseRepositoryTest() {
		Mockery mock = new Mockery();
		
		UserRepository repository = new UserRepository();
		
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
		
		
		repository.Add(user);
		mock.assertIsSatisfied();
	}

}
