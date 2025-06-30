package com.alpha.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.app.DTO.UserSignUpDTO;
import com.alpha.app.Entity.User;
import com.alpha.app.Service.IUserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	
	@PostMapping("/signup")
	ResponseEntity<?> userSignUp (@RequestBody @Valid UserSignUpDTO addUser)
	{
		return userService.addNewUser(addUser);
	}
	
	@PostMapping("/login")
	ResponseEntity<?> userLogIn (@RequestBody @Valid UserSignUpDTO userLogIn)
	{
		return userService.userLogIn(userLogIn);
		
	}
	
	@GetMapping("/allUser")
	List<User> allUser()
	{
		return userService.allUserList();
	}
}
