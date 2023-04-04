package com.vaadin.scoregoatvaadin.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ScoreGoatApiConfig {
    @Value("${score-goat.api.endpoint.prod}")
    private String scoreGoatApiEndpoint;
}
