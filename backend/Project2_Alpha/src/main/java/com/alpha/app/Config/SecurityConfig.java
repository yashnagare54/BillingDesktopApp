//package com.alpha.app.Config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import com.alpha.app.Filter.JWTRequestFilter;
//import com.alpha.app.Special_Service.UserDetailsServiceImpl;
//
////4th step for Spring security and JWT token
//@Component
//public class SecurityConfig {
//
//	@Autowired
//	private UserDetailsServiceImpl userDetailsService;
//	
//	@Autowired
//	private PasswordEncoder encoder;
//	
//	@Autowired
//	private JWTRequestFilter jwtFilter;
//	
//	//Must be protected
//		@Bean
//		  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////		    http.csrf(csrf -> csrf.disable())
////		        .exceptionHandling(exception -> exception.authenticationEntryPoint(null))
////		        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////		        .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
////		        .requestMatchers("/api/test/**").permitAll()
////		        .anyRequest().authenticated());
//			
//			http.authorizeHttpRequests()
////		    .requestMatchers("/api/user**").authenticated()
////		    .requestMatchers("/api/admin**").hasRole("ADMIN")
////		    .requestMatchers("/api/auth/**").permitAll()
//		    .anyRequest().permitAll()
//		    .and();
//
//			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
//			http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//			
//			//http.csrf().disable();
//			
//			//From website to OLD to NEW
//			http.authenticationProvider(authenticationProvider());
//		    // http....;
//		    
//		    return http.build();
//		  }
//		
//		@Bean
//		  public DaoAuthenticationProvider authenticationProvider() {
//		      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		       
//		      authProvider.setUserDetailsService(userDetailsService);
//		      authProvider.setPasswordEncoder(encoder);
//		   
//		      return authProvider;
//		  }
//
//		  
//		// New Change
//		@Bean
//		public AuthenticationManager authenticatonMgr(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//			return authenticationConfiguration.getAuthenticationManager();
//		}
//}
