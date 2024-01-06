package com.fm.carservice;

import com.fm.carservice.dto.CarRequest;
import com.fm.carservice.dto.CarResponse;
import com.fm.carservice.model.Car;
import com.fm.carservice.repository.CarRepository;
import com.fm.carservice.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CarServiceUnitTests {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Test
    public void testCreateCar() {
        // Arrange
        CarRequest carRequest = new CarRequest();
        carRequest.setLicencePlate("123abc");
        carRequest.setBrand("Audi");
        carRequest.setModel("A3");
        carRequest.setPricePerDay(25);

        // Act
        carService.createCar(carRequest);

        // Assert
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    public void testGetAllCars() {
        // Arrange
        Car car = new Car();
        car.setId(1);
        car.setLicencePlate("123abc");
        car.setBrand("Audi");
        car.setModel("A3");
        car.setPricePerDay(25);

        when(carRepository.findAll()).thenReturn(Arrays.asList(car));

        // Act
        List<CarResponse> cars = carService.getAllCars();

        // Assert
        assertEquals(1, cars.size());
        assertEquals("123abc", cars.get(0).getLicencePlate());
        assertEquals("Audi", cars.get(0).getBrand());
        assertEquals("A3", cars.get(0).getModel());
        assertEquals(25, cars.get(0).getPricePerDay());

        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testGetCarByLicencePlate() {
        // Arrange
        Car car = new Car();
        car.setId(1);
        car.setLicencePlate("123abc");
        car.setBrand("Audi");
        car.setModel("A3");
        car.setPricePerDay(25);

        when(carRepository.findByLicencePlate("123abc")).thenReturn(car);

        // Act
        CarResponse car1 = carService.getCarByLicencePlate("123abc");

        // Assert
        assertEquals(1, car1.getId());
        assertEquals("123abc", car1.getLicencePlate());
        assertEquals("Audi", car1.getBrand());
        assertEquals("A3", car1.getModel());
        assertEquals(25, car1.getPricePerDay());

        verify(carRepository, times(1)).findByLicencePlate(car.getLicencePlate());
    }
}
