package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.User;
import com.management.repositories.UserRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class UserRepositoryFake implements UserRepository{
	
	private User entity;
	

	public void deleteAllInBatch() {
		
	}

	public void deleteInBatch(Iterable<User> arg0) {
		
	}

	public List<User> findAll() {
		return null;
	}

	public List<User> findAll(Sort arg0) {
		return null;
	}

	public List<User> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends User> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends User> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {
		
	}

	public User getOne(Integer arg0) {
		return null;
	}

	public <S extends User> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends User> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<User> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {
		
	}

	public void delete(User arg0) {
		
	}

	public void delete(Iterable<? extends User> arg0) {
		
	}

	public void deleteAll() {
		
	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public User findOne(Integer arg0) {
		return entity;
	}

	public <S extends User> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends User> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends User> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends User> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends User> S findOne(Example<S> arg0) {
		return null;
	}
}
