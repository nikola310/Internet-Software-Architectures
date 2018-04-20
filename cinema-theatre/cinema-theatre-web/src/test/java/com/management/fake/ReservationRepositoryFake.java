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

	public List<Reservation> findAll() {
		return null;
	}

	public List<Reservation> findAll(Sort sort) {
		return null;
	}

	public List<Reservation> findAll(Iterable<Integer> ids) {
		return null;
	}

	public <S extends Reservation> List<S> save(Iterable<S> entities) {
		return null;
	}

	public void flush() {

	}

	public <S extends Reservation> S saveAndFlush(S entity) {
		return null;
	}

	public void deleteInBatch(Iterable<Reservation> entities) {

	}

	public void deleteAllInBatch() {

	}

	public Reservation getOne(Integer id) {
		return null;
	}

	public <S extends Reservation> List<S> findAll(Example<S> example) {
		return null;
	}

	public <S extends Reservation> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	public Page<Reservation> findAll(Pageable pageable) {
		return null;
	}

	public <S extends Reservation> S save(S entity) {
		this.entity = entity;
		return entity;
	}

	public Reservation findOne(Integer id) {
		return entity;
	}

	public boolean exists(Integer id) {
		return false;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer id) {

	}

	public void delete(Reservation entity) {

	}

	public void delete(Iterable<? extends Reservation> entities) {

	}

	public void deleteAll() {

	}

	public <S extends Reservation> S findOne(Example<S> example) {
		return null;
	}

	public <S extends Reservation> Page<S> findAll(Example<S> example,
			Pageable pageable) {
		return null;
	}

	public <S extends Reservation> long count(Example<S> example) {
		return 0;
	}

	public <S extends Reservation> boolean exists(Example<S> example) {
		return false;
	}

	public List<Reservation> findByProps(Props p) {
		// TODO Auto-generated method stub
		return null;
	}

}
