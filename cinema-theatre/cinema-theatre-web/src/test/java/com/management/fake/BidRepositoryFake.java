package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Bid;
import com.management.entities.Props;
import com.management.entities.User;
import com.management.repositories.BidRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class BidRepositoryFake implements BidRepository {

	private Bid entity;

	public List<Bid> findAll() {
		return null;
	}

	public List<Bid> findAll(Sort sort) {
		return null;
	}

	public List<Bid> findAll(Iterable<Integer> ids) {
		return null;
	}

	public <S extends Bid> List<S> save(Iterable<S> entities) {
		return null;
	}

	public void flush() {

	}

	public <S extends Bid> S saveAndFlush(S entity) {
		return null;
	}

	public void deleteInBatch(Iterable<Bid> entities) {

	}

	public void deleteAllInBatch() {

	}

	public Bid getOne(Integer id) {
		return null;
	}

	public <S extends Bid> List<S> findAll(Example<S> example) {
		return null;
	}

	public <S extends Bid> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	public Page<Bid> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {

	}

	public void delete(Bid arg0) {

	}

	public void delete(Iterable<? extends Bid> arg0) {

	}

	public void deleteAll() {

	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Bid findOne(Integer arg0) {
		return entity;
	}

	public <S extends Bid> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Bid> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Bid> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Bid> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Bid> S findOne(Example<S> arg0) {
		return null;
	}

	public List<Bid> findBidByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Bid> findByUser(User u) {
		return null;
	}

	public List<Bid> findByUserAndBidAcceptedIsNull(User u) {
		return null;
	}

	public List<Bid> findByPropsAndBidAcceptedIsNull(Props p) {
		return null;
	}

}
