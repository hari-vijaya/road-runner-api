package com.noobies.roadrunner.repository;

import com.noobies.roadrunner.entity.OrdersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<OrdersEntity, String> {
}
