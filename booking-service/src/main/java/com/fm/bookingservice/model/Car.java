package com.fm.bookingservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "car")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String licencePlate;
    private String brand;
    private String model;
    private Integer pricePerDay;
}