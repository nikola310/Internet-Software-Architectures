package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Rankscale;

/**
 * @author Nikola Stojanovic
 *
 */
public interface RankscaleRepository extends JpaRepository<Rankscale, Integer> {

}
