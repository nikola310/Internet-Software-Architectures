package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.CinemaTheatre;
import com.management.repositories.CinemaTheatreRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreRepositoryFake implements CinemaTheatreRepository{
	
	private CinemaTheatre entity;
	

	public void deleteInBatch(Iterable<CinemaTheatre> arg0) {
		
	}

	public List<CinemaTheatre> findAll() {
		return null;
	}

	public List<CinemaTheatre> findAll(Sort arg0) {
		return null;
	}

	public List<CinemaTheatre> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends CinemaTheatre> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends CinemaTheatre> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {
		
	}

	public CinemaTheatre getOne(Integer arg0) {
		return null;
	}

	public <S extends CinemaTheatre> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends CinemaTheatre> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<CinemaTheatre> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {
		
	}

	public void delete(CinemaTheatre arg0) {
		
	}

	public void delete(Iterable<? extends CinemaTheatre> arg0) {
		
	}

	public void deleteAll() {
		
	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public CinemaTheatre findOne(Integer arg0) {
		return entity;
	}

	public <S extends CinemaTheatre> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends CinemaTheatre> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends CinemaTheatre> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends CinemaTheatre> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends CinemaTheatre> S findOne(Example<S> arg0) {
		return null;
	}

	public void deleteAllInBatch() {
		
	}
}
