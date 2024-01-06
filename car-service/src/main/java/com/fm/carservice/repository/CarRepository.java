package com.fm.carservice.repository;

import com.fm.carservice.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
    Car findByLicencePlate(String licencePlate);
}
