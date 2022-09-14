package com.noobies.roadrunner.api.vehicles.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.noobies.roadrunner.config.GeoJsonDeserializer;
import com.noobies.roadrunner.entity.FuelInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRequest {

    private String id;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint currentLocation;
    private String vin;
    private String insurancePolicyNumber;
    private String make;
    private String model;
    private String year;
    private Double ladenWeight;
    private Double unladenWeight;
    private FuelInfo fuelInfo;
    private Integer capacity;
    private Double kilometersDriven;
    private String color;
    private String materialType;
    private Integer noOfTyres;
    private Boolean isAtWarehouse;
    private String lastUpdatedWarehouseId;
    private String status;
    private String assignedDriverId;

}
