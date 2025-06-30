//package com.alpha.app.Special_Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.alpha.app.Entity.User;
//import com.alpha.app.Repositiory.UserRepositiory;
//import com.fasterxml.jackson.core.ErrorReportConfiguration.Builder;
//
////2nd step for Spring security and JWT token
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	// For user details required user repo 
//	@Autowired
//	private UserRepositiory userRepo;
//	
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// Here unique is username , if we have email is unique property then we used that property
//		
//		User user =userRepo.findByUserName(username).get();
//		
//		if(user != null)
//		{
//			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//					.username(user.getUserName())
//					.password(user.getUserPassword())
////					.roles(user.getUserRole().toString()) //role assign 
//					.build();
//			return userDetails;
//		}
//		
//		throw new UsernameNotFoundException("User not found with email: "+username);
//		
//		
//	}
//
//}
