package com.noobies.roadrunner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("drivers")
public class DriverEntity {

    @Id
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
    private Address driverCurrentLocation;
    private Double rating;
    private String bloodGroup;
    private String gender;
}
