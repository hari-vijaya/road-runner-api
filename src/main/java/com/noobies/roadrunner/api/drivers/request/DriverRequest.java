package com.noobies.roadrunner.api.drivers.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.noobies.roadrunner.config.GeoJsonDeserializer;
import com.noobies.roadrunner.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DriverRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String license;
    private String dateOfBirth;
    private String contactNumber;
    private String address;
    private Double kilometersDriven;
    private String status;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint location;
    private Double rating;
    private String bloodGroup;
    private String gender;

}
