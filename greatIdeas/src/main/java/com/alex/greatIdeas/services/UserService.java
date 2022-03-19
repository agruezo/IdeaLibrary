package com.alex.greatIdeas.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.alex.greatIdeas.models.LoginUser;
import com.alex.greatIdeas.models.User;
import com.alex.greatIdeas.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
		// validate user for - duplicate email and password mismatch
		public void validate(User newUser, Errors errors) {
			// Password Matching
			if(!newUser.getPassword().equals(newUser.getConfirm())) {
				errors.rejectValue("password", "Mismatch", "Password does not match!!!");
			}
			// Email Duplicate
			if(userRepository.findByEmail(newUser.getEmail())!=null) {
				errors.rejectValue("email", "unique", "Email is already taken!!!");
			}
				
		}
			
		//	register user and hash their password
		public User registerUser(User newUser) {
				
			String hashedPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashedPW);
			return userRepository.save(newUser);
				
		}
			
		//	find user by email
		public User findByEmail(String email) {
				
			return userRepository.findByEmail(email);
		} 
		
		//	find user by id
		public User findById(Long id) {
				
			return userRepository.findById(id).orElse(null);
		}
			
			
		//	authenticate user
		public boolean authenticateUser(LoginUser newLogin, Errors errors) {
				
			User user = userRepository.findByEmail(newLogin.getUserEmail());
			if(user == null) {
				errors.rejectValue("userEmail", "missingEmail", "Email not found!!!");
				return false;
			} else {
				if(!BCrypt.checkpw(newLogin.getUserPassword(), user.getPassword())) {
					errors.rejectValue("userPassword", "Matches", "Invalid Password!!!");
					return false;
				}
			}
			
			return true;
		}
		
		// get all users
		public List<User> getAllUsers() {
			
			return userRepository.findAll();
		}
}
