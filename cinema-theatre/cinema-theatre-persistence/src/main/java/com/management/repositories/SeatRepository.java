package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Seat;

/**
 * @author Zivko Stanisic
 *
 */
public interface SeatRepository extends JpaRepository<Seat, Integer>{

}
