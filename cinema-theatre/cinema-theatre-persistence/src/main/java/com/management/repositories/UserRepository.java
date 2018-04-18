package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.User;

/**
 * @author Zivko Stanisic
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findUserByUserToken(String token);
	public User findUserByUserEmail(String email);
}
