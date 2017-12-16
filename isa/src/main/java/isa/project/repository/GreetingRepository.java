package isa.project.repository;

import java.util.Collection;

import isa.project.domain.Greeting;

public interface GreetingRepository {

	Collection<Greeting> findAll();

	Greeting create(Greeting greeting);

	Greeting findOne(Long id);
	
	Greeting update(Greeting greeting);

	void delete(Long id);

}
