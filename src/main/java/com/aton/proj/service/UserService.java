package com.aton.proj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aton.proj.dto.AcUserDto;
import com.aton.proj.dto.AcUserResponseDto;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private TokenService tokenService;

	/**
	 * Recupera un utente AC dato il suo indirizzo email. Chiama GET
	 * /users/email/{emailAddress}.
	 *
	 * @param email indirizzo email dell'utente
	 * @return dati dell'utente
	 * @throws org.springframework.web.client.HttpClientErrorException.NotFound se
	 *                                                                          l'utente
	 *                                                                          non
	 *                                                                          esiste
	 */
	public AcUserDto getUserByEmail(String email) {
		log.info("Fetching AC user by email: {}", email);

		AcUserResponseDto response = tokenService.getClient().get().uri("/users/email/{email}", email).retrieve()
				.body(AcUserResponseDto.class);

		if (response == null || response.user() == null) {
			log.warn("Empty response for email: {}", email);
			throw new IllegalStateException("Empty response from AC for email: " + email);
		}

		log.debug("AC user found: id={}, username={}", response.user().id(), response.user().username());
		return response.user();
	}

}
