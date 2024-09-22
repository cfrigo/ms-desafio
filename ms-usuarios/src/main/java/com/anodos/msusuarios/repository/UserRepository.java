package com.anodos.msusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anodos.msusuarios.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
