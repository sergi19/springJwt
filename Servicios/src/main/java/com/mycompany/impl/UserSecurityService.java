package com.mycompany.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.dto.ResponseDTO;
import com.mycompany.modelo.User;
import com.mycompany.persistencia.UserRepository;
import com.mycompany.security.modelo.UserPrincipal;

import javassist.NotFoundException;

@Service
public class UserSecurityService implements UserDetailsService{

	@Autowired
    public UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if (user == null) {
			System.err.println("Usuario no encontrado!!");
		} 
		UserPrincipal userPrincipal = new UserPrincipal(user);
		System.out.println("este es el user: " + user.getUsername());
		System.out.println("este es el user principal: " + userPrincipal.getUsername());
		System.out.println("este es el password de user: " + user.getPassword());
		System.out.println("este es el password de user principal: " + userPrincipal.getPassword());
		return userPrincipal;
	}

}
