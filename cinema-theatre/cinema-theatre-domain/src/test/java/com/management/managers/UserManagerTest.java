package com.management.managers;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.UserDTO;
import com.management.entities.User;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.UnitOfWorkInterface;
import com.management.interfaces.UserRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class UserManagerTest {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewUser_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		UserDTO dto = new UserDTO();
		dto.setUserActive(true);
		dto.setUserName("Pero");
		dto.setUserSurname("Peric");
		dto.setUserAdmin('O');
		dto.setUserCity("Novi Sad");
		dto.setUserCreationDate(new Date());
		dto.setUserEmail("pero@gmail.com");
		dto.setUserPassword("123");
		dto.setUserRank(0);
		dto.setUserPhone(123456);
		dto.setUserStateid((short) 1);

		UserManager manager = new UserManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		User user = uow.getUserRepository().Read(dto.getUserId());
		
		Assert.assertEquals(dto.getUserCity(), user.getUserCity());
		Assert.assertEquals(dto.getUserAdmin(), user.getUserAdmin());
		Assert.assertEquals(dto.getUserRank(), user.getUserRank());
		Assert.assertEquals(dto.getUserCity(), user.getUserCity());
		Assert.assertEquals(dto.getUserEmail(), user.getUserEmail());
		Assert.assertEquals(dto.getUserName(), user.getUserName());
		Assert.assertEquals(dto.getUserPassword(), user.getUserPassword());
		Assert.assertEquals(dto.getUserSurname(), user.getUserSurname());
		Assert.assertEquals(dto.getUserAdmin(), user.getUserAdmin());
		Assert.assertEquals(dto.getUserCreationDate(), user.getUserCreationDate());
		Assert.assertEquals(dto.getUserPhone(), user.getUserPhone());
		Assert.assertEquals(dto.getUserStateid(), user.getUserStateid());
	}
	
	@Test
	public void DeletingUser_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getUserRepository().Delete(1);
        }});
		
        //Act and assert
        UserManager manager = new UserManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadUser_ReturnsUser() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final UserRepositoryInterface repository = mock.mock(UserRepositoryInterface.class);
		
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
		user.setUserStateid((short) 1);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getUserRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(user));
        }});
        
        UserManager manager = new UserManager(uow);
        
        //Act
        UserDTO dto = manager.Read(1);
        
        //Assert
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
		Assert.assertEquals(dto.getUserCreationDate(), user.getUserCreationDate());
		Assert.assertEquals(dto.getUserPhone(), user.getUserPhone());
		Assert.assertEquals(dto.getUserStateid(), user.getUserStateid());
        
        mock.assertIsSatisfied();
	}

}
