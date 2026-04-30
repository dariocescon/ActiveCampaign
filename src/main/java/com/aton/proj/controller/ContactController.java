package com.aton.proj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.aton.proj.dto.AcContactDetailDto;
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
     * Elimina un contatto AC dato il suo id.
     *
     * DELETE /contacts/10
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable String id) {
        try {
            contactService.deleteContactById(id);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("AC contact not found for id: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error deleting AC contact for id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Restituisce i dati di un singolo contatto AC dato il suo id.
     *
     * GET /contacts/2
     */
    @GetMapping("/{id}")
    public ResponseEntity<AcContactDetailDto> getContactById(@PathVariable String id) {
        try {
            AcContactDetailDto contact = contactService.getContactById(id);
            return ResponseEntity.ok(contact);
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("AC contact not found for id: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching AC contact for id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
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
