package com.aton.proj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.aton.proj.dto.AcUserDto;
import com.aton.proj.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Restituisce i dati di un utente AC dato il suo indirizzo email.
	 *
	 * GET /users/user?email=mario.rossi@example.com
	 */
	@GetMapping("/user")
	public ResponseEntity<AcUserDto> getUserByEmail(@RequestParam String email) {
		try {
			AcUserDto user = userService.getUserByEmail(email);
			return ResponseEntity.ok(user);
		} catch (HttpClientErrorException.NotFound e) {
			log.warn("AC user not found for email: {}", email);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error fetching AC user for email {}: {}", email, e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
}
