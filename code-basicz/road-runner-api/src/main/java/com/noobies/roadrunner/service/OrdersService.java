package com.noobies.roadrunner.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.noobies.roadrunner.api.orders.requests.OrderRequest;
import com.noobies.roadrunner.entity.OrdersEntity;
import com.noobies.roadrunner.repository.OrdersRepository;
import com.noobies.roadrunner.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final GeoApiContext context;

    public void saveOrder(OrderRequest orderRequest) throws ApiException, IOException, InterruptedException {
        LatLng latLngOrigin = new LatLng(orderRequest.getOrigin().getX(), orderRequest.getOrigin().getY());
        LatLng latLngDestination = new LatLng(orderRequest.getDestination().getX(), orderRequest.getDestination().getY());

        GeocodingResult[] resultOrigin = GeocodingApi.reverseGeocode(context, latLngOrigin).await();
        GeocodingResult[] resultDestination = GeocodingApi.reverseGeocode(context, latLngDestination).await();
        OrdersEntity ordersEntity = OrdersEntity.builder()
                .capacity(orderRequest.getCapacity())
                .customerId(orderRequest.getCustomerId())
                .materialType(orderRequest.getMaterialType())
                .weight(orderRequest.getWeight())
                .isRouteAllocated(orderRequest.getIsRouteAllocated())
                .deliveryStatus(orderRequest.getDeliveryStatus())
                .contactNo(orderRequest.getContactNo())
                .address(orderRequest.getAddress())
                .pinCode(orderRequest.getPinCode())
                .landmark(orderRequest.getLandmark())
                .origin(CommonUtils.getAddress(resultOrigin))
                .destination(CommonUtils.getAddress(resultDestination))
                .build();
        ordersRepository.save(ordersEntity);

    }
}
