package com.alpha.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project2AlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2AlphaApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	//If not write below bean below error get
	//Field passwordEncoder in com.alpha.app.Service.UserServiceImpl required a bean of type 'org.springframework.security.crypto.password.PasswordEncoder' that could not be found.
//	@Bean 
//	  public PasswordEncoder encoder() { 
//		  return new BCryptPasswordEncoder();
//	  }
	
	
	
}
