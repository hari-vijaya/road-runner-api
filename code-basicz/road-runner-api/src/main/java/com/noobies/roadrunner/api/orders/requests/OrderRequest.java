package com.noobies.roadrunner.api.orders.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.noobies.roadrunner.config.GeoJsonDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private Integer capacity;
    private String customerId;
    private String materialType;
    private String weight;
    private Boolean isRouteAllocated;
    private String deliveryStatus;
    private String contactNo;
    private String address;
    private String pinCode;
    private String landmark;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint origin;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint destination;

}
