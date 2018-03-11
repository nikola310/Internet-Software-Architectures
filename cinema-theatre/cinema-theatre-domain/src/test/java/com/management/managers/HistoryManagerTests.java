package com.management.managers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.HistoryDTO;
import com.management.entities.History;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.HistoryRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class HistoryManagerTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingHistory_ReturnsBoolean() {
		// Arrange
		uow = new UnitOfWorkFake();

		HistoryDTO dto = new HistoryDTO();
		dto.setHistoryPrice(300);

		HistoryManager manager = new HistoryManager(uow);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		History history = uow.getHistoryRepository().Read(dto.getHistoryId());

		Assert.assertEquals(dto.getHistoryPrice(), history.getHistoryPrice());
	}

	@Test
	public void DeletingHistory_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(uow).getHistoryRepository().Delete(1);
			}
		});

		// Act and assert
		HistoryManager manager = new HistoryManager(uow);

		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadHistory_ReturnsHistory() {
		// Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final HistoryRepositoryInterface repository = mock
				.mock(HistoryRepositoryInterface.class);

		final History history = new History();
		history.setHistoryPrice(300);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(uow).getHistoryRepository();
				will(returnValue(repository));
			}
		});

		mock.checking(new Expectations() {
			{
				oneOf(repository).Read(1);
				will(returnValue(history));
			}
		});

		HistoryManager manager = new HistoryManager(uow);

		// Act
		HistoryDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getHistoryId(), history.getHistoryId());
		Assert.assertEquals(dto.getHistoryPrice(), history.getHistoryPrice());

		mock.assertIsSatisfied();
	}
}
