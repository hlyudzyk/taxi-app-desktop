package com.taxiapp.desktop.net.requests;

import com.taxiapp.desktop.entities.OrderStatus;
import com.taxiapp.desktop.entities.TaxiType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterOrdersRequest {
    private String userId;
    private String driverId;
    private LocalDateTime timeAfter;
    private LocalDateTime timeBefore;
    private String pickupLocation;
    private String deliveryLocation;
    private TaxiType taxiType;
    private OrderStatus orderStatus;
}
