# Skill: SpringSchedulerGenerator

## Istruzioni
Genera scheduler con gestione delle esecuzioni sovrapposte.

## Requisiti
- Utilizza i parametri del application.properties
- Annota i singoli metodi
- Sfrutta la sovrapposizione tra esecuzioni utilizzando oggetti atomici
- Verifica sempre che la schedulazione sia attivata da parametro
- Dividi schedulazione da codice che viene schedulato: devono essere classi in file differenti
- Logga
- I metodi devono essere testabili
- Gestione degli errori

## Esempio input
"crea schedulazione per sincronizzare i Worklog"

## Output atteso
package com.aton.proj.scheduler;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aton.proj.service.WorklogService;

@Component
public class WorklogSyncScheduler {

	private static final Logger log = LoggerFactory.getLogger(WorklogSyncScheduler.class);

	@Autowired
	private WorklogService worklogService;

	@Value("${app.scheduler.worklog-sync.cron.active:true}")
	private boolean syncSchedulerEnabled;

	@Value("${app.scheduler.worklog-update.cron.active:true}")
	private boolean updateSchedulerEnabled;

	@Value("${app.worklog-update.days.in.the.past:2}")
	private int daysInThePast;
	
	private final AtomicBoolean isSyncing = new AtomicBoolean(false);
	private final AtomicBoolean isUpdating = new AtomicBoolean(false);

	@Scheduled(cron = "${app.scheduler.worklog-sync.cron}", zone = "${app.scheduler.timezone}")
	public void syncWorklogs() {
		if (syncSchedulerEnabled) {
			if (!isSyncing.compareAndSet(false, true)) {
				log.warn("Previous worklog sync is still running, skipping this execution");
				return;
			}

			try {
				worklogService.syncWorklogs();
			} catch (Exception e) {
				log.error("Unexpected error during worklog sync: {}", e.getMessage(), e);
			} finally {
				isSyncing.set(false);
			}
		}
	}

	@Scheduled(cron = "${app.scheduler.worklog-update.cron}", zone = "${app.scheduler.timezone}")
	public void updateWorklogsWithoutWorkorder() {
		if (updateSchedulerEnabled) {
			if (!isSyncing.get()) {
				if (!isUpdating.compareAndSet(false, true)) {
					log.warn("Previous worklog update is still running, skipping this execution");
					return;
				}

				try {
					worklogService.updateWorklogsNoWorkOrder(daysInThePast);
				} catch (Exception e) {
					log.error("Unexpected error during worklog update: {}", e.getMessage(), e);
				} finally {
					isUpdating.set(false);
				}
			}else {
				log.warn("Try to update worklog without workorder but Worklog synchronization is running.");
			}
		}
	}
}