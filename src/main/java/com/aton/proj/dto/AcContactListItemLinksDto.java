package com.aton.proj.dto;

public record AcContactListItemLinksDto(
        String automation,
        String list,
        String contact,
        String form,
        String autosyncLog,
        String campaign,
        String unsubscribeAutomation,
        String message
) {
}
