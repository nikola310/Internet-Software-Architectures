package com.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Friendslist;

/**
 * @author Zivko Stanisic
 *
 */
public interface FriendsListRepository extends JpaRepository<Friendslist, Integer>{

}
