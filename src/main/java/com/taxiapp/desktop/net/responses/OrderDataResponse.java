package com.taxiapp.desktop.net.responses;

import com.taxiapp.desktop.entities.OrderStatus;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDataResponse {
    private String id;
    private String userId;
    private String userName;
    private String driverId;
    private String driverName;
    private String pickupLocation;
    private String deliveryLocation;
    private double price;
    private double distance;
    private String carId;
    private String taxiType;
    private String orderedAt;
    private String finishedAt;
    private OrderStatus orderStatus;

    public void setDriverId(String driverId) {
        this.driverId = Objects.requireNonNullElse(driverId, "Not assigned");
    }
    public void setDriverName(String driverName) {
        this.driverName = Objects.requireNonNullElse(driverName, "Not assigned");
    }
    public void setFinishedAt(String finishedAt) {
        this.finishedAt = Objects.requireNonNullElse(finishedAt, "In progress");
    }

    public String getPickupAddress(){
        return pickupLocation;
    }

    public String getDeliveryAddress(){
        return deliveryLocation;
    }


}

