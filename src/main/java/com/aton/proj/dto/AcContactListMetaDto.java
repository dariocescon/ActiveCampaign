package com.aton.proj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AcContactListMetaDto(
        @JsonProperty("page_input") AcContactListMetaPageInputDto pageInput,
        String total,
        boolean sortable
) {
}
