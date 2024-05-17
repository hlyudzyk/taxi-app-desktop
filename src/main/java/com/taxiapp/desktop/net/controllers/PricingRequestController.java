package com.taxiapp.desktop.net.controllers;

import static com.taxiapp.desktop.net.ApiEndpoints.GET_PRICING_POLICY;
import static com.taxiapp.desktop.net.ApiEndpoints.UPDATE_PRICING_POLICY;

import java.net.http.HttpResponse;

public class PricingRequestController extends RequestController{
    public static HttpResponse<String> getPricingPolicy(){
        return getRequest(GET_PRICING_POLICY,null);
    }

    public static HttpResponse<String> updatePricingPolicy(String newPricingPolicy){
        return patchRequest(UPDATE_PRICING_POLICY,newPricingPolicy);
    }

}
