package com.noobies.roadrunner.api.warehouses.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.noobies.roadrunner.config.GeoJsonDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WarehouseRequest {
    private String warehouseName;
    private String contactNumber;
    private String address;
    private String personInCharge;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint location;
}
