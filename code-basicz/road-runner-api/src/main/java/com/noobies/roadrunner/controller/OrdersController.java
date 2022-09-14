package com.noobies.roadrunner.controller;

import com.google.maps.errors.ApiException;
import com.noobies.roadrunner.api.orders.requests.OrderRequest;
import com.noobies.roadrunner.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    public void saveOrder(@RequestBody OrderRequest orderRequest) throws IOException, InterruptedException, ApiException {
        ordersService.saveOrder(orderRequest);
    }

}
