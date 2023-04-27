package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MatchPredictionDto {
    private Long id;
    private String whoWin;
    private Long userId;
    private Long matchId;
}
