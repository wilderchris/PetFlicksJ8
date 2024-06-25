package com.petflicks.app.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petflicks.app.Exception.UserNotFoundException;
import com.petflicks.app.Exception.UsernameAlreadyExists;
import com.petflicks.app.Models.User;
import com.petflicks.app.Service.UserService;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	
	private UserService userService;
	
	
	public UserController() {
		super();
	}
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	
	
	@PostMapping(path="/v1/register")
	public ResponseEntity<Map<String,Integer>> register(@RequestBody User newUser){
		//This methods responsibility is to sign up a new user
		//no checking if user already exists
		//no current path??
		try
		{
//			UserDetail userDetail = new UserDetail(newUser);
//			String token=tokenProvider.generateToken(userDetail);
			newUser = userService.register(newUser);
			Map<String, Integer> newIdMap = new HashMap<>();
			newIdMap.put("generatedId", newUser.getUserId());
			return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);
			
		}catch (UsernameAlreadyExists e)	{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
				
//	@PostMapping(path="/auth")
//	public ResponseEntity<User> logIn(@RequestBody Map<String, String> credentials){
//		//This methods responsibility is to log in the user
//
//		String username = credentials.get("username");
//		String password = credentials.get("password");
//		
//
//		try {
//			//UserDetail userDetail1 = new UserDetail(userService.getUserByUsername(username));
//			//this.authenticate(username, password);
////			User user = userService.login(username, password);
//			User user = userService.getUserByUsername(username);
//			//UserDetail userDetail = new UserDetail(user);
//		//	HttpHeaders jwtHeader = this.getHeader(userDetail);
//			
//		//	String token = Integer.toString(user.getUserId());
//			
//			return new ResponseEntity<>(user, HttpStatus.OK);
//
//		} catch (UserNotFoundException e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
	
//	@GetMapping(path="/{userId}/auth")
//	public ResponseEntity<User> checkLogin(@PathVariable String userId) throws UserNotFoundException{
//		
//		try {
//		//	String token = tokenProvider.extractToken(authorization);
//		//	HttpHeaders jwtHeader = tokenProvider.getHeaderJWT(token);
//			User loggedInPerson =userService.getUserById(Integer.parseInt(userId));
//			if(loggedInPerson!=null)
//				return new ResponseEntity<>(loggedInPerson, HttpStatus.OK);
//			else
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//		catch (UserNotFoundException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//	}

	@GetMapping(path="/v1/{userId}")//
	public ResponseEntity<User> getUserById(@PathVariable int userId) throws UserNotFoundException{
		System.out.println("get by user id");
		User user = userService.getUserById(userId);
		if (user != null) {
		return ResponseEntity.ok(user);
	} else
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path="/email/{email}")//
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) throws UserNotFoundException{
		User user = userService.getUserByEmail(email);
		if (user != null) {
		return ResponseEntity.ok(user);
		} else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path="/username/{username}")//
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws UserNotFoundException{
		User user = userService.getUserByUsername(username);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else
			return ResponseEntity.notFound().build();
	}

	
	@PutMapping(path="/{userId}")//
	public ResponseEntity<User> updateUser(@PathVariable int userId,
			@RequestBody User userToEdit) throws UserNotFoundException {
			
		if (userToEdit != null && userToEdit.getUserId() == userId) {
			userToEdit = userService.update(userToEdit);
			
			if (userToEdit != null)
				return ResponseEntity.ok(userToEdit);
			else
				return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@DeleteMapping(path="/{userId}")//
	public ResponseEntity<Void> deleteUser(@RequestBody User deleteUser) throws UserNotFoundException {
		if (deleteUser != null) {
			userService.deleteUser(deleteUser);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
	
}
