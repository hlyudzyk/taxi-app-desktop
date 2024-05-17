package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.taxiapp.desktop.net.controllers.DriverRequestController;
import com.taxiapp.desktop.net.responses.DriverDataResponse;
import java.util.Collections;
import java.util.List;

public class DriverService extends Service{

    public static List<DriverDataResponse> getDrivers(){
        TypeReference<List<DriverDataResponse>> typeReference = new TypeReference<List<DriverDataResponse>>() {};
        return getValueFromResponse(DriverRequestController.getAllDriversAsJson(),
            typeReference).orElse(Collections.emptyList());
    }

    public static DriverDataResponse getDriver(String id){
        TypeReference<DriverDataResponse> typeReference = new TypeReference<>() {};
        return getValueFromResponse(DriverRequestController.getDriverById(id),typeReference).orElse(null);
    }
}
