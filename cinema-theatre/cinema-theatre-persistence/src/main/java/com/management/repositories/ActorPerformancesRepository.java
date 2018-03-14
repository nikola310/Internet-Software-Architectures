package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Actorperformances;

/**
 * @author Zivko Stanisic
 *
 */
public interface ActorPerformancesRepository extends JpaRepository<Actorperformances, Integer>{

}
