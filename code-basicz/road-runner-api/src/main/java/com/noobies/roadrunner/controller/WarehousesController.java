package com.noobies.roadrunner.controller;

import com.google.maps.errors.ApiException;
import com.noobies.roadrunner.api.warehouses.request.WarehouseRequest;
import com.noobies.roadrunner.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/warehouses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WarehousesController {

    private final WarehouseService warehouseService;

    @PostMapping
    public void saveOrder(@RequestBody WarehouseRequest warehouseRequest) throws IOException, InterruptedException, ApiException {
        warehouseService.saveWarehouse(warehouseRequest);
    }

}
