package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Segment;

public interface SegmentRepository extends JpaRepository<Segment, Integer>{

}
