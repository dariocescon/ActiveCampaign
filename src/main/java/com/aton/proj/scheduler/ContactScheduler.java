package com.aton.proj.scheduler;

import com.aton.proj.dto.AcContactDto;
import com.aton.proj.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ContactScheduler {

    private static final Logger log = LoggerFactory.getLogger(ContactScheduler.class);

    private final ContactService contactService;

    @Value("${app.scheduler.contact-sync.cron.active:true}")
    private boolean syncEnabled;

    @Value("${app.scheduler.contact-bulk-import.cron.active:false}")
    private boolean bulkImportEnabled;

    private final AtomicBoolean isSyncing = new AtomicBoolean(false);
    private final AtomicBoolean isBulkImporting = new AtomicBoolean(false);

    public ContactScheduler(ContactService contactService) {
        this.contactService = contactService;
    }

    @Scheduled(cron = "${app.scheduler.contact-sync.cron}", zone = "${app.scheduler.timezone}")
    public void scheduleContactSync() {
        if (!syncEnabled) {
            return;
        }

        if (!isSyncing.compareAndSet(false, true)) {
            log.warn("Previous contact sync is still running, skipping this execution");
            return;
        }

        try {
            List<AcContactDto> contacts = fetchContactsForSync(); // TODO: sostituire con dati da DB locale
            contactService.syncContacts(contacts);
        } catch (Exception e) {
            log.error("Unexpected error during contact sync: {}", e.getMessage(), e);
        } finally {
            isSyncing.set(false);
        }
    }

    @Scheduled(cron = "${app.scheduler.contact-bulk-import.cron}", zone = "${app.scheduler.timezone}")
    public void scheduleContactBulkImport() {
        if (!bulkImportEnabled) {
            return;
        }

        if (!isBulkImporting.compareAndSet(false, true)) {
            log.warn("Previous contact bulk import is still running, skipping this execution");
            return;
        }

        try {
            List<AcContactDto> contacts = fetchContactsForBulkImport(); // TODO: sostituire con dati da DB locale
            contactService.bulkImportContacts(contacts);
        } catch (Exception e) {
            log.error("Unexpected error during contact bulk import: {}", e.getMessage(), e);
        } finally {
            isBulkImporting.set(false);
        }
    }

    private List<AcContactDto> fetchContactsForSync() {
        // TODO: iniettare il repository del DB locale e restituire i contatti da sincronizzare
        return Collections.emptyList();
    }

    private List<AcContactDto> fetchContactsForBulkImport() {
        // TODO: iniettare il repository del DB locale e restituire i contatti da importare
        return Collections.emptyList();
    }
}
