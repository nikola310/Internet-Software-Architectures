package com.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Rankscale;

/**
 * @author Nikola Stojanovic
 *
 */
public interface RankscaleRepository extends JpaRepository<Rankscale, Integer> {

	List<Rankscale> getRankscaleByScaleActiveIsTrue();
}
