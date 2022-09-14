package com.noobies.roadrunner.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.noobies.roadrunner.api.drivers.request.DriverRequest;
import com.noobies.roadrunner.api.drivers.response.DriverResponse;
import com.noobies.roadrunner.entity.DriverEntity;
import com.noobies.roadrunner.entity.WarehouseEntity;
import com.noobies.roadrunner.repository.DriverRepository;
import com.noobies.roadrunner.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

//    private final GeoApiContext context;
//
//    private final CommonUtils commonUtils;

    public void saveDriverDetails(DriverRequest driverRequest) throws ApiException, IOException, InterruptedException {
//        LatLng latLngDetails= new LatLng(driverRequest.getLocation().getX(), driverRequest.getLocation().getY());
//        GeocodingResult[] geocodingResult = GeocodingApi.reverseGeocode(context, latLngDetails).await();
        DriverEntity driverEntity = DriverEntity.builder()
                .firstName(driverRequest.getFirstName())
                .lastName(driverRequest.getLastName())
                .contactNumber(driverRequest.getContactNumber())
                .address(driverRequest.getAddress())
//                .driverCurrentLocation(commonUtils.getAddress(geocodingResult))
                .bloodGroup(driverRequest.getBloodGroup())
                .gender(driverRequest.getGender())
                .rating(driverRequest.getRating())
                .kilometersDriven(driverRequest.getKilometersDriven())
                .status(driverRequest.getStatus())
                .dateOfBirth(driverRequest.getDateOfBirth())
                .license(driverRequest.getLicense())
                .email(driverRequest.getEmail())
                .build();
        driverRepository.save(driverEntity);
    }

    public List<DriverEntity> getDriverDetails() {
        return driverRepository.findAll();
    }

    public DriverEntity getDriverDetailById(String id) {
        return driverRepository.findById(id).get();
    }

}
