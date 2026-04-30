package com.aton.proj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AcContactDetailDto(
        String id,
        String email,
        String phone,
        String firstName,
        String lastName,
        String orgid,
        String orgname,
        String organization,

        // Timestamps
        String cdate,
        String adate,
        String udate,
        String edate,
        @JsonProperty("deleted_at")           String deletedAt,
        @JsonProperty("created_utc_timestamp") String createdUtcTimestamp,
        @JsonProperty("updated_utc_timestamp") String updatedUtcTimestamp,
        @JsonProperty("created_timestamp")     String createdTimestamp,
        @JsonProperty("updated_timestamp")     String updatedTimestamp,
        @JsonProperty("created_by")            String createdBy,
        @JsonProperty("updated_by")            String updatedBy,
        @JsonProperty("rating_tstamp")         String ratingTstamp,
        @JsonProperty("socialdata_lastcheck")  String socialdataLastcheck,
        @JsonProperty("last_click_date")       String lastClickDate,
        @JsonProperty("last_open_date")        String lastOpenDate,
        @JsonProperty("last_mpp_open_date")    String lastMppOpenDate,

        // Email info
        @JsonProperty("email_local")  String emailLocal,
        @JsonProperty("email_domain") String emailDomain,
        String hash,

        // Bounce
        @JsonProperty("bounced_hard") String bouncedHard,
        @JsonProperty("bounced_soft") String bouncedSoft,
        @JsonProperty("bounced_date") String bouncedDate,

        // Flags
        String deleted,
        String anonymized,
        String gravatar,
        String sentcnt,
        @JsonProperty("segmentio_id")  String segmentioId,
        String ip,
        String ua,

        // Tracking
        @JsonProperty("mpp_tracking")    String mppTracking,
        @JsonProperty("best_send_hour")  String bestSendHour,

        // SMS / WhatsApp
        @JsonProperty("sms_consent")            String smsConsent,
        @JsonProperty("sms_consent_updated_at") String smsConsentUpdatedAt,
        @JsonProperty("whatsapp_id")             String whatsappId,
        @JsonProperty("whatsapp_username")       String whatsappUsername,

        // Relations
        List<Object> scoreValues,
        List<Object> accountContacts,
        AcContactLinksDto links
) {
}
