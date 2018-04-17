package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Performance;
import com.management.repositories.PerformanceRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceRepositoryFake implements PerformanceRepository{

	private Performance entity;
	

	public void deleteAllInBatch() {
		
	}

	public void deleteInBatch(Iterable<Performance> arg0) {
		
	}
	

	public List<Performance> findAll() {
		return null;
	}

	public List<Performance> findAll(Sort arg0) {
		return null;
	}

	public List<Performance> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends Performance> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends Performance> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {
		
	}

	public Performance getOne(Integer arg0) {
		return null;
	}

	public <S extends Performance> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends Performance> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<Performance> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {
		
	}

	public void delete(Performance arg0) {
		
	}

	public void delete(Iterable<? extends Performance> arg0) {
		
	}

	public void deleteAll() {
		
	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Performance findOne(Integer arg0) {
		return entity;
	}

	public <S extends Performance> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Performance> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Performance> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Performance> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Performance> S findOne(Example<S> arg0) {
		return null;
	}
}
