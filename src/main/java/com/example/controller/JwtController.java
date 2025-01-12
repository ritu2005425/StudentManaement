package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.helper.JwtUtil;
import com.example.jwt.model.JwtRequest;
import com.example.jwt.model.JwtResponse;
import com.example.service.CustomUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//is used to authenticate 
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value="/token",method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
     System.out.println(jwtRequest);
     try
     {
    	 this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
     }
     catch(UsernameNotFoundException e)
     {
    	e.printStackTrace();
    	throw new Exception("bad credentials");
     }
     catch(BadCredentialsException e) {
    	 e.printStackTrace();
     	throw new Exception("bad credentials");
     }
     //fine area
     UserDetails userdetails=customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
     String token=this.jwtUtil.generateToken(userdetails);
     System.out.println("JWT "+token);
	
     return ResponseEntity.ok(new JwtResponse(token));
		
	}

}
