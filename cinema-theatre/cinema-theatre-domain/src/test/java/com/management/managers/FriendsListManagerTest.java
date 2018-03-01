package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.FriendslistDTO;
import com.management.entities.Friendslist;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.FriendsListRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendsListManagerTest {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewFriendsList_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		FriendslistDTO dto = new FriendslistDTO();
		dto.setFriendsStatus('P');

		FriendsListManager manager = new FriendsListManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Friendslist list = uow.getFriendsListRepository().Read(dto.getFriendsId());
		
		Assert.assertEquals(dto.getFriendsStatus(), list.getFriendsStatus());
		
	}
	
	@Test
	public void DeletingFriendsList_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getFriendsListRepository().Delete(1);
        }});
		
        //Act and assert
        FriendsListManager manager = new FriendsListManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadFriendsList_ReturnsUser() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final FriendsListRepositoryInterface repository = mock.mock(FriendsListRepositoryInterface.class);
		
		final Friendslist list = new Friendslist();
		list.setFriendsStatus('P');
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getFriendsListRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(list));
        }});
        
        FriendsListManager manager = new FriendsListManager(uow);
        
        //Act
        FriendslistDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getFriendsStatus(), list.getFriendsStatus());
		
        mock.assertIsSatisfied();
	}
}
