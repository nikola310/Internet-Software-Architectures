package com.isa.isa.jsp.repository;

import java.util.Collection;

import com.isa.isa.jsp.domain.Greeting;

public interface GreetingRepository {

	Collection<Greeting> findAll();

	Greeting create(Greeting greeting);

	Greeting findOne(Long id);
	
	Greeting update(Greeting greeting);

	void delete(Long id);

}
