package com.taxiapp.desktop.net.controllers;

import static com.taxiapp.desktop.net.ApiEndpoints.ALL_ORDERS_URL;
import static com.taxiapp.desktop.net.ApiEndpoints.NEW_ORDER_URL;
import static com.taxiapp.desktop.net.ApiEndpoints.ORDER_URL;

import java.net.http.HttpResponse;
import java.util.Map;

public class OrdersRequestController extends RequestController{
    public static HttpResponse<String> getAllOrdersAsJson(Map<String,String> filterParams) {
        return getRequest(ALL_ORDERS_URL,filterParams);
    }

    public static HttpResponse<String> getOrderById(String id){
        return getEntityById(ORDER_URL,id);
    }

    public static HttpResponse<String> createNewOrder(String newRequestAsJson){
        return postRequest(NEW_ORDER_URL,newRequestAsJson);
    }
}
