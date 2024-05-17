package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.taxiapp.desktop.net.controllers.UserRequestController;
import com.taxiapp.desktop.net.requests.FilterUsersRequest;
import com.taxiapp.desktop.net.responses.UserDataResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserService extends Service{
    public static List<UserDataResponse> getUsers(FilterUsersRequest filterUsersRequest,int page,int limit)  {
        TypeReference<List<UserDataResponse>> typeReference = new TypeReference<List<UserDataResponse>>() {};

        List<UserDataResponse> users = new ArrayList<>();
        try {
            Map<String,String> params = toMap(filterUsersRequest);
            params.put("page", String.valueOf(page));
            params.put("limit", String.valueOf(limit));

            users = getValueFromResponse(
                UserRequestController.getAllUsersAsJson(params),typeReference).orElse(
                Collections.emptyList());
        } catch (IllegalAccessException e) {
            //TODO handle
        }

        return users;
    }
    public static UserDataResponse getUser(String id){
        TypeReference<UserDataResponse> typeReference = new TypeReference<UserDataResponse>() {};
        return getValueFromResponse(UserRequestController.getUserById(id),typeReference).orElse(null);
    }

}
