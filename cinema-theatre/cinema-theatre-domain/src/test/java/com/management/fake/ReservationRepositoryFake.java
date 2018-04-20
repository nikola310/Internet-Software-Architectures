/**
 * 
 */
package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Props;
import com.management.entities.Reservation;
import com.management.repositories.ReservationRepository;

/**
 * @author Nikola Stojanovic
 *
 */
public class ReservationRepositoryFake implements ReservationRepository {

	private Reservation entity;
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<Reservation> findAll() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll(org.springframework.data.domain.Sort)
	 */
	public List<Reservation> findAll(Sort sort) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll(java.lang.Iterable)
	 */
	public List<Reservation> findAll(Iterable<Integer> ids) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#save(java.lang.Iterable)
	 */
	public <S extends Reservation> List<S> save(Iterable<S> entities) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#flush()
	 */
	public void flush() {

	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#saveAndFlush(java.lang.Object)
	 */
	public <S extends Reservation> S saveAndFlush(S entity) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#deleteInBatch(java.lang.Iterable)
	 */
	public void deleteInBatch(Iterable<Reservation> entities) {

	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#deleteAllInBatch()
	 */
	public void deleteAllInBatch() {

	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#getOne(java.io.Serializable)
	 */
	public Reservation getOne(Integer id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll(org.springframework.data.domain.Example)
	 */
	public <S extends Reservation> List<S> findAll(Example<S> example) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Sort)
	 */
	public <S extends Reservation> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
	 */
	public Page<Reservation> findAll(Pageable pageable) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	public <S extends Reservation> S save(S entity) {
		this.entity = entity;
		return entity;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)
	 */
	public Reservation findOne(Integer id) {
		return entity;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#exists(java.io.Serializable)
	 */
	public boolean exists(Integer id) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#count()
	 */
	public long count() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)
	 */
	public void delete(Integer id) {

	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
	 */
	public void delete(Reservation entity) {

	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Iterable)
	 */
	public void delete(Iterable<? extends Reservation> entities) {

	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	public void deleteAll() {

	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.query.QueryByExampleExecutor#findOne(org.springframework.data.domain.Example)
	 */
	public <S extends Reservation> S findOne(Example<S> example) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Pageable)
	 */
	public <S extends Reservation> Page<S> findAll(Example<S> example,
			Pageable pageable) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.query.QueryByExampleExecutor#count(org.springframework.data.domain.Example)
	 */
	public <S extends Reservation> long count(Example<S> example) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.query.QueryByExampleExecutor#exists(org.springframework.data.domain.Example)
	 */
	public <S extends Reservation> boolean exists(Example<S> example) {
		return false;
	}

	public List<Reservation> findByProps(Props p) {
		// TODO Auto-generated method stub
		return null;
	}

}
