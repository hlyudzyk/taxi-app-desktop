package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.taxiapp.desktop.net.controllers.OrdersRequestController;
import com.taxiapp.desktop.net.requests.FilterOrdersRequest;
import com.taxiapp.desktop.net.requests.NewOrderRequest;
import com.taxiapp.desktop.net.responses.OrderDataResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OrderService extends Service{
    public static List<OrderDataResponse> getOrders(FilterOrdersRequest ordersFilter,int page,int limit)  {
        TypeReference<List<OrderDataResponse>> typeReference = new TypeReference<List<OrderDataResponse>>() {};

        List<OrderDataResponse> orders = new ArrayList<>();
        try {
            Map<String,String> params = toMap(ordersFilter);
            params.put("page", String.valueOf(page));
            params.put("limit", String.valueOf(limit));

            orders = getValueFromResponse(
                OrdersRequestController.getAllOrdersAsJson(params),typeReference).orElse(
                Collections.emptyList());
        } catch (IllegalAccessException e) {
            //TODO handle
        }

        return orders;
    }

    public static OrderDataResponse getOrder(String id){
        TypeReference<OrderDataResponse> typeReference = new TypeReference<>() {};
        return getValueFromResponse(OrdersRequestController.getOrderById(id),typeReference).orElse(null);
    }

    public static void createNewOrder(
        String userId,
        String pickupLocation,
        String  deliveryLocation,
        String taxiType){

        String newOrderJson;


        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            NewOrderRequest newOrderRequest = NewOrderRequest.builder()
                .userId(userId)
                .taxiType(taxiType)
                .pickupLocation(pickupLocation)
                .deliveryLocation(deliveryLocation)
                .build();

            newOrderJson = ow.writeValueAsString(newOrderRequest);
            OrdersRequestController.createNewOrder(newOrderJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
