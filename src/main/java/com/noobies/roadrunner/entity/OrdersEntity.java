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
@Document("orders")
public class OrdersEntity {
    @Id
    private String id;
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
    private Address origin;
    private Address destination;
    private String assignedWarehouseId;
}
