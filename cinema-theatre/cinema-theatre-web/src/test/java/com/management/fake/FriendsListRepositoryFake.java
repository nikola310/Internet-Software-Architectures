package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Friendslist;
import com.management.repositories.FriendsListRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendsListRepositoryFake implements FriendsListRepository{

	private Friendslist entity;
	

	public void deleteAllInBatch() {
		
	}

	
	public void deleteInBatch(Iterable<Friendslist> arg0) {
		
	}

	public List<Friendslist> findAll() {
		return null;
	}

	public List<Friendslist> findAll(Sort arg0) {
		return null;
	}

	public List<Friendslist> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends Friendslist> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends Friendslist> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {
		
	}

	public Friendslist getOne(Integer arg0) {
		return null;
	}

	public <S extends Friendslist> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends Friendslist> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<Friendslist> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {
		
	}

	public void delete(Friendslist arg0) {
		
	}

	public void delete(Iterable<? extends Friendslist> arg0) {
		
	}

	public void deleteAll() {
		
	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Friendslist findOne(Integer arg0) {
		return entity;
	}

	public <S extends Friendslist> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Friendslist> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Friendslist> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Friendslist> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Friendslist> S findOne(Example<S> arg0) {
		return null;
	}
}
