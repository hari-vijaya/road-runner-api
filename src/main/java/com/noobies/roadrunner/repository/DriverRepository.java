package com.noobies.roadrunner.repository;

import com.noobies.roadrunner.entity.DriverEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepository extends MongoRepository<DriverEntity, String> {

}
