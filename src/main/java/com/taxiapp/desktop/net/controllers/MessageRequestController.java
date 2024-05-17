package com.taxiapp.desktop.net.controllers;

import com.taxiapp.desktop.net.ApiEndpoints;
import java.net.http.HttpResponse;

public class MessageRequestController extends RequestController{

    public static HttpResponse<String> sendMessage(String newMessageJson){
        return postRequest(ApiEndpoints.NEW_MESSAGE_URL,newMessageJson);
    }

    public static HttpResponse<String> getAllMessages() {
        return getRequest(ApiEndpoints.ALL_MESSAGES_URL,null);
    }
}
