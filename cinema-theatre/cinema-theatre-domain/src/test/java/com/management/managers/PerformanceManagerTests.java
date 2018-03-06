package com.management.managers;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.PerformanceDTO;
import com.management.entities.Performance;
import com.management.fake.UnitOfWorkFake;
import com.management.interfaces.PerformanceRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceManagerTests {
	private UnitOfWorkInterface uow;

	@Test
	public void AddingNewPerformance_ReturnsBoolean() {
		//Arrange
		uow = new UnitOfWorkFake();
		
		PerformanceDTO dto = new PerformanceDTO();
		dto.setPerCreationDate(new Date());
		dto.setPerDescription("Neki opis.");
		dto.setPerDirector("Nikola Stojanovic");
		dto.setPerDuration(11);
		dto.setPerGenre("Komedija");
		dto.setPerPoster(new byte[] {121});
		dto.setPerPrice(100);
		dto.setPerRank(0);
		dto.setPerType('M');
		PerformanceManager manager = new PerformanceManager(uow);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		Performance per = uow.getPerformanceRepository().Read(dto.getPerId());
		
		Assert.assertEquals(dto.getPerId(), per.getPerId());
		Assert.assertEquals(per.getPerDescription(), dto.getPerDescription());
		Assert.assertEquals(per.getPerDirector(), dto.getPerDirector());
		Assert.assertEquals(per.getPerDuration(), dto.getPerDuration());
		Assert.assertEquals(per.getPerGenre(), dto.getPerGenre());
		Assert.assertEquals((int)per.getPerPrice(), (int)dto.getPerPrice());
		Assert.assertEquals(per.getPerRank(), dto.getPerRank());
		Assert.assertEquals(per.getPerType(), dto.getPerType());
	}
	
	@Test
	public void DeletingPerformance_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getPerformanceRepository().Delete(1);
        }});
		
        //Act and assert
        PerformanceManager manager = new PerformanceManager(uow);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadPerformance_ReturnsPerformance() {
		//Arrange
		Mockery mock = new Mockery();
		uow = mock.mock(UnitOfWorkInterface.class);
		final PerformanceRepositoryInterface repository = mock.mock(PerformanceRepositoryInterface.class);
		
		final Performance per = new Performance();
		per.setPerCreationDate(new Date());
		per.setPerDescription("Neki opis.");
		per.setPerDirector("Nikola Stojanovic");
		per.setPerDuration(11);
		per.setPerGenre("Komedija");
		per.setPerPoster(new byte[] {121});
		per.setPerPrice(100);
		per.setPerRank(0);
		per.setPerType('M');
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (uow).getPerformanceRepository();
            will(returnValue(repository));
        }});
        
        mock.checking(new Expectations() {{
            oneOf (repository).Read(1);
            will(returnValue(per));
        }});
        
        PerformanceManager manager = new PerformanceManager(uow);
        
        //Act
        PerformanceDTO dto = manager.Read(1);
        
        //Assert
        Assert.assertNotNull(dto);
        
        Assert.assertEquals(dto.getPerId(), per.getPerId());
        Assert.assertEquals(per.getPerDescription(), dto.getPerDescription());
		Assert.assertEquals(per.getPerDirector(), dto.getPerDirector());
		Assert.assertEquals(per.getPerDuration(), dto.getPerDuration());
		Assert.assertEquals(per.getPerGenre(), dto.getPerGenre());
		Assert.assertEquals((int)per.getPerPrice(), (int)dto.getPerPrice());
		Assert.assertEquals(per.getPerRank(), dto.getPerRank());
		Assert.assertEquals(per.getPerType(), dto.getPerType());
        
        mock.assertIsSatisfied();
	}
}
