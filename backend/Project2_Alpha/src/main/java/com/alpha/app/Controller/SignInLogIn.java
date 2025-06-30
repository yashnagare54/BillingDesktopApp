package com.alpha.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.app.DTO.UserSignUpDTO;
import com.alpha.app.Jwt_Utils.JwtUtil;
import com.alpha.app.Service.IUserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class SignInLogIn {

	@Autowired
	private IUserService userService;
	
//	@Autowired
//	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@PostMapping("/signup")
	ResponseEntity<?> registerNewUser(@RequestBody UserSignUpDTO user)
	{
		return userService.addNewUser(user);
	}
	
//	@PostMapping("/login")
//	ResponseEntity<?> logIn(@RequestBody @Valid AuthRequest request)//BindingResult br
//	{
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword());
//		try {
//			Authentication authentication = manager.authenticate(token);
//			UserSignUpDTO userInfo =userService.getUserDetailsByName(authentication.getName()); 
//			
//			String jwtTok = jwtUtils.generateToken(userInfo.getUserName());
//			return ResponseEntity.ok(new AuthResponse("Auth successful!!!", jwtTok, userInfo));
//		} catch (BadCredentialsException e) {
//			// TODO: handle exception
//			return new ResponseEntity<>("Incorrect UserName or Password", HttpStatus.BAD_REQUEST);
//		}
//	}
	
}
