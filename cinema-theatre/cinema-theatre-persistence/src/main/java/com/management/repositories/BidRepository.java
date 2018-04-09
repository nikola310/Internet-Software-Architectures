package com.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer> {

//	public List<Bid> findBidByUserId(int id);
}
