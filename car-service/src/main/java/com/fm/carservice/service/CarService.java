package com.fm.carservice.service;

import com.fm.carservice.dto.CarRequest;
import com.fm.carservice.dto.CarResponse;
import com.fm.carservice.model.Car;
import com.fm.carservice.repository.CarRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @PostConstruct
    public void loadSampleData(){
        if(carRepository.count() == 0){
            Car car1 = Car.builder().
                    licencePlate("123abc")
                    .brand("Audi")
                    .model("A3")
                    .pricePerDay(25)
                    .build();
            Car car2 = Car.builder().
                    licencePlate("abc123")
                    .brand("Volvo")
                    .model("V70")
                    .pricePerDay(15)
                    .build();

            carRepository.save(car1);
            carRepository.save(car2);


        }
    }

    public void createCar(CarRequest carRequest){
        Car car = Car.builder()
                .licencePlate(carRequest.getLicencePlate())
                .brand(carRequest.getBrand())
                .model(carRequest.getModel())
                .pricePerDay(carRequest.getPricePerDay())
                .build();
        carRepository.save(car);
    }

    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();

        return cars.stream().map(this::mapToCarResponse).toList();
    }

    public CarResponse getCarByLicencePlate(String licencePlate) {
        Car car = carRepository.findByLicencePlate(licencePlate);

        return mapToCarResponse(car);
    }

    private CarResponse mapToCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .licencePlate(car.getLicencePlate())
                .brand(car.getBrand())
                .model(car.getModel())
                .pricePerDay(car.getPricePerDay())
                .build();
    }


}
