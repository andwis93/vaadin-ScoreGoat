package com.vaadin.scoregoatvaadin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPredictionDto {
    private String homeLogo;
    private String homeTeam;
    private int homeGoal;
    private String date;
    private String time;
    private int awayGoal;
    private String awayTeam;
    private String awayLogo;
    private String prediction;
    private int points;
    private int result = -1;
}
