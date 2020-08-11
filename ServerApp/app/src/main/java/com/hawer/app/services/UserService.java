package com.hawer.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawer.app.accessingdata.UserRepository;
import com.hawer.app.entity.User;
import com.hawer.app.exception.ServiceException;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	public User getUser(Long id) throws ServiceException {
		if (userRepository.existsById(id)) {
			
			User user =  userRepository.findUserById(id);
			return user;
		}
		return null;
	}

	public User saveNewUser(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(userDetailsService.encodePassword(user.getPassword()));
		newUser.setRole(user.getRole());
		newUser.setDescription(user.getDescription());
		newUser.setEmail(user.getEmail());
		return userRepository.save(newUser);
	}

	public void deleteUser(Long id) throws ServiceException {
		userRepository.deleteById(id);
	}

	public void updatePassword(User user) {
		userRepository.updatePassword(userDetailsService.encodePassword(user.getPassword()), user.getUsername());
	}

	public void updateDataUser(User user) {
		Long id = user.getId();
		if (userRepository.existsById(id)) {
			String username = user.getUsername();
			String password = user.getPassword();
			String role = user.getRole();
			String description = user.getDescription();
			String email = user.getEmail();
			if (username == null || username.isBlank()) {
				username = userRepository.findUserById(id).getUsername();
				user.setUsername(username);
			}
			if (password == null || password.isBlank() || password.length() < 6) {
				password = userRepository.findUserById(id).getPassword();
				user.setPassword(password);
			} else {
				user.setPassword(userDetailsService.encodePassword(password));
			}
			if (role == null || role.isBlank()) {
				role = userRepository.findUserById(id).getRole();
				user.setRole(role);
			}
			if (description == null || description.isBlank()) {
				description = userRepository.findUserById(id).getDescription();
				user.setDescription(description);
			}
			if (email == null || email.isBlank()) {
				email = userRepository.findUserById(id).getEmail();
				user.setEmail(email);
			}
			userRepository.save(user);
		}
	}

	public List<User> getUsers() {
		return userRepository.findAndSortAllUsers();
	}

	public User hasRole(String username) {
		return userRepository.findByUsername(username);
	}

	public boolean existsUser(String username) {
		if (userRepository.loadUserByUsername(username) == null)
			return false;
		else
			return true;
	}

}
