package com.noobies.roadrunner.controller;

import com.google.maps.errors.ApiException;
import com.noobies.roadrunner.api.drivers.request.DriverRequest;
import com.noobies.roadrunner.entity.DriverEntity;
import com.noobies.roadrunner.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/save")
    public void saveDriverDetails(@RequestBody DriverRequest driverRequest) throws IOException, InterruptedException, ApiException {
        driverService.saveDriverDetails(driverRequest);
    }

    @GetMapping("/getAll")
    public List<DriverEntity> getDriversDetails() {
        return driverService.getDriverDetails();
    }

    @GetMapping("/getDriverDetail")
    public DriverEntity getDriverDetailById(String id) {
        return driverService.getDriverDetailById(id);
    }
}
