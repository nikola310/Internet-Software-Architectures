package com.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Props;
import com.management.entities.User;

/**
 * @author Nikola Stojanovic
 *
 */
public interface PropsRepository extends JpaRepository<Props, Integer>{

	List<Props> getPropsByPropsApprovedIsNull();
	
	List<Props> getPropsByPropsUsedIsFalse();
	
	List<Props> getPropsByUserAndPropsApprovedIsTrue(User user);
}
