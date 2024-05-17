package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.taxiapp.desktop.net.controllers.AuthenticationRequestController;
import com.taxiapp.desktop.net.requests.RegisterRequest;

public class AdminService extends  Service {
    public static boolean registerUser(RegisterRequest registerRequest){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();

            String registerRequestJson = ow.writeValueAsString(registerRequest);
            AuthenticationRequestController.registerUser(registerRequestJson);
            return true;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
