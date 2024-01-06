package com.fm.bookingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookingReference;
    private String location;
    @OneToOne(mappedBy = "licencePlate")
    private Car car;
    private LocalDate dateFrom;
    private LocalDate dateTo;



}
