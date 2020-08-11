package com.hawer.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hawer.app.dto.UserDto;
import com.hawer.app.exception.ResourceNotFoundException;
import com.hawer.app.exception.ServiceException;
import com.hawer.app.mappers.UserMapper;
import com.hawer.app.services.UserService;


@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@PostMapping(value = "/register")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) throws Exception {
		try {
			if (userDto.getPassword().length() < 6 || userDto.getPassword().length() == 0) {
				throw new ServiceException("Niepoprawna długość hasła");
			}
			if (userService.existsUser(userDto.getUsername())) {
				throw new ServiceException("Użytkownik z taką nazwą już istnieje");
			}
			if (userDto.getUsername().isBlank() || userDto.getUsername() == null) {
				throw new ServiceException("Użytkownik musi mieć nazwę");
			}
			if(userDto.getRole() == null) {
				throw new ServiceException("Ustaw rolę użytkownika");
			}
			if(userDto.getEmail().isBlank() || !userDto.getEmail().contains("@") || userDto.getEmail().length() < 5) {
				throw new ServiceException("Nieprawidłowy adres email");
			}
			return ResponseEntity.ok(userMapper.userToUserDto(userService.saveNewUser(userMapper.userDtoToUser(userDto))));
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@PostMapping(value = "/authenticateRole")
	public UserDto role(@RequestBody String username) throws ResourceNotFoundException {
		UserDto userDto = userMapper.userToUserDto(userService.hasRole(username));
		if (userDto == null) {
			throw new ResourceNotFoundException("Nie znaleziono użytkownika");
		}
		return userDto;
	}

	@DeleteMapping(value = "/admin-settings/{userId}")
	public void deleteUser(@PathVariable("userId") Long id) throws ServiceException, ResourceNotFoundException {
		try {
			if (userMapper.userToUserDto(userService.getUser(id)) == null) {
				throw new ResourceNotFoundException("Nie znaleziono użytkownika");
			}
			userService.deleteUser(id);
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@GetMapping(value = "/admin-settings/{id}")
	public UserDto getUser(@PathVariable("id") Long id) throws ServiceException, ResourceNotFoundException {
		try {
			UserDto userDto = userMapper.userToUserDto(userService.getUser(id));
			if (userDto == null) {
				throw new ResourceNotFoundException("Nie znaleziono użytkownika");
			}
			return userDto;
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@GetMapping(value = "/admin-settings/users")
	public List<UserDto> getUsers() throws ServiceException {
		List<UserDto> userList = userMapper.toUserDtoList(userService.getUsers());
		if (userList.isEmpty()) {
			throw new ServiceException("Lista użytkowników jest pusta");
		}
		return userList;
	}

	@PatchMapping(value = "/settings")
	public void updatePassword(@RequestBody UserDto userDto) throws ServiceException {
		try {
			if (userDto.getPassword().length() < 6) {
				throw new ServiceException("Niepoprawna długość hasła");
			}
			userService.updatePassword(userMapper.userDtoToUser(userDto));
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@PutMapping(value = "admin-settings/user")
	public void updateUser(@RequestBody UserDto userDto) throws ServiceException {
		try {
			if (userService.existsUser(userDto.getUsername())) {
				throw new ServiceException("Użytkownik z taką nazwą już istnieje");
			}
			if (userDto.getPassword().length() >= 6 || userDto.getPassword().length() == 0) {
			} else {
				throw new ServiceException("Niepoprawna długość hasła");
			}
			if (userDto.getEmail().length() >= 5 || userDto.getEmail().length() == 0) {
			} else {
				throw new ServiceException("Niepoprawny adres email");
			}
			System.out.println(userDto.getEmail());
			userService.updateDataUser(userMapper.userDtoToUser(userDto));
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
