package com.fm.carservice.controller;


import com.fm.carservice.dto.CarRequest;
import com.fm.carservice.dto.CarResponse;
import com.fm.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createCar
            (@RequestBody CarRequest carRequest) {
        carService.createCar(carRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getAllCarsByLicensePlate
            (@RequestParam String licencePlate) {
        return carService.getCarByLicencePlate(licencePlate);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
    }
}
