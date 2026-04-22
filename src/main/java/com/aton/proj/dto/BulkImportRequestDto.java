package com.aton.proj.dto;

import java.util.List;

public record BulkImportRequestDto(List<AcContactDto> contacts) {
}
