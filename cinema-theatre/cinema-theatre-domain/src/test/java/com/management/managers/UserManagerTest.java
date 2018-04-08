package com.management.managers;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.UserDTO;
import com.management.entities.User;
import com.management.fake.UserRepositoryFake;
import com.management.repositories.UserRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class UserManagerTest {

	private UserRepository userRepository;

	@Test
	public void AddingNewUser_ReturnsBoolean() {
		// Arrange
		userRepository = new UserRepositoryFake();
		
		UserDTO dto = new UserDTO();
		dto.setUserName("Pero");
		dto.setUserSurname("Peric");
		dto.setUserAdmin('O');
		dto.setUserCity("Novi Sad");
		dto.setUserEmail("pero@gmail.com");
		dto.setUserPassword("123");
		dto.setUserRank(0);
		dto.setUserPhone(123456);
		dto.setUserStateid("381");

		UserManager manager = new UserManager(userRepository);
		manager.addRegistrationToken("nekiToken");
		
		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		User user = userRepository.findOne(0);

		Assert.assertEquals(dto.getUserCity(), user.getUserCity());
		Assert.assertEquals(dto.getUserAdmin(), user.getUserAdmin());
		Assert.assertEquals(dto.getUserRank(), user.getUserRank());
		Assert.assertEquals(dto.getUserCity(), user.getUserCity());
		Assert.assertEquals(dto.getUserEmail(), user.getUserEmail());
		Assert.assertEquals(dto.getUserName(), user.getUserName());
		Assert.assertEquals(dto.getUserPassword(), user.getUserPassword());
		Assert.assertEquals(dto.getUserSurname(), user.getUserSurname());
		Assert.assertEquals(dto.getUserAdmin(), user.getUserAdmin());
		Assert.assertEquals(dto.getUserPhone(), user.getUserPhone());
		Assert.assertEquals(dto.getUserStateid(), user.getUserStateid());
	}

	@Test
	public void DeletingUser_ReturnsBoolean() {
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
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadUser_ReturnsUser() {
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

		// Act
		UserDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getUserCity(), user.getUserCity());
		Assert.assertEquals(dto.getUserAdmin(), user.getUserAdmin());
		Assert.assertEquals(dto.getUserRank(), user.getUserRank());
		Assert.assertEquals(dto.getUserCity(), user.getUserCity());
		Assert.assertEquals(dto.getUserEmail(), user.getUserEmail());
		Assert.assertEquals(dto.getUserName(), user.getUserName());
		Assert.assertEquals(dto.getUserPassword(), user.getUserPassword());
		Assert.assertEquals(dto.getUserSurname(), user.getUserSurname());
		Assert.assertEquals(dto.getUserAdmin(), user.getUserAdmin());
		Assert.assertEquals(dto.getUserPhone(), user.getUserPhone());
		Assert.assertEquals(dto.getUserStateid(), user.getUserStateid());

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllUsers_ReturnsAllUsers() {
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

		// Act
		ArrayList<UserDTO> listDTO = manager.ReadAll();

		// Assert
		Assert.assertNotNull(listDTO);

		Assert.assertEquals(listDTO.get(0).getUserCity(), list.get(0).getUserCity());
		Assert.assertEquals(listDTO.get(0).getUserAdmin(), list.get(0).getUserAdmin());
		Assert.assertEquals(listDTO.get(0).getUserRank(), list.get(0).getUserRank());
		Assert.assertEquals(listDTO.get(0).getUserCity(), list.get(0).getUserCity());
		Assert.assertEquals(listDTO.get(0).getUserEmail(), list.get(0).getUserEmail());
		Assert.assertEquals(listDTO.get(0).getUserName(), list.get(0).getUserName());
		Assert.assertEquals(listDTO.get(0).getUserPassword(), list.get(0).getUserPassword());
		Assert.assertEquals(listDTO.get(0).getUserSurname(), list.get(0).getUserSurname());
		Assert.assertEquals(listDTO.get(0).getUserAdmin(), list.get(0).getUserAdmin());
		Assert.assertEquals(listDTO.get(0).getUserPhone(), list.get(0).getUserPhone());
		Assert.assertEquals(listDTO.get(0).getUserStateid(), list.get(0).getUserStateid());

		Assert.assertEquals(listDTO.get(1).getUserCity(), list.get(1).getUserCity());
		Assert.assertEquals(listDTO.get(1).getUserAdmin(), list.get(1).getUserAdmin());
		Assert.assertEquals(listDTO.get(1).getUserRank(), list.get(1).getUserRank());
		Assert.assertEquals(listDTO.get(1).getUserCity(), list.get(1).getUserCity());
		Assert.assertEquals(listDTO.get(1).getUserEmail(), list.get(1).getUserEmail());
		Assert.assertEquals(listDTO.get(1).getUserName(), list.get(1).getUserName());
		Assert.assertEquals(listDTO.get(1).getUserPassword(), list.get(1).getUserPassword());
		Assert.assertEquals(listDTO.get(1).getUserSurname(), list.get(1).getUserSurname());
		Assert.assertEquals(listDTO.get(1).getUserAdmin(), list.get(1).getUserAdmin());
		Assert.assertEquals(listDTO.get(1).getUserPhone(), list.get(1).getUserPhone());
		Assert.assertEquals(listDTO.get(1).getUserStateid(), list.get(1).getUserStateid());

		mock.assertIsSatisfied();
	}
}
