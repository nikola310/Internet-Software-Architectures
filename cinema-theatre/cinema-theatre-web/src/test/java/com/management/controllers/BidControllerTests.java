package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.BidDTO;
import com.management.entities.Bid;
import com.management.fake.BidRepositoryFake;
import com.management.managers.BidManager;
import com.management.repositories.BidRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class BidControllerTests {
	private BidRepository bidRepository;

	@Test
	public void AddingNewBid_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		bidRepository = new BidRepositoryFake();

		BidDTO dto = new BidDTO();
		dto.setBidAccepted(false);
		dto.setBidPrice(500);
		BidManager manager = new BidManager(bidRepository);
		BidController controller = new BidController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addBid(dto), new ResponseEntity<BidDTO>(
				dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void DeletingBid_ReturnsOK() {
		Mockery mock = new Mockery();
		bidRepository = mock.mock(BidRepository.class);

		// Expectations
		mock.checking(new Expectations() {
			{
				oneOf(bidRepository).delete(1);
			}
		});

		BidManager manager = new BidManager(bidRepository);
		// Act and assert
		BidController controller = new BidController(manager);
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteBid(1),
				new ResponseEntity<BidDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadBid_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		bidRepository = mock.mock(BidRepository.class);

		final Bid bid = new Bid();
		bid.setBidAccepted(true);
		bid.setBidPrice(500);

		mock.checking(new Expectations() {
			{
				oneOf(bidRepository).findOne(1);
				will(returnValue(bid));
			}
		});

		BidManager manager = new BidManager(bidRepository);
		BidController controller = new BidController(manager);

		// Act
		ResponseEntity<BidDTO> response = controller.getBid(1);
		BidDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<BidDTO>(dto,
				HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllBids_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		bidRepository = mock.mock(BidRepository.class);

		final ArrayList<Bid> list = new ArrayList<Bid>();

		Bid bid1 = new Bid();
		bid1.setBidAccepted(true);
		bid1.setBidPrice(300);

		Bid bid2 = new Bid();
		bid2.setBidAccepted(false);
		bid2.setBidPrice(500);

		list.add(bid1);
		list.add(bid2);

		mock.checking(new Expectations() {
			{
				oneOf(bidRepository).findAll();
				will(returnValue(list));
			}
		});

		BidManager manager = new BidManager(bidRepository);
		BidController controller = new BidController(manager);

		// Act
		ResponseEntity<List<BidDTO>> response = controller.getBids();
		ArrayList<BidDTO> dtoList = (ArrayList<BidDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<BidDTO>>(dtoList,
				HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
