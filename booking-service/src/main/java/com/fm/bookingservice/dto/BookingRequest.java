package com.fm.bookingservice.dto;

import com.fm.bookingservice.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private String licencePlate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
