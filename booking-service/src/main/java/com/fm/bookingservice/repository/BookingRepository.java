package com.fm.bookingservice.repository;

import com.fm.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingByBookingReference(String bookingReference);
    void deleteBookingByBookingReference(String bookingReference);
}
