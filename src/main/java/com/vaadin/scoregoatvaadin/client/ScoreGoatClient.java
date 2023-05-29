package com.vaadin.scoregoatvaadin.client;

import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.scoregoatvaadin.config.ScoreGoatApiConfig;
import com.vaadin.scoregoatvaadin.domain.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.*;

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

    private URI createUriForLogIn(UserParamDto userParamDto) {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint() +
                         "/login")
                .queryParam("name", userParamDto.getName())
                .queryParam("password", userParamDto.getPassword())
                .build().encode().toUri();
    }

    public UserRespondDto logIn(UserParamDto userParamDto){
        try {
            return restTemplate.getForObject(createUriForLogIn(userParamDto), UserRespondDto.class);
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

    public boolean changePassword(PasswordDto passwordDto){
        try {
            restTemplate.put(createUri() + "/users/passwordchange", passwordDto);
            return true;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    private URI createUriForAccountChange(AccountDto accountDto) {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint() +
                        "/users/accountchange")
                .queryParam("userId", accountDto.getUserId())
                .queryParam("userName", accountDto.getUserName())
                .queryParam("email", accountDto.getEmail())
                .queryParam("password", accountDto.getPassword())
                .build().encode().toUri();
    }

    private HttpEntity<AccountDto> passHeaders(AccountDto accountDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<AccountDto>(accountDto, headers);
    }

    public UserRespondDto changeAccountValues(AccountDto accountDto){
        try {
        ResponseEntity<UserRespondDto> response = restTemplate.exchange(createUriForAccountChange(accountDto),
                HttpMethod.PUT, passHeaders(accountDto), UserRespondDto.class);
            return response.getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new UserRespondDto(Messages.ACCOUNT_UPDATED_BAD.getMessage(), NotificationTypes.ERROR.getType());
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
            Match[] boardsRespond = restTemplate.getForObject(createUriForMatches(userId, leagueId), Match[].class);
            return Optional.ofNullable(boardsRespond)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
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
            return new NotificationRespond(Messages.SAVE_PREDICTIONS_BAD.getMessage(),
                    NotificationVariant.LUMO_ERROR.getVariantName());
        }
    }
    private URI createUriForUserPredictions(Long userId, int leagueId) {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getScoreGoatApiEndpoint() +
                        "/prediction")
                .queryParam("userId", userId)
                .queryParam("leagueId", leagueId)
                .build().encode().toUri();
    }
    public List<UserPredictionDto> getUserPredictions(Long userId, int leagueId) {
        try {
            UserPredictionDto[] boardsRespond = restTemplate.getForObject(
                    createUriForUserPredictions(userId, leagueId), UserPredictionDto[].class);
            return Optional.ofNullable(boardsRespond)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
