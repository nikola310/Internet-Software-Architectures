package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Rankscale;
import com.management.repositories.RankscaleRepository;

public class RankscaleRepositoryFake implements RankscaleRepository {

	private Rankscale entity;
	
	public List<Rankscale> findAll() {
		return null;
	}

	public List<Rankscale> findAll(Sort sort) {
		return null;
	}

	public List<Rankscale> findAll(Iterable<Integer> ids) {
		return null;
	}

	public <S extends Rankscale> List<S> save(Iterable<S> entities) {
		return null;
	}

	public void flush() {

	}

	public <S extends Rankscale> S saveAndFlush(S entity) {
		return null;
	}

	public void deleteInBatch(Iterable<Rankscale> entities) {

	}

	public void deleteAllInBatch() {

	}

	public Rankscale getOne(Integer id) {
		return null;
	}

	public <S extends Rankscale> List<S> findAll(Example<S> example) {
		return null;
	}

	public <S extends Rankscale> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	public Page<Rankscale> findAll(Pageable pageable) {
		return null;
	}

	public <S extends Rankscale> S save(S entity) {
		this.entity = entity ;
		return entity ;
	}

	public Rankscale findOne(Integer id) {
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

	public void delete(Rankscale entity) {

	}

	public void delete(Iterable<? extends Rankscale> entities) {

	}

	public void deleteAll() {

	}

	public <S extends Rankscale> S findOne(Example<S> example) {
		return null;
	}

	public <S extends Rankscale> Page<S> findAll(Example<S> example,
			Pageable pageable) {
		return null;
	}

	public <S extends Rankscale> long count(Example<S> example) {
		return 0;
	}

	public <S extends Rankscale> boolean exists(Example<S> example) {
		return false;
	}

	public List<Rankscale> getRankscaleByScaleActiveIsTrue() {
		return null;
	}

}
