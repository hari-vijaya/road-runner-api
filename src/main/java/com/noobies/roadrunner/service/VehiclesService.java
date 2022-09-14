package com.noobies.roadrunner.service;

import com.noobies.roadrunner.api.vehicles.requests.VehicleRequest;
import com.noobies.roadrunner.entity.VehiclesEntity;
import com.noobies.roadrunner.repository.VehiclesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiclesService {

    private final VehiclesRepository vehiclesRepository;

    public void saveVehicle(VehicleRequest vehicleRequest) {
        VehiclesEntity vehiclesEntity = VehiclesEntity.builder()
                .currentLocation(vehicleRequest.getCurrentLocation())
                .vin(vehicleRequest.getVin())
                .insurancePolicyNumber(vehicleRequest.getInsurancePolicyNumber())
                .make(vehicleRequest.getMake())
                .model(vehicleRequest.getModel())
                .year(vehicleRequest.getYear())
                .ladenWeight(vehicleRequest.getLadenWeight())
                .unladenWeight(vehicleRequest.getUnladenWeight())
                .fuelInfo(vehicleRequest.getFuelInfo())
                .capacity(vehicleRequest.getCapacity())
                .kilometersDriven(vehicleRequest.getKilometersDriven())
                .color(vehicleRequest.getColor())
                .materialType(vehicleRequest.getMaterialType())
                .noOfTyres(vehicleRequest.getNoOfTyres())
                .isAtWarehouse(vehicleRequest.getIsAtWarehouse())
                .lastUpdatedWarehouseId(vehicleRequest.getLastUpdatedWarehouseId())
                .status(vehicleRequest.getStatus())
                .build();
        vehiclesRepository.save(vehiclesEntity);
    }

    public List<VehiclesEntity> getAllVehicles() {
        return vehiclesRepository.findAll();
    }

    public VehiclesEntity getVehicleById(String id) {
        return vehiclesRepository.findById(id).get();
    }
}
