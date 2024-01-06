package com.fm.carservice.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "car")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Car {
    private int id;
    private String licencePlate;
    private String brand;
    private String model;
    private Integer pricePerDay;
}
