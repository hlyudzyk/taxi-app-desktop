package com.taxiapp.desktop.net.controllers;

import static com.taxiapp.desktop.net.ApiEndpoints.ALL_USERS_URL;
import static com.taxiapp.desktop.net.ApiEndpoints.USER_URL;

import java.net.http.HttpResponse;
import java.util.Map;

public class UserRequestController extends RequestController{

    public static HttpResponse<String> getAllUsersAsJson(Map<String,String> filterParams) {
        return getRequest(ALL_USERS_URL,filterParams);
    }

    public static HttpResponse<String> getUserById(String id){
        return getEntityById(USER_URL,id);
    }
}
