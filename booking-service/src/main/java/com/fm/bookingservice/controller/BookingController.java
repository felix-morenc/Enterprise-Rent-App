package com.fm.bookingservice.controller;

import com.fm.bookingservice.dto.BookingRequest;
import com.fm.bookingservice.dto.BookingResponse;
import com.fm.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void makeBooking(@RequestBody BookingRequest bookingRequest) {
        bookingService.makeBooking(bookingRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
