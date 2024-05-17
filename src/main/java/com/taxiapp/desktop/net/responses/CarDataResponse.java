package com.taxiapp.desktop.net.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDataResponse {
    private String carId;
    private String carPlate;
    private String carManufacturer;
    private String carModel;
    private int carProductionYear;
    private String driverId;
}