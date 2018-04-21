package com.management.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.RegistrationDTO;
import com.management.dto.UserDTO;
import com.management.entities.User;
import com.management.fake.UserRepositoryFake;
import com.management.mail.Mailing;
import com.management.managers.UserManager;
import com.management.repositories.UserRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class UserControllerTests {

	private UserRepository userRepository;

	@Test
	public void AddingNewUser_ReturnsOK() {
		// Arrange
		userRepository = new UserRepositoryFake();

		RegistrationDTO dto = new RegistrationDTO();
		dto.setUserName("Pero");
		dto.setUserSurname("Peric");
		dto.setUserCity("Novi Sad");
		dto.setUserEmail("pero@gmail.com");
		dto.setUserPassword("123");
		dto.setUserPhone("123456");
		dto.setUserStateid("381");

		UserManager manager = new UserManager(userRepository);
		Mailing mailing = new Mailing();

		UserController controller = new UserController();
		controller.setMailingManager(mailing);
		controller.setManager(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addUser(dto), new ResponseEntity<RegistrationDTO>(dto,HttpStatus.OK));
	}

	@Test
	public void DeletingUser_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		userRepository = mock.mock(UserRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(userRepository).delete(1);
			}
		});

		// Act and assert
		UserManager manager = new UserManager(userRepository);
		Mailing mailing = new Mailing();

		UserController controller = new UserController();
		controller.setMailingManager(mailing);
		controller.setManager(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteUser(1), new ResponseEntity<UserDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadUser_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		userRepository = mock.mock(UserRepository.class);

		final User user = new User();
		user.setUserId(1);
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
		user.setUserStateid("381");

		mock.checking(new Expectations() {
			{
				oneOf(userRepository).findOne(1);
				will(returnValue(user));
			}
		});

		UserManager manager = new UserManager(userRepository);
		Mailing mailing = new Mailing();

		UserController controller = new UserController();
		controller.setMailingManager(mailing);
		controller.setManager(manager);

		// Act
		ResponseEntity<UserDTO> response = controller.getUser(1);
		UserDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<UserDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllUsers_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		userRepository = mock.mock(UserRepository.class);

		final ArrayList<User> list = new ArrayList<User>();

		User user1 = new User();
		user1.setUserId(1);
		user1.setUserActive(true);
		user1.setUserName("Pero");
		user1.setUserSurname("Peric");
		user1.setUserAdmin('O');
		user1.setUserCity("Novi Sad");
		user1.setUserCreationDate(new Date());
		user1.setUserEmail("pero@gmail.com");
		user1.setUserPassword("123");
		user1.setUserRank(0);
		user1.setUserPhone(123456);
		user1.setUserStateid("381");

		User user2 = new User();
		user2.setUserId(2);
		user2.setUserActive(false);
		user2.setUserName("Djuro");
		user2.setUserSurname("Djuric");
		user2.setUserAdmin('O');
		user2.setUserCity("Sombor");
		user2.setUserCreationDate(new Date());
		user2.setUserEmail("djuro@gmail.com");
		user2.setUserPassword("321");
		user2.setUserRank(1);
		user2.setUserPhone(654321);
		user2.setUserStateid("386");

		list.add(user1);
		list.add(user2);

		mock.checking(new Expectations() {
			{
				oneOf(userRepository).findAll();
				will(returnValue(list));
			}
		});

		UserManager manager = new UserManager(userRepository);
		Mailing mailing = new Mailing();

		UserController controller = new UserController();
		controller.setMailingManager(mailing);
		controller.setManager(manager);

		// Act
		ResponseEntity<List<UserDTO>> response = controller.getUsers();
		ArrayList<UserDTO> listDTO = (ArrayList<UserDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<UserDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
