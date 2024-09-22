package com.anodos.msusuarios.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anodos.msusuarios.entities.User;
import com.anodos.msusuarios.entities.dto.LoginDTO;
import com.anodos.msusuarios.entities.dto.UserDTO;
import com.anodos.msusuarios.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping()
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@PostMapping()
	public ResponseEntity<String> save(@RequestBody UserDTO user) {

		if (userService.saveByUser(user).getId() == null) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao efetuar o cadastro, por favor verificar os dados informados");
		}

		return ResponseEntity.ok("Usuario cadastrado com sucesso");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO login) {

		if (userService.validaLogon(login)) {
			return ResponseEntity.ok("Usuario cadastrado com sucesso");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}

	}

	@GetMapping(value = "/search")
	public ResponseEntity<User> findByUsername(@RequestParam String username) {

		User u = userService.findByUsername(username);

		if (u.equals(new User())) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não localizado!");
		}

		return ResponseEntity.ok(u);
	}

}
