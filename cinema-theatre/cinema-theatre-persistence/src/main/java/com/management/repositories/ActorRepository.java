package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Actor;

/**
 * @author Zivko Stanisic
 *
 */
public interface ActorRepository extends JpaRepository<Actor, Integer>{

}
