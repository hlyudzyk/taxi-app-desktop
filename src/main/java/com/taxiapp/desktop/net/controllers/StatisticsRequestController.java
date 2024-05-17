package com.taxiapp.desktop.net.controllers;

import static com.taxiapp.desktop.net.ApiEndpoints.STATISTICS_PERIOD_URL;
import static com.taxiapp.desktop.net.ApiEndpoints.STATISTICS_RATIO_URL;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class StatisticsRequestController extends RequestController{
    public static HttpResponse<String> getStatistics(){
        return getRequest(STATISTICS_RATIO_URL,null);
    }

    public static HttpResponse<String> getPeriodStat(LocalDateTime before, LocalDateTime after){
        Map<String, String> params = new HashMap<>();

        params.put("before",before.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        params.put("after",after.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return getRequest(STATISTICS_PERIOD_URL,params);
    }
}
