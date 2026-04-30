package com.aton.proj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AcUserDto(
        String id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone,
        String signature,
        String lang,
        @JsonProperty("localZoneid") String localZoneid,
        String mfaEnabled,
        List<String> roles,
        AcUserLinksDto links
) {
}
