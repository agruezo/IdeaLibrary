package com.alex.greatIdeas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.alex.greatIdeas.models.Idea;
import com.alex.greatIdeas.models.User;
import com.alex.greatIdeas.repositories.IdeaRepository;
import com.alex.greatIdeas.repositories.UserRepository;

@Service
public class IdeaService {
	
	@Autowired
	public IdeaRepository ideaRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	// All ideas
	public List<Idea> getAllIdeas() {
		
		return ideaRepository.findAll();
	}
	
	// Get one idea
	public Idea showOne(Long id) {
		
		return ideaRepository.findById(id).orElse(null);
	}
	
	// Create idea
	public Idea createIdea(Idea idea) {
		
		return ideaRepository.save(idea);
	}
	
	// Update idea
	public Idea updateIdea(Idea idea) {
		
		return ideaRepository.save(idea);
	}
	
	// Delete idea
	public void deleteIdea(Long id) {
		
		ideaRepository.deleteById(id);
	}
	
	// Like idea
	public void likeIdea(Idea idea, User user) {
		
		List<User> ideaLikers = idea.getIdeaLikers();
		ideaLikers.add(user);
		ideaRepository.save(idea);
		
	}
	
	// Unlike idea
	public void unlikeIdea(Idea idea, User user) {
		
		List<User> ideaLikers = idea.getIdeaLikers();
		ideaLikers.remove(user);
		ideaRepository.save(idea);
	}
	
	public List<Idea> ideaLikesDesc() {
		
		return ideaRepository.findAllByIdeaLikesOrderByIdeaLikersDesc();
		
	}
	
	public List<Idea> ideaLikesAsc() {
		
		return ideaRepository.findAllByIdeaLikersOrderByIdeaLikersAsc();
		
	}
	
	
}
