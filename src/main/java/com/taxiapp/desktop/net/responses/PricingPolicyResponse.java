package com.taxiapp.desktop.net.responses;

import lombok.Data;

@Data
public class PricingPolicyResponse {
    private double tariff;
    private double basicTaxiTypeMultiplier;
    private double cargoTaxiTypeMultiplier;
    private double premiumTaxiTypeMultiplier;
    private double companyChargePerOrder;
}
