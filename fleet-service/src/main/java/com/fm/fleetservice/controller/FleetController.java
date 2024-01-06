package com.fm.fleetservice.controller;

import com.fm.fleetservice.dto.FleetResponse;
import com.fm.fleetservice.service.FleetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet")
@RequiredArgsConstructor
public class FleetController {

    private final FleetService fleetService;

    // http://localhost:8082/api/fleet?location=amsterdam
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FleetResponse> carsAtLocation
            (@RequestParam String location) {
        return fleetService.carsAtLocation(location);
    }

    // http://localhost:8082/api/fleet/location?licencePlate=123abc
    @GetMapping("/location")
    @ResponseStatus(HttpStatus.OK)
    public FleetResponse carLocation
    (@RequestParam String licencePlate) {
        return fleetService.locatedAt(licencePlate);
    }


}
