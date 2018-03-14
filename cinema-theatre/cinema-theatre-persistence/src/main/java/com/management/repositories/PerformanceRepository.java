package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Performance;

/**
 * @author Zivko Stanisic
 *
 */
public interface PerformanceRepository extends JpaRepository<Performance, Integer>{

}
