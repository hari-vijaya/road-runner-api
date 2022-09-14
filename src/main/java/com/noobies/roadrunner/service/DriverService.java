package com.noobies.roadrunner.service;

import com.google.maps.errors.ApiException;
import com.noobies.roadrunner.api.drivers.request.DriverRequest;
import com.noobies.roadrunner.entity.DriverEntity;
import com.noobies.roadrunner.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public void saveDriverDetails(DriverRequest driverRequest) throws ApiException, IOException, InterruptedException {
        DriverEntity driverEntity = DriverEntity.builder()
                .firstName(driverRequest.getFirstName())
                .lastName(driverRequest.getLastName())
                .contactNumber(driverRequest.getContactNumber())
                .address(driverRequest.getAddress())
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
