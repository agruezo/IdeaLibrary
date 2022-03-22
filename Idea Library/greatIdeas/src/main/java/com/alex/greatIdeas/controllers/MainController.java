package com.alex.greatIdeas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.alex.greatIdeas.models.Idea;
import com.alex.greatIdeas.models.LoginUser;
import com.alex.greatIdeas.models.User;
import com.alex.greatIdeas.services.IdeaService;
import com.alex.greatIdeas.services.UserService;



@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdeaService ideaService;
	
	
	
	@GetMapping("/")
	public String index(
			@ModelAttribute("newUser") User user,
			@ModelAttribute("newLogin") LoginUser loginUser) {
		
		return "index.jsp";
	}

	//****** REGISTER USER ******//
	
		@PostMapping("/register")
		public String register(
				@Valid @ModelAttribute("newUser") User user,
				BindingResult result,
				HttpSession session,
				@ModelAttribute("newLogin") LoginUser loginUser) {
			
			// validate user
			userService.validate(user, result);
			if(result.hasErrors()) {
				return "index.jsp";
			}
			
			// register user
			userService.registerUser(user);
			
			// put user in session
			session.setAttribute("loggedInUser", user);
			return "redirect:/ideas";
			
		}

	//****** LOGIN USER ******//
		
		@PostMapping("/login")
		public String login(
				@Valid @ModelAttribute("newLogin") LoginUser loginUser,
				BindingResult result,
				HttpSession session,
				@ModelAttribute("newUser") User user) {
			
			// authenticate user
			userService.authenticateUser(loginUser, result);
			if(result.hasErrors()) {
				return "index.jsp";
			}
			
			User loggedInUser = userService.findByEmail(loginUser.getUserEmail());
			
			// put user in session
			session.setAttribute("loggedInUser", loggedInUser);
			return "redirect:/ideas";
		}
		
	//****** LOGOUT ******//
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			
			session.invalidate();
			return "redirect:/";
		}
		
//****** DASHBOARD ******//
		
		@GetMapping("/ideas")
		public String dashboard(
				Model viewModel,
				HttpSession session) {
			
			if(session.getAttribute("loggedInUser") != null) {
				User user = (User) session.getAttribute("loggedInUser");
				User userLoggedIn = userService.findById(user.getId());
				viewModel.addAttribute("ideas", ideaService.getAllIdeas());
				viewModel.addAttribute("userLoggedIn", userLoggedIn);
				
				return "dashboard.jsp";
			}
			
			return "redirect:/";
		}
		
		@GetMapping("/ideas/highest")
		public String highestLikes(
				Model viewModel,
				HttpSession session) {
			
			if(session.getAttribute("loggedInUser") != null) {
				User user = (User) session.getAttribute("loggedInUser");
				User userLoggedIn = userService.findById(user.getId());
				
				viewModel.addAttribute("userLoggedIn", userLoggedIn);
				viewModel.addAttribute("ideas", ideaService.ideaLikesDesc());
				
				return "dashboard.jsp";
			}
			
			return "redirect:/";
		}
		
		@GetMapping("/ideas/lowest")
		public String lowestLikes(
				Model viewModel,
				HttpSession session) {
			
			if(session.getAttribute("loggedInUser") != null) {
				User user = (User) session.getAttribute("loggedInUser");
				User userLoggedIn = userService.findById(user.getId());
				
				viewModel.addAttribute("userLoggedIn", userLoggedIn);
				viewModel.addAttribute("ideas", ideaService.ideaLikesAsc());
				
				return "dashboard.jsp";
			}
			
			return "redirect:/";
		}
		
//****** CREATE IDEA ******//
		
		@GetMapping("/ideas/new")
		public String newIdea(
				@ModelAttribute("newIdea") Idea idea,
				HttpSession session) {
			
			if(session.getAttribute("loggedInUser") != null) {
				return "new.jsp";
			} else {
				return "redirect:/";
			}
		}
		
		@PostMapping("/ideas/create")
		public String createIdea(
				@Valid @ModelAttribute("newIdea") Idea idea,
				BindingResult result,
				HttpSession session) {
			
			if(result.hasErrors()) {
				return "new.jsp";
			}
			
			ideaService.createIdea(idea);
			return "redirect:/ideas";
		}
		
//****** DISPLAY IDEA ******//		
		
		@GetMapping("/ideas/{id}")
		public String showIdea(
				@PathVariable("id") Long id,
				Model viewModel,
				HttpSession session) {
			
			if(session.getAttribute("loggedInUser") != null) {
				
				
				Idea idea = ideaService.showOne(id);
				
				viewModel.addAttribute("idea", idea);
				viewModel.addAttribute("user", userService.findByEmail("loggedInUser"));
				viewModel.addAttribute("likers", idea.getIdeaLikers());
				
				return "show.jsp";
			} 
			
			return "redirect:/";
		}
		
//****** EDIT IDEA ******//
		
		@GetMapping("/ideas/{id}/edit")
		public String editIdea(
				@PathVariable("id") Long id,
				Model viewModel,
				HttpSession session) {
			
			if(session.getAttribute("loggedInUser") != null) {
				
				Idea idea = ideaService.showOne(id);
				 
				viewModel.addAttribute("editIdea", idea);
				
				return "edit.jsp";
			}
			
			return "redirect:/";
		}
		
		@PutMapping("/ideas/{id}/update")
		public String updateIdea(
				@PathVariable("id") Long id,
				@Valid @ModelAttribute("editIdea") Idea idea,
				BindingResult result,
				Model viewModel) {
			
			if(result.hasErrors()) {
				
				return "edit.jsp";
			}
			
			return "redirect:/ideas";
		}
		
//****** DELETE IDEA ******//
		
		@DeleteMapping("/ideas/{id}/delete")
		public String destroyIdea(
				@PathVariable("id") Long id) {
			
			ideaService.deleteIdea(id);
			return "redirect:/ideas";
		}

//****** LIKE AND UNLIKE IDEA ******//
		
		@GetMapping("/ideas/{id}/like")
		public String likeIdea(
				@PathVariable("id") Long id,
				HttpSession session) {
			
			User user = (User) session.getAttribute("loggedInUser");
			User ideaLiker = userService.findById(user.getId());
			
			Idea idea=ideaService.showOne(id);
			ideaService.likeIdea(idea, ideaLiker);
			
			return "redirect:/ideas";
		}
		
		@GetMapping("/ideas/{id}/unlike")
		public String unlikeIdea(
				@PathVariable("id") Long id,
				HttpSession session) {
			
			User user = (User) session.getAttribute("loggedInUser");
			User ideaUnLiker = userService.findById(user.getId());
			
			Idea idea=ideaService.showOne(id);
			ideaService.unlikeIdea(idea, ideaUnLiker);
			return "redirect:/ideas";
		}
		
}
