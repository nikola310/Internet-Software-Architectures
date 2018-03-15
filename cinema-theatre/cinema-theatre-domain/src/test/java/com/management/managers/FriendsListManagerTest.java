package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.FriendslistDTO;
import com.management.entities.Friendslist;
import com.management.fake.FriendsListRepositoryFake;
import com.management.repositories.FriendsListRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendsListManagerTest {
	private FriendsListRepository friendsListRepository;

	@Test
	public void AddingNewFriendsList_ReturnsBoolean() {
		//Arrange
		friendsListRepository = new FriendsListRepositoryFake();
		
		FriendslistDTO dto = new FriendslistDTO();
		dto.setFriendsStatus('P');

		FriendsListManager manager = new FriendsListManager(friendsListRepository);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Friendslist list = friendsListRepository.findOne(0);
		
		Assert.assertEquals(dto.getFriendsStatus(), list.getFriendsStatus());
		
	}
	
	@Test
	public void DeletingFriendsList_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		friendsListRepository = mock.mock(FriendsListRepository.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (friendsListRepository).delete(1);
        }});
		
        //Act and assert
        FriendsListManager manager = new FriendsListManager(friendsListRepository);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadFriendsList_ReturnsUser() {
		//Arrange
		Mockery mock = new Mockery();
		friendsListRepository = mock.mock(FriendsListRepository.class);
		
		final Friendslist list = new Friendslist();
		list.setFriendsStatus('P');
        
        mock.checking(new Expectations() {{
            oneOf (friendsListRepository).findOne(1);
            will(returnValue(list));
        }});
        
        FriendsListManager manager = new FriendsListManager(friendsListRepository);
        
        //Act
        FriendslistDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getFriendsStatus(), list.getFriendsStatus());
		
        mock.assertIsSatisfied();
	}
}
