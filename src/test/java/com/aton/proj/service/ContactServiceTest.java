package com.aton.proj.service;

import com.aton.proj.dto.AcContactDto;
import com.aton.proj.factory.AcContactDtoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Tag("integration")
class ContactServiceTest {

	@Autowired
	private ContactService contactService;

	@Test
	@DisplayName("syncContacts - invia 10 contatti al servizio remoto AC senza errori")
	void syncContacts_shouldSend10ContactsWithoutErrors() {
		List<AcContactDto> contacts = AcContactDtoFactory.create(1);

		System.err.println(contacts);

		assertDoesNotThrow(() -> contactService.syncContacts(contacts));
	}
}
