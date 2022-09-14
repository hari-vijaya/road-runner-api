package com.noobies.roadrunner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuelInfo {
    private String fuelType;
    private Integer capacity;
    private Integer mileage;
    private Integer currentFuelStat;
}
