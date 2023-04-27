package com.vaadin.scoregoatvaadin.client;

import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.scoregoatvaadin.config.ScoreGoatApiConfig;
import com.vaadin.scoregoatvaadin.domain.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScoreGoatClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreGoatClient.class);
    private final RestTemplate restTemplate;
    private final ScoreGoatApiConfig apiConfig;

    private URI createUri() {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint())
                .build().encode().toUri();
    }

    public UserRespondDto logIn(UserParamDto userparam){
        try {
            return restTemplate.postForObject(createUri() + "/login", userparam, UserRespondDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public UserRespondDto createUser(UserDto userDto){
        try {
            return restTemplate.postForObject(createUri() + "/users", userDto, UserRespondDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public UserRespondDto changePassword(PasswordDto passwordDto){
        try {
            return restTemplate.postForObject(createUri() + "/users/passwordchange", passwordDto, UserRespondDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public UserRespondDto changeAccountValues(AccountDto accountDto){
        try {
            return restTemplate.postForObject(createUri() + "/users/accountchange", accountDto, UserRespondDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private URI createUriForMatches(Long userId, int leagueId) {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint() +
                        "/match")
                .queryParam("userId", userId)
                .queryParam("leagueId", leagueId)
                .build().encode().toUri();
    }

    public List<Match> getMatchesByLeagueId(long userId, int leagueId){
        try {
            return Arrays.stream(Objects.requireNonNull(
                    restTemplate.postForObject(createUriForMatches(userId, leagueId), null, Match[].class))).collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return List.of();
        }
    }

    public NotificationRespond saveUserPredictions(PredictionDto predictionDto){
        try {
          return restTemplate.postForObject(createUri() + "/prediction", predictionDto, NotificationRespond.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new NotificationRespond("Something Went Wrong - couldn't save predictions",
                    NotificationVariant.LUMO_ERROR.getVariantName());
        }
    }
}
