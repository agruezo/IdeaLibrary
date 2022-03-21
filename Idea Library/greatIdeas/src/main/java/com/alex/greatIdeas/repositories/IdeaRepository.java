package com.alex.greatIdeas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alex.greatIdeas.models.Idea;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long>{

	List<Idea> findAll();
	
	@Query("SELECT i FROM Idea i ORDER by i.ideaLikers.size DESC")
	List<Idea> findAllByIdeaLikesOrderByIdeaLikersDesc();
	
	@Query("SELECT i FROM Idea i ORDER by i.ideaLikers.size ASC")
	List<Idea> findAllByIdeaLikersOrderByIdeaLikersAsc();
	
	
}
