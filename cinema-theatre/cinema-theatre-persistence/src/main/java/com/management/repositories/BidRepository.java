package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer> {

}
