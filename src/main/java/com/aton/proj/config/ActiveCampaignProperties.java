package com.aton.proj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "activecampaign.api")
public record ActiveCampaignProperties(String url, String key) {
}
