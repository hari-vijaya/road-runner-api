package com.noobies.roadrunner.utils;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.noobies.roadrunner.entity.Address;
import com.noobies.roadrunner.entity.WarehouseEntity;
import com.noobies.roadrunner.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommonUtils {

    private final WarehouseRepository warehouseRepository;
    private final GeoApiContext context;

    public Address getAddress(GeocodingResult[] results) {
        Address address = new Address();
        Arrays.stream(results[0].addressComponents).forEach(addressComponent -> {
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.LOCALITY))
                address.setLocality(addressComponent.longName);
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1))
                address.setAdministrativeAreaLevel1(addressComponent.longName);
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2))
                address.setAdministrativeAreaLevel2(addressComponent.longName);
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.COUNTRY))
                address.setCountry(addressComponent.longName);
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.POSTAL_CODE))
                address.setPostalCode(addressComponent.longName);

        });
        address.setLocation(new GeoJsonPoint(results[0].geometry.location.lat, results[0].geometry.location.lng));
        return address;
    }

    public String findNearByWarehouse(String[] origins) throws IOException, InterruptedException, ApiException {
        List<WarehouseEntity> warehouseEntities = warehouseRepository.findAll();

        List<String> wareHouseLocations = warehouseEntities.stream()
                .map(warehouseEntity -> warehouseEntity.getWarehouseLocation().getLocation().getCoordinates().get(0) + "," + warehouseEntity.getWarehouseLocation().getLocation().getCoordinates().get(1))
                .collect(Collectors.toList());
        String[] destinations = new String[wareHouseLocations.size()];
        wareHouseLocations.toArray(destinations);


        DistanceMatrixRow[] matrix =
                DistanceMatrixApi.getDistanceMatrix(context, origins, destinations).await().rows;
        long min = 0;
        int minIndex = 0;
        for (int i = 0; i < origins.length; i++) {
            for (int j = 0; j < destinations.length; j++) {
                DistanceMatrixElement[] elements = matrix[i].elements;
                DistanceMatrixElement element = elements[j];
                if (min > element.distance.inMeters) {
                    min = element.distance.inMeters;
                    minIndex = j;
                }
                System.out.println(element.status.name());
                System.out.println(element.distance.inMeters);
            }
        }

        return warehouseEntities.get(minIndex).getId();
    }
}

