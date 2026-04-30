package com.aton.proj.dto;

import java.util.List;

/**
 * Risposta completa di AC per GET /api/3/contacts/{id}.
 */
public record AcContactResponseDto(
        List<Object> contactAutomations,
        List<AcContactDataDto> contactData,
        List<AcContactListItemDto> contactLists,
        List<Object> deals,
        List<Object> fieldValues,
        List<Object> geoIps,
        List<Object> accountContacts,
        AcContactDetailDto contact
) {
}
