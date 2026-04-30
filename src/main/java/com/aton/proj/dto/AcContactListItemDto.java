package com.aton.proj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AcContactListItemDto(
        String id,
        String contact,
        String list,
        String form,
        String seriesid,
        String sdate,
        String udate,
        String status,
        String responder,
        String sync,
        String unsubreason,
        String campaign,
        String message,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name")  String lastName,
        String ip4Sub,
        String sourceid,
        String autosyncLog,
        @JsonProperty("ip4_last")  String ip4Last,
        String ip4Unsub,
        @JsonProperty("created_timestamp") String createdTimestamp,
        @JsonProperty("updated_timestamp") String updatedTimestamp,
        @JsonProperty("created_by") String createdBy,
        @JsonProperty("updated_by") String updatedBy,
        String unsubscribeAutomation,
        String channel,
        String automation,
        AcContactListItemLinksDto links
) {
}
