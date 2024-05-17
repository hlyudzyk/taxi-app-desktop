package com.taxiapp.desktop.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PricingPolicy {
    private double tariff;
    private double basicTaxiTypeMultiplier;
    private double cargoTaxiTypeMultiplier;
    private double premiumTaxiTypeMultiplier;
    private double companyChargePerOrder;
}