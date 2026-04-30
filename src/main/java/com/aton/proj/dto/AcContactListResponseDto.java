package com.aton.proj.dto;

import java.util.List;

/**
 * Risposta completa di AC per GET /api/3/contacts.
 */
public record AcContactListResponseDto(
        List<Object> scoreValues,
        List<AcContactDetailDto> contacts,
        AcContactListMetaDto meta
) {
}
