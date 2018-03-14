package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Hall;

/**
 * @author Zivko Stanisic
 *
 */
public interface HallRepository extends JpaRepository<Hall, Integer>{

}
