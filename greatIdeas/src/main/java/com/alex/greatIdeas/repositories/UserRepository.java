package com.alex.greatIdeas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alex.greatIdeas.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findAll();
	
	public User findByEmail(String email);
	
}
