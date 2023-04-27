package com.vaadin.scoregoatvaadin.service;

import com.vaadin.scoregoatvaadin.domain.Match;
import com.vaadin.scoregoatvaadin.domain.PredictionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Service
public class MatchFilterService {

    public List<Match> notPlayed(List<Match> matches) {
        return matches.stream().filter(match -> match.getStatus().equals("Not Started")).toList();
    }
    public PredictionDto onlySelectedMatches(PredictionDto predictionDto) {
        Map<Long, String> filteredList = new HashMap<>();
        predictionDto.getMatchSelections().entrySet().stream().filter(match -> !match.getValue().isEmpty()).forEach(
                entry -> filteredList.put(entry.getKey(), entry.getValue()));
          return new PredictionDto(predictionDto.getUserId(), filteredList);
    }
}
