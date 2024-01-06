package com.fm.fleetservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fleetinfo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FleetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String licencePlate;
    private String location;
}
