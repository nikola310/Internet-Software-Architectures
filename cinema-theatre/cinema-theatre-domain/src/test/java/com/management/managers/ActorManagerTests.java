package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.ActorDTO;
import com.management.entities.Actor;
import com.management.fake.ActorRepositoryFake;
import com.management.repositories.ActorRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorManagerTests {
	private ActorRepository actorRepository;

	@Test
	public void AddingNewActor_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		actorRepository = new ActorRepositoryFake();
		
		
		ActorDTO dto = new ActorDTO();
		dto.setAcName("Zivko");
		dto.setAcSurname("Stanisic");
		ActorManager manager = new ActorManager(actorRepository);


		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Actor actor = actorRepository.findOne(0);
		
		Assert.assertEquals(dto.getAcName(), actor.getAcName());
		Assert.assertEquals(dto.getAcSurname(), actor.getAcSurname());
		mock.assertIsSatisfied();
	}
	
	@Test
	public void DeletingActor_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		actorRepository = mock.mock(ActorRepository.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (actorRepository).delete(1);
        }});
		
        //Act and assert
        ActorManager manager = new ActorManager(actorRepository);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadActor_ReturnsActor() {
		//Arrange
		Mockery mock = new Mockery();
		actorRepository = mock.mock(ActorRepository.class);
		
		final Actor actor = new Actor();
		actor.setAcName("Zivko");
		actor.setAcSurname("Stanisic");
        
        mock.checking(new Expectations() {{
            oneOf (actorRepository).findOne(1);
            will(returnValue(actor));
        }});
        
        ActorManager manager = new ActorManager(actorRepository);
        
        //Act
        ActorDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getAcName(), actor.getAcName());
        Assert.assertEquals(dto.getAcSurname(), actor.getAcSurname());
        
        mock.assertIsSatisfied();
	}
}
