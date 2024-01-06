package com.fm.carservice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private int id;
    private String licencePlate;
    private String brand;
    private String model;
    private Integer pricePerDay;
}
