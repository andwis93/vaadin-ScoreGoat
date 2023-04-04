package com.vaadin.scoregoatvaadin.client;

import com.vaadin.scoregoatvaadin.config.ScoreGoatApiConfig;
import com.vaadin.scoregoatvaadin.domain.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScoreGoatClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreGoatClient.class);
    private final RestTemplate restTemplate;
    private final ScoreGoatApiConfig apiConfig;

    private URI createUriForCreateUser() {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint() +
                        "/users")
                .build().encode().toUri();
    }

    public UserRespondDto createUser(UserDto userDto){
        try {
            return restTemplate.postForObject(createUriForCreateUser(), userDto, UserRespondDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private URI createUriForLogIn() {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint() +
                        "/login")
                .build().encode().toUri();
    }
    public UserRespondDto logIn(UserParamDto userparam){
        try {
            return restTemplate.postForObject(createUriForLogIn(), userparam, UserRespondDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private URI createUriForPasswordChange() {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint() +
                        "/users/passwordchange")
                .build().encode().toUri();
    }

    public UserRespondDto changePassword(PasswordDto passwordDto){
        try {
            return restTemplate.postForObject(createUriForPasswordChange(), passwordDto, UserRespondDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private URI createUriForMatches(int leagueId) {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint() +
                        "/match")
                .queryParam("id", leagueId)
                .build().encode().toUri();
    }

    public List<Match> getMatchesByLeagueId(int leagueId){
        try {
            return Arrays.stream(Objects.requireNonNull(
                    restTemplate.getForObject(createUriForMatches(leagueId), Match[].class))).collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return List.of();
        }
    }
}
