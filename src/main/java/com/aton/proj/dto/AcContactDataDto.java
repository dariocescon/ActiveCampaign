package com.aton.proj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AcContactDataDto(
        String id,
        String contact,
        String tstamp,
        String geoTstamp,
        String geoIp4,
        String geoCountry2,
        @JsonProperty("geo_country")           String geoCountry,
        String geoState,
        String geoCity,
        String geoZip,
        String geoArea,
        String geoLat,
        String geoLon,
        String geoTz,
        String geoTzOffset,
        @JsonProperty("ga_campaign_source")        String gaCampaignSource,
        @JsonProperty("ga_campaign_name")          String gaCampaignName,
        @JsonProperty("ga_campaign_medium")        String gaCampaignMedium,
        @JsonProperty("ga_campaign_term")          String gaCampaignTerm,
        @JsonProperty("ga_campaign_content")       String gaCampaignContent,
        @JsonProperty("ga_campaign_customsegment") String gaCampaignCustomsegment,
        @JsonProperty("ga_first_visit")            String gaFirstVisit,
        @JsonProperty("ga_times_visited")          String gaTimesVisited,
        @JsonProperty("fb_id")   String fbId,
        @JsonProperty("fb_name") String fbName,
        @JsonProperty("tw_id")   String twId,
        @JsonProperty("created_timestamp") String createdTimestamp,
        @JsonProperty("updated_timestamp") String updatedTimestamp,
        @JsonProperty("created_by") String createdBy,
        @JsonProperty("updated_by") String updatedBy,
        List<Object> links
) {
}
