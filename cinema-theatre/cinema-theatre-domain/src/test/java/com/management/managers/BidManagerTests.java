package com.management.managers;

import java.io.IOException;
import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.BidDTO;
import com.management.entities.Bid;
import com.management.fake.BidRepositoryFake;
import com.management.repositories.BidRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class BidManagerTests {
	private BidRepository bidRepository;

	@Test
	public void AddingNewBid_ReturnsBoolean(){
		// Arrange
		bidRepository = new BidRepositoryFake();

		BidDTO dto = new BidDTO();
		// dto.setBidAccepted(false);
		dto.setBidPrice(300);

		BidManager manager = new BidManager(bidRepository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		Bid bid = bidRepository.findOne(0);

		Assert.assertEquals(dto.getBidId(), bid.getBidId());
		Assert.assertEquals(dto.getBidPrice(), bid.getBidPrice(), 0.01);
		// Assert.assertEquals(dto.isBidAccepted(), bid.getBidAccepted());
	}
	
	@Test
	public void DeletingBid_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		bidRepository = mock.mock(BidRepository.class);

		// Expectations
		mock.checking(new Expectations() {
			{
				oneOf(bidRepository).delete(1);
			}
		});

		// Act and assert
		BidManager manager = new BidManager(bidRepository);
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadBid_ReturnsBid() throws IOException {
		// Arrange
		Mockery mock = new Mockery();
		bidRepository = mock.mock(BidRepository.class);

		final Bid bid = new Bid();
		bid.setBidAccepted(false);
		bid.setBidPrice(300);

		mock.checking(new Expectations() {
			{
				oneOf(bidRepository).findOne(1);
				will(returnValue(bid));
			}
		});

		BidManager manager = new BidManager(bidRepository);

		// Act
		BidDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getBidId(), bid.getBidId());
		Assert.assertEquals(dto.getBidPrice(), bid.getBidPrice(), 0.01);
		// Assert.assertEquals(dto.isBidAccepted(), bid.getBidAccepted());
		mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadAllBids_ReturnsAllBids() throws IOException {
		// Arrange
		Mockery mock = new Mockery();
		bidRepository = mock.mock(BidRepository.class);

		final ArrayList<Bid> list = new ArrayList<Bid>();

		Bid b1 = new Bid();
		b1.setBidAccepted(true);
		b1.setBidPrice(500);

		Bid b2 = new Bid();
		b2.setBidAccepted(true);
		b2.setBidPrice(500);

		list.add(b1);
		list.add(b2);

		mock.checking(new Expectations() {
			{
				oneOf(bidRepository).findAll();
				will(returnValue(list));
			}
		});

		BidManager manager = new BidManager(bidRepository);

		// Act
		ArrayList<BidDTO> dtoList = manager.ReadAll();

		// Assert
		Assert.assertNotNull(dtoList);

		Assert.assertEquals(dtoList.get(0).getBidId(), list.get(0)
				.getBidId());
		Assert.assertEquals(dtoList.get(0).getBidPrice(), list.get(0)
				.getBidPrice(), 0.01);
		// Assert.assertEquals(dtoList.get(0).isBidAccepted(), list.get(0)
		//		.getBidAccepted());


		Assert.assertEquals(dtoList.get(1).getBidId(), list.get(1)
				.getBidId());
		Assert.assertEquals(dtoList.get(1).getBidPrice(), list.get(1)
				.getBidPrice(), 0.01);
		// Assert.assertEquals(dtoList.get(1).isBidAccepted(), list.get(1)
		//		.getBidAccepted());		
		mock.assertIsSatisfied();
	}
}
