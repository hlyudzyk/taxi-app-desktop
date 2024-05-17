package com.taxiapp.desktop.net.controllers;

import static com.taxiapp.desktop.net.ApiEndpoints.ALL_DRIVERS_URL;
import static com.taxiapp.desktop.net.ApiEndpoints.DRIVER_URL;

import java.net.http.HttpResponse;
import java.util.Collections;

public class DriverRequestController extends RequestController {
    public static HttpResponse<String> getAllDriversAsJson(){
        return getRequest(ALL_DRIVERS_URL, Collections.emptyMap());
    }

    public static HttpResponse<String> getDriverById(String id){
        return getEntityById(DRIVER_URL,id);
    }
}
