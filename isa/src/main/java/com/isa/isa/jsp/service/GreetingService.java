package com.isa.isa.jsp.service;

import java.util.Collection;

import com.isa.isa.jsp.domain.Greeting;

public interface GreetingService {

	Collection<Greeting> findAll();

	Greeting findOne(Long id);

	Greeting create(Greeting greeting) throws Exception;

	Greeting update(Greeting greeting) throws Exception;

	void delete(Long id);
	
}