package com.noobies.roadrunner.controller;

import com.noobies.roadrunner.api.vehicles.requests.VehicleRequest;
import com.noobies.roadrunner.entity.VehiclesEntity;
import com.noobies.roadrunner.service.VehiclesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VehiclesController {

    private final VehiclesService vehiclesService;

    @PostMapping
    public void saveVehicle(@RequestBody VehicleRequest vehicleRequest) {
        vehiclesService.saveVehicle(vehicleRequest);
    }

    @GetMapping("/getAll")
    public List<VehiclesEntity> getAllVehicles() {
        return vehiclesService.getAllVehicles();
    }

    @GetMapping("/getVehicleDetail")
    public VehiclesEntity getVehicleById(@RequestParam String id) {
        return vehiclesService.getVehicleById(id);
    }
}
