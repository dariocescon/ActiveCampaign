package com.aton.proj.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.aton.proj.dto.AcContactDto;
import com.aton.proj.dto.AcContactListResponseDto;
import com.aton.proj.dto.BulkImportRequestDto;
import com.aton.proj.dto.ContactSyncRequestDto;

@Service
public class ContactService {

	private static final Logger log = LoggerFactory.getLogger(ContactService.class);

	// POST /contact/sync: max 5 req/sec → 250ms tra le chiamate
	private static final long SYNC_DELAY_MS = 250;

	// POST /import/bulk_import: max 100 req/min → 700ms tra i batch (margine di
	// sicurezza)
	private static final long BULK_DELAY_MS = 700;

	private static final int BULK_BATCH_SIZE = 250;

	@Autowired
	private TokenService tokenService;

	/**
	 * Sincronizza una lista di contatti su AC uno alla volta via /contact/sync
	 * (upsert per email). Rispetta il limite di 5 req/sec inserendo un delay tra le
	 * chiamate.
	 */
	public void syncContacts(List<AcContactDto> contacts) {
		if (contacts.isEmpty()) {
			log.info("No contacts to sync");
			return;
		}

		log.info("Starting sync of {} contacts via /contact/sync", contacts.size());
		int success = 0;
		int failed = 0;

		for (int i = 0; i < contacts.size(); i++) {
			AcContactDto contact = contacts.get(i);
			try {
				tokenService.getClient().post().uri("/contact/sync").contentType(MediaType.APPLICATION_JSON)
						.body(new ContactSyncRequestDto(contact)).retrieve().toBodilessEntity();
				success++;
				log.debug("Synced contact: {}", contact.email());
			} catch (Exception e) {
				failed++;
				log.error("Failed to sync contact {}: {}", contact.email(), e.getMessage(), e);
			}

			if (i < contacts.size() - 1) {
				sleep(SYNC_DELAY_MS, "sync");
			}
		}

		log.info("Sync completed: {} success, {} failed out of {}", success, failed, contacts.size());
	}

	/**
	 * Importa una lista di contatti su AC in batch da 250 via /import/bulk_import.
	 * Rispetta il limite di 100 req/min inserendo un delay tra i batch.
	 */
	public void bulkImportContacts(List<AcContactDto> contacts) {
		if (contacts.isEmpty()) {
			log.info("No contacts to bulk import");
			return;
		}

		List<List<AcContactDto>> batches = partition(contacts, BULK_BATCH_SIZE);
		log.info("Starting bulk import of {} contacts in {} batch(es)", contacts.size(), batches.size());

		for (int i = 0; i < batches.size(); i++) {
			List<AcContactDto> batch = batches.get(i);
			int batchNum = i + 1;
			try {
				tokenService.getClient().post().uri("/import/bulk_import").contentType(MediaType.APPLICATION_JSON)
						.body(new BulkImportRequestDto(batch)).retrieve().toBodilessEntity();
				log.info("Batch {}/{} imported: {} contacts", batchNum, batches.size(), batch.size());
			} catch (Exception e) {
				log.error("Failed to import batch {}/{}: {}", batchNum, batches.size(), e.getMessage(), e);
			}

			if (i < batches.size() - 1) {
				sleep(BULK_DELAY_MS, "bulk import");
			}
		}

		log.info("Bulk import finished: {}/{} batches processed", batches.size(), batches.size());
	}

	private void sleep(long millis, String context) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			log.warn("Thread interrupted during {}", context);
		}
	}

	/**
	 * Recupera la lista dei contatti AC tramite GET /contacts.
	 *
	 * @return risposta completa di AC con lista contatti e metadati di paginazione
	 */
	public AcContactListResponseDto getContacts() {
		log.info("Fetching contact list from AC");

		AcContactListResponseDto response = tokenService.getClient().get().uri("/contacts").retrieve()
				.body(AcContactListResponseDto.class);

		if (response == null) {
			log.warn("Empty response from AC for GET /contacts");
			throw new IllegalStateException("Empty response from AC for GET /contacts");
		}

		int count = response.contacts() != null ? response.contacts().size() : 0;
		log.debug("AC contacts retrieved: {}", count);
		return response;
	}

	private static <T> List<List<T>> partition(List<T> list, int size) {
		List<List<T>> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i += size) {
			result.add(list.subList(i, Math.min(i + size, list.size())));
		}
		return result;
	}
}
