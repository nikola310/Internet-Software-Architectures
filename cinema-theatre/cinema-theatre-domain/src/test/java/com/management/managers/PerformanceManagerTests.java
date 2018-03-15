package com.management.managers;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.PerformanceDTO;
import com.management.entities.Performance;
import com.management.fake.PerformanceRepositoryFake;
import com.management.repositories.PerformanceRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceManagerTests {
	private PerformanceRepository performanceRepository;

	@Test
	public void AddingNewPerformance_ReturnsBoolean() {
		//Arrange
		performanceRepository = new PerformanceRepositoryFake();
		
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
		PerformanceManager manager = new PerformanceManager(performanceRepository);

		//Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));
		
		//Performance per = performanceRepository.getOne(0);
		
		/*Assert.assertEquals(dto.getPerId(), per.getPerId());
		Assert.assertEquals(per.getPerDescription(), dto.getPerDescription());
		Assert.assertEquals(per.getPerDirector(), dto.getPerDirector());
		Assert.assertEquals(per.getPerDuration(), dto.getPerDuration());
		Assert.assertEquals(per.getPerGenre(), dto.getPerGenre());
		Assert.assertEquals((int)per.getPerPrice(), (int)dto.getPerPrice());
		Assert.assertEquals(per.getPerRank(), dto.getPerRank());
		Assert.assertEquals(per.getPerType(), dto.getPerType());*/
	}
	
	@Test
	public void DeletingPerformance_ReturnsBoolean() {
		//Arrange
		Mockery mock = new Mockery();
		performanceRepository = mock.mock(PerformanceRepository.class);
		
		// expectations
        mock.checking(new Expectations() {{
            oneOf (performanceRepository).delete(1);
        }});
		
        //Act and assert
        PerformanceManager manager = new PerformanceManager(performanceRepository);
        Assert.assertNotNull(manager);
        Assert.assertTrue(manager.Delete(1));    
        
        mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadPerformance_ReturnsPerformance() {
		//Arrange
		Mockery mock = new Mockery();
		performanceRepository = mock.mock(PerformanceRepository.class);
		
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
        
        mock.checking(new Expectations() {{
            oneOf (performanceRepository).findOne(1);
            will(returnValue(per));
        }});
        
        PerformanceManager manager = new PerformanceManager(performanceRepository);
        
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
