package com.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Props;
import com.management.entities.Reservation;

/**
 * @author Nikola Stojanovic
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	public List<Reservation> findByProps(Props p);
}
