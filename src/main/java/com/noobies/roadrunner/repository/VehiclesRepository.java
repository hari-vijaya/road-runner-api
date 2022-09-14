package com.noobies.roadrunner.repository;

import com.noobies.roadrunner.entity.VehiclesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiclesRepository extends MongoRepository<VehiclesEntity, String> {
}
