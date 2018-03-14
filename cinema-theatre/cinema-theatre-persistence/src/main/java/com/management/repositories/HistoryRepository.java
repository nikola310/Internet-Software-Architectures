package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.History;

/**
 * @author Nikola Stojanovic
 *
 */
public interface HistoryRepository extends JpaRepository<History, Integer>{

}
