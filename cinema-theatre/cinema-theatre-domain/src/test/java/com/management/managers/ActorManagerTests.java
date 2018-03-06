package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.ActorDTO;
import com.management.entities.Actor;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.ActorRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorManagerTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewActor_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		ActorDTO dto = new ActorDTO();
		dto.setAcName("Zivko");
		dto.setAcSurname("Stanisic");
		ActorManager manager = new ActorManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Actor actor = uow.getActorRepository().Read(dto.getAcId());
		
		Assert.assertEquals(dto.getAcName(), actor.getAcName());
		Assert.assertEquals(dto.getAcSurname(), actor.getAcSurname());
	}
	
	@Test
	public void DeletingActor_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getActorRepository().Delete(1);
        }});
		
        //Act and assert
        ActorManager manager = new ActorManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadActor_ReturnsActor() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final ActorRepositoryInterface repository = mock.mock(ActorRepositoryInterface.class);
		
		final Actor actor = new Actor();
		actor.setAcName("Zivko");
		actor.setAcSurname("Stanisic");
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getActorRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(actor));
        }});
        
        ActorManager manager = new ActorManager(uow);
        
        //Act
        ActorDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getAcName(), actor.getAcName());
        Assert.assertEquals(dto.getAcSurname(), actor.getAcSurname());
        
        mock.assertIsSatisfied();
	}
}
