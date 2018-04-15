package com.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Bid;
import com.management.entities.Props;
import com.management.entities.User;

public interface BidRepository extends JpaRepository<Bid, Integer> {

	List<Bid> findByUser(User u);
	
	List<Bid> findByUserAndBidAcceptedIsNull(User u);
	
	List<Bid> findByPropsAndBidAcceptedIsNull(Props p);
}
