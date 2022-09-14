package com.noobies.roadrunner.entity;

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
public class Address {
    private String locality;
    private String administrativeAreaLevel2;
    private String administrativeAreaLevel1;
    private String country;
    private String postalCode;
    private String route;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint location;
}
