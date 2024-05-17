package com.taxiapp.desktop.net.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCarRequest {
    private String driverId;
    private String carPlate;
    private String manufacturer;
    private String model;
    private int productionYear;
}