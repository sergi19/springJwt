package com.mycompany.persistencia;

import com.mycompany.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
}
