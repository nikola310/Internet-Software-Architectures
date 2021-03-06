package com.management.managers;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.ActorPerformancesDTO;
import com.management.entities.Actorperformances;
import com.management.fake.ActorPerformancesRepositoryFake;
import com.management.repositories.ActorPerformancesRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorPerformancesManagerTests {

	private ActorPerformancesRepository actorReprformancesRepository;

	@Test
	public void AddingNewActorPerformances_ReturnsBoolean() {
		// Arrange
		actorReprformancesRepository = new ActorPerformancesRepositoryFake();

		ActorPerformancesDTO dto = new ActorPerformancesDTO();
		ActorPerformancesManager manager = new ActorPerformancesManager(actorReprformancesRepository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		Actorperformances ap = actorReprformancesRepository.findOne(0);

		Assert.assertEquals(dto.getApId(), ap.getApId());
	}

	@Test
	public void DeletingActorPerformances_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		actorReprformancesRepository = mock.mock(ActorPerformancesRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(actorReprformancesRepository).delete(1);
			}
		});

		// Act and assert
		ActorPerformancesManager manager = new ActorPerformancesManager(actorReprformancesRepository);
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadActorPerformances_ReturnsActorPerformances() {
		// Arrange
		Mockery mock = new Mockery();
		actorReprformancesRepository = mock.mock(ActorPerformancesRepository.class);

		final Actorperformances ap = new Actorperformances();

		mock.checking(new Expectations() {
			{
				oneOf(actorReprformancesRepository).findOne(1);
				will(returnValue(ap));
			}
		});

		ActorPerformancesManager manager = new ActorPerformancesManager(actorReprformancesRepository);

		// Act
		ActorPerformancesDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getApId(), ap.getApId());

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllActorPerformances_ReturnsAllActorPerformances() {
		// Arrange
		Mockery mock = new Mockery();
		actorReprformancesRepository = mock.mock(ActorPerformancesRepository.class);

		final ArrayList<Actorperformances> list = new ArrayList<Actorperformances>();

		Actorperformances ap1 = new Actorperformances();
		Actorperformances ap2 = new Actorperformances();

		list.add(ap1);
		list.add(ap2);

		mock.checking(new Expectations() {
			{
				oneOf(actorReprformancesRepository).findAll();
				will(returnValue(list));
			}
		});

		ActorPerformancesManager manager = new ActorPerformancesManager(actorReprformancesRepository);

		// Act
		ArrayList<ActorPerformancesDTO> listDTO = manager.ReadAll();

		// Assert
		Assert.assertNotNull(listDTO);

		Assert.assertEquals(listDTO.get(0).getApId(), list.get(0).getApId());
		Assert.assertEquals(listDTO.get(1).getApId(), list.get(1).getApId());

		mock.assertIsSatisfied();
	}
}
