package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Reservation;

/**
 * @author Nikola Stojanovic
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
