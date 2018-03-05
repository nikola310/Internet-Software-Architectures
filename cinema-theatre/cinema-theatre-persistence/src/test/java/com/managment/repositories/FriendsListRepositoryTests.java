package com.managment.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.Friendslist;
import com.management.entities.User;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendsListRepositoryTests {
	private UnitOfWork uow;

	public FriendsListRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_FriendsListTest() {
		Mockery mock = new Mockery();

		// Arrange
		int keyUser1 = 0;
		int keyUser2 = 0;
		int key = 0;
		Friendslist list = new Friendslist();
		list.setFriendsStatus('P');

		User user1 = new User();
		user1.setUserActive(true);
		user1.setUserName("Zivko");
		user1.setUserSurname("Stanisic");
		user1.setUserAdmin('N');
		user1.setUserCity("Novi Sad");
		user1.setUserCreationDate(new Date());
		user1.setUserEmail("zivko@gmail.com");
		user1.setUserPassword("123");
		user1.setUserRank(0);
		user1.setUserPhone(123456);
		user1.setUserStateid("381");

		User user2 = new User();
		user2.setUserActive(true);
		user2.setUserName("Nikola");
		user2.setUserSurname("Stojanovic");
		user2.setUserAdmin('N');
		user2.setUserCity("Novi Sad");
		user2.setUserCreationDate(new Date());
		user2.setUserEmail("nidzo@gmail.com");
		user2.setUserPassword("123");
		user2.setUserRank(0);
		user2.setUserPhone(1234563232);
		user2.setUserStateid("381");

		list.setUserByUserId(user1);
		list.setUserByUseUserId(user1);
		list.setUserByUseUserId2(user2);

		// Act
		uow.getUserRepository().Add(user1);
		uow.getUserRepository().Add(user2);
		uow.getFriendsListRepository().Add(list);
		uow.commitChanges();

		// Assert

		// Arrange and act
		ArrayList<User> listUser = uow.getUserRepository().ReadAll();

		for (User tmp : listUser) {
			if (tmp.getUserName().equals("Zivko") && tmp.getUserSurname().equals("Stanisic")) {
				keyUser1 = tmp.getUserId();
				break;
			}
		}

		for (User tmp : listUser) {
			if (tmp.getUserName().equals("Nikola") && tmp.getUserSurname().equals("Stojanovic")) {
				keyUser2 = tmp.getUserId();
				break;
			}
		}

		ArrayList<Friendslist> listFriends = uow.getFriendsListRepository().ReadAll();

		for (Friendslist tmp : listFriends) {
			if (tmp.getUserByUserId().getUserName().equals("Zivko")
					&& tmp.getUserByUserId().getUserSurname().equals("Stanisic")) {
				key = tmp.getFriendsId();
				break;
			}
		}

		user1 = uow.getUserRepository().Read(keyUser1);
		user2 = uow.getUserRepository().Read(keyUser2);
		list = uow.getFriendsListRepository().Read(key);
		uow.commitChanges();

		// Assert
		Assert.assertNotNull(user1);
		Assert.assertNotNull(user2);
		Assert.assertNotNull(list);

		Assert.assertEquals(user1, list.getUserByUserId());
		Assert.assertEquals(user1, list.getUserByUseUserId());
		Assert.assertEquals(user2, list.getUserByUseUserId2());
		Assert.assertEquals('P', list.getFriendsStatus());

		// Arrange
		list = uow.getFriendsListRepository().Read(1);
		uow.commitChanges();

		Assert.assertNotNull(list);

		// Act
		list.setFriendsStatus('A');
		uow.getFriendsListRepository().Update();
		uow.commitChanges();

		uow.getUserRepository().Delete(keyUser1);
		uow.getUserRepository().Delete(keyUser2);
		uow.getFriendsListRepository().Delete(key);
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
