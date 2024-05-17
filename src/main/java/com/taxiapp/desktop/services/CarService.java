package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.taxiapp.desktop.net.controllers.CarRequestController;
import com.taxiapp.desktop.net.requests.NewCarRequest;
import com.taxiapp.desktop.net.responses.CarDataResponse;
import java.util.Collections;
import java.util.List;

public class CarService extends Service {
    public static CarDataResponse createNewCar(NewCarRequest newCarRequest){
        String newCarJson;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            newCarJson = ow.writeValueAsString(newCarRequest);
        } catch (JsonProcessingException e) {
            newCarJson = null;
            throw new RuntimeException(e);
        }

        TypeReference<CarDataResponse> typeReference = new TypeReference<>() {};
        return getValueFromResponse(CarRequestController.createNewCar(newCarJson),typeReference).orElse(
            null);
}

    public static List<CarDataResponse> getAllCars(){
        TypeReference<List<CarDataResponse>> typeReference = new TypeReference<>() {};
        return getValueFromResponse(CarRequestController.getAllCars(), typeReference).orElse(
            Collections.emptyList());
    }
}
