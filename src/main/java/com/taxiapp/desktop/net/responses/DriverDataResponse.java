package com.taxiapp.desktop.net.responses;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverDataResponse {
    private String driverId;
    private String driverName;
    private String driverEmail;
    private String driverStatus;
    private String carId;
    private String carModel;
    private String carPlate;
    private String currentLocation;
    private LocalDate birthday;
}
