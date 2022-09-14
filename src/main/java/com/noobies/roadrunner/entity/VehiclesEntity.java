package com.noobies.roadrunner.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.noobies.roadrunner.config.GeoJsonDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("vehicles")
public class VehiclesEntity {
    @Id
    private String id;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint currentLocation;
    @Indexed(unique = true)
    private String vin;
    @Indexed(unique = true)
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
    private Integer types;
    private Boolean isAtWarehouse;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint lastUpdatedWarehouseLocation;
    private Boolean isAtService;
}
