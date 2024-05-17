package com.taxiapp.desktop.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.taxiapp.desktop.entities.PricingPolicy;
import com.taxiapp.desktop.net.controllers.PricingRequestController;
import com.taxiapp.desktop.net.responses.PricingPolicyResponse;

public class PricingService extends Service{
    public static PricingPolicyResponse getPricingPolicy(){
        TypeReference<PricingPolicyResponse> typeReference = new TypeReference<>(){};
        return getValueFromResponse(PricingRequestController.getPricingPolicy(),typeReference).orElse(null);
    }

    public static boolean updatePricingPolicy(PricingPolicy pricingPolicy){
        TypeReference<Boolean> typeReference = new TypeReference<>(){};
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonPricingPolicy;
        try {
            jsonPricingPolicy = ow.writeValueAsString(pricingPolicy);
        } catch (JsonProcessingException e) {
            jsonPricingPolicy = null;
            throw new RuntimeException(e);
        }

        return getValueFromResponse(PricingRequestController.updatePricingPolicy(jsonPricingPolicy),typeReference).orElse(false);


    }
}
