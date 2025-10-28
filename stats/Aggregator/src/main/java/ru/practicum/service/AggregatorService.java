package ru.practicum.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.stats.avro.EventSimilarityAvro;
import ru.practicum.ewm.stats.avro.UserActionAvro;

import java.util.*;

@Service
public class AggregatorService {
    private final Double VIEW_WEIGHT = 0.4;
    private final Double REGISTER_WEIGHT = 0.8;
    private final Double LIKE_WEIGHT = 1.0;
    //Map<EventID, Map < UserID, WEIGHT>>
    Map<Long, Map<Long, Double>> actionWeightMap = new HashMap<>();
    //Map<EventID1, Map < EventId2, sim(EventId1, EventId2)>>
    Map<Long, Map<Long, Double>> minWeightSum = new HashMap<>();


    public List<EventSimilarityAvro> aggregate(UserActionAvro action) {
        List<EventSimilarityAvro> answer = new ArrayList<>();
        Map<Long, Double> usersWeight = actionWeightMap.computeIfAbsent(
                action.getEventId(),
                k -> new HashMap<>()
        );
        double actionWeight = 0.0;
        switch (action.getActionType()) {
            case VIEW -> actionWeight = VIEW_WEIGHT;
            case REGISTER -> actionWeight = REGISTER_WEIGHT;
            case LIKE -> actionWeight = LIKE_WEIGHT;
        }
        Double currentWeight = usersWeight.getOrDefault(action.getUserId(), 0.0);

        if (actionWeight <= currentWeight) {
            return answer;
        }
        usersWeight.put(action.getUserId(), actionWeight);
        Map<Long, Double> eventShips = minWeightSum.get(action.getEventId());

    }

    private Double sin(Long event1, Long event2) {

    }
}



