package com.taxiapp.desktop.net.controllers;

import com.taxiapp.desktop.net.ApiEndpoints;
import java.net.http.HttpResponse;

public class CarRequestController extends RequestController{
    public static HttpResponse<String> createNewCar(String newCarAsJson){
        return postRequest(ApiEndpoints.NEW_CAR_URL,newCarAsJson);
    }

    public static HttpResponse<String> getAllCars(){
        return getRequest(ApiEndpoints.ALL_CARS_URL,null);
    }
}
