package com.taxiapp.desktop.net.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewOrderRequest {
    private String userId;
    private String pickupLocation;
    private String  deliveryLocation;
    private String taxiType;
}
