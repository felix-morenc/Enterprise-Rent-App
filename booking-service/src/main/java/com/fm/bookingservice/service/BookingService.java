package com.fm.bookingservice.service;

import com.fm.bookingservice.dto.BookingRequest;
import com.fm.bookingservice.dto.BookingResponse;
import com.fm.bookingservice.dto.CarResponse;
import com.fm.bookingservice.dto.FleetResponse;
import com.fm.bookingservice.model.Booking;
import com.fm.bookingservice.model.Car;
import com.fm.bookingservice.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final WebClient webClient;

    @Value("${carservice.baseurl}")
    private String carServiceBaseUrl;

    @Value("${fleetservice.baseurl}")
    private String fleetServiceBaseUrl;


    public boolean makeBooking(BookingRequest bookingRequest){
        Booking booking = new Booking();
        booking.setBookingReference(UUID.randomUUID().toString());

        String licencePlate = bookingRequest.getLicencePlate();

        FleetResponse fleetResponse = webClient.get()
                .uri("http://" + fleetServiceBaseUrl + "/api/fleet/location",
                        uriBuilder -> uriBuilder.queryParam("licencePlate", licencePlate).build())
                .retrieve()
                .bodyToMono(FleetResponse.class)
                .block();

        booking.setLocation(fleetResponse.getLocation());

        CarResponse carResponse = webClient.get()
                .uri("http://" + carServiceBaseUrl + "/api/car",
                        uriBuilder -> uriBuilder.queryParam("licencePlate", licencePlate).build())
                .retrieve()
                .bodyToMono(CarResponse.class)
                .block();

        booking.setCar(mapToCar(carResponse));
        booking.setDateFrom(bookingRequest.getDateFrom());
        booking.setDateTo(bookingRequest.getDateTo());

        bookingRepository.save(booking);
        return true;


    }

    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
                .map(booking -> new BookingResponse(
                        booking.getId(),
                        booking.getBookingReference(),
                        booking.getCar().getLicencePlate(),
                        booking.getLocation(),
                        booking.getDateFrom(),
                        booking.getDateTo()
                ))
                .collect(Collectors.toList());
    }

    public BookingResponse getBookingByReference(String bookingReference){
        Booking booking = bookingRepository.findBookingByBookingReference(bookingReference);

        return mapToBookingResponse(booking);

    }

    public void deleteBookingByReference(String bookingReference){
        bookingRepository.deleteBookingByBookingReference(bookingReference);
    }

    public BookingResponse updateBooking(Booking booking){
        Booking existingBooking = bookingRepository.findBookingByBookingReference(booking.getBookingReference());
        existingBooking.setLocation(booking.getLocation());
        existingBooking.setCar(booking.getCar());
        existingBooking.setDateFrom(booking.getDateFrom());
        existingBooking.setDateTo(booking.getDateTo());
        bookingRepository.save(existingBooking);
        return mapToBookingResponse(existingBooking);


    }


    private Car mapToCar(CarResponse carResponse) {
        return Car.builder()
                .id(carResponse.getId())
                .licencePlate(carResponse.getLicencePlate())
                .brand(carResponse.getBrand())
                .model(carResponse.getModel())
                .pricePerDay(carResponse.getPricePerDay())
                .build();
    }

    private BookingResponse mapToBookingResponse(Booking booking) {

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setId(booking.getId());
        bookingResponse.setBookingReference(booking.getBookingReference());
        bookingResponse.setLicencePlate(booking.getCar().getLicencePlate());
        bookingResponse.setLocation(booking.getLocation());
        bookingResponse.setDateFrom(booking.getDateFrom());
        bookingResponse.setDateTo(booking.getDateTo());
        return bookingResponse;
    }

}
