package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.taxiapp.desktop.net.controllers.StatisticsRequestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StatisticsService extends Service{
    public static double getRatio(){
        TypeReference<Double> typeReference = new TypeReference<>(){};
        return getValueFromResponse(StatisticsRequestController.getStatistics(),typeReference).orElse(0.0);
    }

    public static Map<String,Number> getPeriodStat() {
        TypeReference<Map<String, Number>> typeReference = new TypeReference<>() {
        };
        return getValueFromResponse(
            StatisticsRequestController.getPeriodStat(LocalDateTime.of(2024, 5, 11, 0, 0, 0),
                LocalDateTime.of(2024, 4, 11, 0, 0, 0)
                ), typeReference).orElse(new HashMap<>());
    }
}
