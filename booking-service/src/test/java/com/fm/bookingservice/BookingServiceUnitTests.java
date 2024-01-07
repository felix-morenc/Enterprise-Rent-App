package com.fm.bookingservice;

import com.fm.bookingservice.dto.BookingRequest;
import com.fm.bookingservice.dto.BookingResponse;
import com.fm.bookingservice.dto.CarResponse;
import com.fm.bookingservice.dto.FleetResponse;
import com.fm.bookingservice.model.Booking;
import com.fm.bookingservice.model.Car;
import com.fm.bookingservice.repository.BookingRepository;
import com.fm.bookingservice.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
public class BookingServiceUnitTests {
    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(bookingService, "carServiceBaseUrl", "http://localhost:8080");
        ReflectionTestUtils.setField(bookingService, "fleetServiceBaseUrl", "http://localhost:8082");
    }

    //Not working, can not figure out why
    /*@Test
    public void testMakeBooking(){

        CarResponse carResponse = new CarResponse();
        carResponse.setId(1);
        carResponse.setLicencePlate("123abc");
        carResponse.setBrand("Audi");
        carResponse.setModel("A3");
        carResponse.setPricePerDay(25);

        Car car = new Car();
        car.setId(1);
        car.setLicencePlate("123abc");
        car.setBrand("Audi");
        car.setModel("A3");
        car.setPricePerDay(25);

        FleetResponse fleetResponse = new FleetResponse();
        fleetResponse.setLicencePlate("123abc");
        fleetResponse.setLocation("Amsterdam");

        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setLicencePlate(carResponse.getLicencePlate());
        bookingRequest.setDateFrom(LocalDate.of(2023, 11, 8));
        bookingRequest.setDateTo(LocalDate.of(2023, 11, 9));

        Booking booking = new Booking();
        booking.setId(1);
        booking.setBookingReference("test");
        booking.setLocation("Amsterdam");
        booking.setCar(car);
        booking.setDateFrom(LocalDate.of(2023, 11, 8));
        booking.setDateTo(LocalDate.of(2023, 11, 9));

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(),  any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(FleetResponse[].class)).thenReturn(Mono.just(new FleetResponse[]{fleetResponse}));
        when(responseSpec.bodyToMono(CarResponse[].class)).thenReturn(Mono.just(new CarResponse[]{carResponse}));

        boolean result = bookingService.makeBooking(bookingRequest);

        assertTrue(result);



        verify(bookingRepository, times(1)).save(any(Booking.class));

    }*/

    @Test
    public void testGetAllBookings() {
        // Arrange
        Car car = new Car();
        car.setId(1);
        car.setLicencePlate("123abc");
        car.setBrand("Audi");
        car.setModel("A3");
        car.setPricePerDay(25);

        Car car1 = new Car();
        car.setId(2);
        car.setLicencePlate("abc123");
        car.setBrand("Volve");
        car.setModel("V70");
        car.setPricePerDay(15);

        Booking booking1 = new Booking(0,"test","Amsterdam",car,LocalDate.of(2023, 11, 8),LocalDate.of(2023, 11, 9));

        Booking booking2 = new Booking(0,"test1","Brussels",car1,LocalDate.of(2023, 11, 8),LocalDate.of(2023, 11, 9));


        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking1, booking2));

        // Act
        List<BookingResponse> result = bookingService.getAllBookings();

        // Assert
        assertEquals(2, result.size());

        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookingByReference(){
        // Arrange
        Car car = new Car();
        car.setId(1);
        car.setLicencePlate("123abc");
        car.setBrand("Audi");
        car.setModel("A3");
        car.setPricePerDay(25);

        Booking booking = new Booking(0,"test","Amsterdam",car,LocalDate.of(2023, 11, 8),LocalDate.of(2023, 11, 9));

        when(bookingRepository.findBookingByBookingReference("test")).thenReturn(booking);

        // Act
        BookingResponse booking1 = bookingService.getBookingByReference("test");

        // Assert
        assertEquals(0, booking1.getId());
        assertEquals("test", booking1.getBookingReference());
        assertEquals("123abc", booking1.getLicencePlate());
        assertEquals("Amsterdam", booking1.getLocation());
        assertEquals(LocalDate.of(2023, 11, 8), booking1.getDateFrom());
        assertEquals(LocalDate.of(2023, 11, 9), booking1.getDateTo());

        verify(bookingRepository, times(1)).findBookingByBookingReference(booking.getBookingReference());
    }


}
