package com.alpha.app.Service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.app.DTO.UserSignUpDTO;
import com.alpha.app.Entity.User;
import com.alpha.app.Repositiory.UserRepositiory;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepositiory userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;	

	@Override
	public ResponseEntity<?> addNewUser(UserSignUpDTO addUser) {
		
		if(userRepo.findByUserEmail(addUser.getUserEmail()).isPresent())
		{
			return new ResponseEntity<>("User with "+addUser.getUserEmail()+" already present.", HttpStatus.CONFLICT);
		}
		
		User userObj = mapper.map(addUser, User.class);
		LocalDate currentDt = LocalDate.now();
		userObj.setCreatedOn(currentDt);
//		userObj.setUserPassword(passwordEncoder.encode(userObj.getUserPassword()));
		User newUser = userRepo.save(userObj);
		if(newUser ==null)
		{
			return new ResponseEntity<>("User Sign up Failed. Try again", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("User Sign up successfully", HttpStatus.CREATED);
		
		
	}

	@Override
	public ResponseEntity<?> userLogIn(UserSignUpDTO userLogIn) {
		
		User userObj = userRepo.findByUserEmail(userLogIn.getUserEmail()).orElse(null);
		
		if(userObj !=null)
		{
			if(userLogIn.getUserPassword().equals(userObj.getUserPassword()))
			{
				return new ResponseEntity<>(userObj.getUserId(),HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>("Password is incorrect",HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("User Not Exist! Please Sign in first",HttpStatus.BAD_REQUEST);
		
	}

	@Override
	public List<User> allUserList() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public UserSignUpDTO getUserDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUserEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
		return mapper.map(user, UserSignUpDTO.class);
	}
	
	
}
