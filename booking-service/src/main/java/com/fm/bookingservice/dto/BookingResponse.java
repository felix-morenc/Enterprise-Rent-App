package com.fm.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private String bookingReference;
    private String licencePlate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
