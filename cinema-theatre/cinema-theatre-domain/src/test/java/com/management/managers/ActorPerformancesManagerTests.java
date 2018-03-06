package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.ActorPerformancesDTO;
import com.management.entities.Actorperformances;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.ActorPerformancesRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorPerformancesManagerTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewActorPerformances_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		ActorPerformancesDTO dto = new ActorPerformancesDTO();
		ActorPerformancesManager manager = new ActorPerformancesManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Actorperformances ap = uow.getActorPerformancesRepository().Read(dto.getApId());
		
		Assert.assertEquals(dto.getApId(), ap.getApId());
	}
	
	@Test
	public void DeletingActorPerformances_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getActorPerformancesRepository().Delete(1);
        }});
		
        //Act and assert
        ActorPerformancesManager manager = new ActorPerformancesManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadActorPerformances_ReturnsActorPerformances() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final ActorPerformancesRepositoryInterface repository = mock.mock(ActorPerformancesRepositoryInterface.class);
		
		final Actorperformances ap = new Actorperformances();
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getActorPerformancesRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(ap));
        }});
        
        ActorPerformancesManager manager = new ActorPerformancesManager(uow);
        
        //Act
        ActorPerformancesDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getApId(), ap.getApId());
        
        mock.assertIsSatisfied();
	}
}

