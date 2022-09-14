package com.noobies.roadrunner.repository;

import com.noobies.roadrunner.entity.WarehouseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends MongoRepository<WarehouseEntity, String> {
}
