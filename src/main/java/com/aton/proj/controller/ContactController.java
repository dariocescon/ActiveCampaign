package com.aton.proj.controller;

import com.aton.proj.dto.AcUserDto;
import com.aton.proj.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * Restituisce i dati di un utente AC dato il suo indirizzo email.
     *
     * GET /contacts/user?email=mario.rossi@example.com
     */
    @GetMapping("/user")
    public ResponseEntity<AcUserDto> getUserByEmail(@RequestParam String email) {
        try {
            AcUserDto user = contactService.getUserByEmail(email);
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
