package com.aton.proj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.aton.proj.config.ActiveCampaignProperties;

@Service
public class TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenService.class);
    private static final String API_TOKEN_HEADER = "Api-Token";

    private final RestClient restClient;

    public TokenService(ActiveCampaignProperties properties) {
        log.info("Initializing ActiveCampaign client with base URL: {}", properties.url());
        this.restClient = RestClient.builder()
                .baseUrl(properties.url())
                .defaultHeader(API_TOKEN_HEADER, properties.key())
                .build();
    }

    public RestClient getClient() {
        return restClient;
    }
}
