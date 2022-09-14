package com.noobies.roadrunner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("warehouses")
public class WarehouseEntity {
    @Id
    private String id;
    private String name;
    private String contactNumber;
    private String address;
    private String manager;
    private Address warehouseLocation;
}
