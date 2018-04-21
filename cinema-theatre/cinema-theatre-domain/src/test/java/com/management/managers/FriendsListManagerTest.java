package com.management.managers;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.FriendslistDTO;
import com.management.entities.Friendslist;
import com.management.fake.FriendsListRepositoryFake;
import com.management.fake.UserRepositoryFake;
import com.management.repositories.FriendsListRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendsListManagerTest {

	private FriendsListRepository friendsListRepository;

	@Test
	public void AddingNewFriendsList_ReturnsBoolean() {
		// Arrange
		friendsListRepository = new FriendsListRepositoryFake();

		FriendslistDTO dto = new FriendslistDTO();
		dto.setFriendsStatus('P');

		FriendsListManager manager = new FriendsListManager();
		manager.setFriendsListRepository(friendsListRepository);
		manager.setUserRepository(new UserRepositoryFake());

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		Friendslist list = friendsListRepository.findOne(0);

		Assert.assertEquals(dto.getFriendsStatus(), list.getFriendsStatus());

	}

	@Test
	public void DeletingFriendsList_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		friendsListRepository = mock.mock(FriendsListRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(friendsListRepository).delete(1);
			}
		});

		// Act and assert
		FriendsListManager manager = new FriendsListManager();
		manager.setFriendsListRepository(friendsListRepository);
		
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadFriendsList_ReturnsUser() {
		// Arrange
		Mockery mock = new Mockery();
		friendsListRepository = mock.mock(FriendsListRepository.class);

		final Friendslist list = new Friendslist();
		list.setFriendsStatus('P');

		mock.checking(new Expectations() {
			{
				oneOf(friendsListRepository).findOne(1);
				will(returnValue(list));
			}
		});

		FriendsListManager manager = new FriendsListManager();
		manager.setFriendsListRepository(friendsListRepository);

		// Act
		FriendslistDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getFriendsStatus(), list.getFriendsStatus());

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllFriendsLists_ReturnsAllFriendsLists() {
		// Arrange
		Mockery mock = new Mockery();
		friendsListRepository = mock.mock(FriendsListRepository.class);

		final ArrayList<Friendslist> list = new ArrayList<Friendslist>();

		Friendslist list1 = new Friendslist();
		list1.setFriendsStatus('P');

		Friendslist list2 = new Friendslist();
		list2.setFriendsStatus('A');

		list.add(list1);
		list.add(list2);

		mock.checking(new Expectations() {
			{
				oneOf(friendsListRepository).findAll();
				will(returnValue(list));
			}
		});

		FriendsListManager manager = new FriendsListManager();
		manager.setFriendsListRepository(friendsListRepository);

		// Act
		ArrayList<FriendslistDTO> listDTO = manager.ReadAll();

		// Assert
		Assert.assertNotNull(listDTO);

		Assert.assertEquals(listDTO.get(0).getFriendsStatus(), list.get(0).getFriendsStatus());
		Assert.assertEquals(listDTO.get(1).getFriendsStatus(), list.get(1).getFriendsStatus());

		mock.assertIsSatisfied();
	}
}
