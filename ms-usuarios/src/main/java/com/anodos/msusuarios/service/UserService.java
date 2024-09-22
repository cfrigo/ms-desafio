package com.anodos.msusuarios.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anodos.msusuarios.entities.User;
import com.anodos.msusuarios.entities.dto.LoginDTO;
import com.anodos.msusuarios.entities.dto.UserDTO;
import com.anodos.msusuarios.repository.UserRepository;

@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<User> findAll() {
		List<User> list = new ArrayList<>();

		try {
			list = userRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return list;
		}

		return list;
	}

	public User saveByUser(UserDTO user) {

		User u = new User();
		try {
			u.setUsername(user.getUsername());
			u.setPassword(passwordEncoder.encode(user.getPassword()));
			u.setRoles(user.getRoles());
			return userRepository.save(u);
		} catch (Exception e) {
			logger.error("Erro ao efetuar o cadastro: " + e.getMessage());
			return new User();
		}
	}

	public User findByUsername(String username) {
		User user = new User();
		try {
			user = userRepository.findByUsername(username);
		} catch (Exception e) {
			logger.error("Erro ao consultar username: " + e.getMessage());
			return user;
		}

		return user;
	}

	public boolean validaLogon(LoginDTO login) {
		boolean isMatch = false;
		User user = new User();
		try {
			user = userRepository.findByUsername(login.getUsername().toString());

			isMatch = passwordEncoder.matches(login.getPassword(), user.getPassword());

		} catch (Exception e) {
			logger.error("Erro ao validar login: " + e.getMessage());
			return false;
		}

		return isMatch;
	}

}
