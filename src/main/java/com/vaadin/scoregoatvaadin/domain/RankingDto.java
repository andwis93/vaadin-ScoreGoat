package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RankingDto {
    int rank;
    String userName;
    int points;
}
