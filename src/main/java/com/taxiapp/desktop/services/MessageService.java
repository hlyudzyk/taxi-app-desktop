package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.taxiapp.desktop.net.controllers.MessageRequestController;
import com.taxiapp.desktop.net.requests.NewMessageRequest;
import com.taxiapp.desktop.net.responses.MessageDataResponse;
import java.util.Collections;
import java.util.List;

public class MessageService extends Service{

    public static MessageDataResponse sendMessage(NewMessageRequest newMessageRequest) {
        String newMessageJson;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            newMessageJson = ow.writeValueAsString(newMessageRequest);
        } catch (JsonProcessingException e) {
            newMessageJson = null;
            throw new RuntimeException(e);
        }
        TypeReference<MessageDataResponse> typeReference = new TypeReference<>() {};
        return getValueFromResponse(MessageRequestController.sendMessage(newMessageJson),typeReference).orElse(null);
    }

    public static List<MessageDataResponse> getAllMessages() {
        TypeReference<List<MessageDataResponse>> typeReference = new TypeReference<>() {};
        return getValueFromResponse(MessageRequestController.getAllMessages(),typeReference).orElse(
            Collections.emptyList());
    }
}
