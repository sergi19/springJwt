package com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.dto.ResponseDTO;
import com.mycompany.dto.UserCredentialsDTO;
import com.mycompany.impl.UserSecurityService;
import com.mycompany.security.TokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value="/login")
	public ResponseDTO signIn(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword())
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userSecurityService.loadUserByUsername(userCredentialsDTO.getUsername());
		String token = tokenUtil.generateToken(userDetails);
		ResponseDTO responseDTO = new ResponseDTO(token);
		return responseDTO;
	}
	
	
}
