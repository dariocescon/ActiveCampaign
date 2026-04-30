package com.aton.proj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aton.proj.dto.AcContactListResponseDto;
import com.aton.proj.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * Restituisce la lista dei contatti presenti su AC.
     *
     * GET /contacts
     */
    @GetMapping
    public ResponseEntity<AcContactListResponseDto> getContacts() {
        try {
            AcContactListResponseDto response = contactService.getContacts();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching contact list from AC: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
