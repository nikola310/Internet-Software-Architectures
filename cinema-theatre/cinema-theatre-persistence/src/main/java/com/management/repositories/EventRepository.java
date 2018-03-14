package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Event;

/**
 * @author Zivko Stanisic
 *
 */
public interface EventRepository extends JpaRepository<Event, Integer>{

}
