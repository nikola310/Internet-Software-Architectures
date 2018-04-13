package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Props;
import com.management.repositories.PropsRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class PropsRepositoryFake implements PropsRepository {

	private Props entity;
	
	public List<Props> findAll() {
		return null;
	}

	public List<Props> findAll(Sort sort) {
		return null;
	}

	public List<Props> findAll(Iterable<Integer> ids) {
		return null;
	}

	public <S extends Props> List<S> save(Iterable<S> entities) {
		return null;
	}

	public void flush() {

	}

	public <S extends Props> S saveAndFlush(S entity) {
		return null;
	}

	public void deleteInBatch(Iterable<Props> entities) {

	}

	public void deleteAllInBatch() {

	}

	public Props getOne(Integer id) {
		return null;
	}

	public <S extends Props> List<S> findAll(Example<S> example) {
		return null;
	}

	public <S extends Props> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	public Page<Props> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {

	}

	public void delete(Props arg0) {

	}

	public void delete(Iterable<? extends Props> arg0) {

	}

	public void deleteAll() {

	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Props findOne(Integer arg0) {
		return entity;
	}

	public <S extends Props> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Props> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Props> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Props> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Props> S findOne(Example<S> arg0) {
		return null;
	}

	public List<Props> getPropsByPropsApprovedIsNull() {
		return null;
	}

}
