package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Actor;
import com.management.repositories.ActorRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorRepositoryFake implements ActorRepository {

	private Actor entity;

	public void deleteAllInBatch() {

	}

	public void deleteInBatch(Iterable<Actor> arg0) {

	}

	public List<Actor> findAll() {
		return null;
	}

	public List<Actor> findAll(Sort arg0) {
		return null;
	}

	public List<Actor> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends Actor> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends Actor> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {

	}

	public Actor getOne(Integer arg0) {
		return null;
	}

	public <S extends Actor> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends Actor> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<Actor> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {

	}

	public void delete(Actor arg0) {

	}

	public void delete(Iterable<? extends Actor> arg0) {

	}

	public void deleteAll() {

	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Actor findOne(Integer arg0) {
		return entity;
	}

	public <S extends Actor> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Actor> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Actor> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Actor> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Actor> S findOne(Example<S> arg0) {
		return null;
	}
}
