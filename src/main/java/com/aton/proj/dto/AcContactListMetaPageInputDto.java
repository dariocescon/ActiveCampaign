package com.aton.proj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AcContactListMetaPageInputDto(
        String segmentid,
        int formid,
        int listid,
        int tagid,
        int limit,
        int offset,
        String search,
        String sort,
        int seriesid,
        int waitid,
        int status,
        int forceQuery,
        String cacheid
) {
}
