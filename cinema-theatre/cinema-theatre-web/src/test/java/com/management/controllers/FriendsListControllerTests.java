package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.FriendslistDTO;
import com.management.entities.Friendslist;
import com.management.fake.FriendsListRepositoryFake;
import com.management.repositories.FriendsListRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendsListControllerTests {

	private FriendsListRepository friendsListRepository;

	@Test
	public void AddingNewFriendsList_ReturnsOK() {
		// Arrange
		friendsListRepository = new FriendsListRepositoryFake();

		FriendslistDTO dto = new FriendslistDTO();
		dto.setFriendsStatus('P');

		//FriendsListManager manager = new FriendsListManager();
		FriendsListController controller = new FriendsListController();

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addFriendslist(dto), new ResponseEntity<FriendslistDTO>(dto, HttpStatus.OK));

	}

	@Test
	public void DeletingFriendsList_ReturnsOK() {
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
		//FriendsListManager manager = new FriendsListManager();
		FriendsListController controller = new FriendsListController();

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteFriendsList(1), new ResponseEntity<FriendslistDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadFriendsList_ReturnsOK() {
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

		//FriendsListManager manager = new FriendsListManager();
		FriendsListController controller = new FriendsListController();

		// Act
		ResponseEntity<FriendslistDTO> response = controller.getFriendslist(1);
		FriendslistDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<FriendslistDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllFriendsLists_ReturnsOK() {
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

		//FriendsListManager manager = new FriendsListManager();
		FriendsListController controller = new FriendsListController();

		// Act
		ResponseEntity<List<FriendslistDTO>> response = controller.getFriendslists();
		ArrayList<FriendslistDTO> listDTO = (ArrayList<FriendslistDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<FriendslistDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
