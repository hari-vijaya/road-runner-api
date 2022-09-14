package com.noobies.roadrunner.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.noobies.roadrunner.api.warehouses.request.WarehouseRequest;
import com.noobies.roadrunner.entity.WarehouseEntity;
import com.noobies.roadrunner.repository.WarehouseRepository;
import com.noobies.roadrunner.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    private final GeoApiContext context;

    private final CommonUtils commonUtils;

    public void saveWarehouse(WarehouseRequest warehouseRequest) throws ApiException, IOException, InterruptedException {
        LatLng latLngWarehouse = new LatLng(warehouseRequest.getLocation().getX(), warehouseRequest.getLocation().getY());

        GeocodingResult[] resultWarehouse = GeocodingApi.reverseGeocode(context, latLngWarehouse).await();
        WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .name(warehouseRequest.getName())
                .contactNumber(warehouseRequest.getContactNumber())
                .address(warehouseRequest.getAddress())
                .manager(warehouseRequest.getManager())
                .warehouseLocation(commonUtils.getAddress(resultWarehouse))
                .build();
        warehouseRepository.save(warehouseEntity);
    }
}
