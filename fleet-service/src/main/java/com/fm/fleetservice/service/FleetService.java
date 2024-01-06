package com.fm.fleetservice.service;


import com.fm.fleetservice.dto.FleetResponse;
import com.fm.fleetservice.model.FleetInfo;
import com.fm.fleetservice.repository.FleetRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FleetService {

    private final FleetRepository fleetRepository;

    @PostConstruct
    public void loadSampleData() {
        if(fleetRepository.count() <= 0){
            FleetInfo fleetInfo1 = new FleetInfo();
            fleetInfo1.setLicencePlate("123abc");
            fleetInfo1.setLocation("Amsterdam");

            FleetInfo fleetInfo2 = new FleetInfo();
            fleetInfo2.setLicencePlate("abc123");
            fleetInfo2.setLocation("Antwerp");

            fleetRepository.save(fleetInfo1);
            fleetRepository.save(fleetInfo2);
        }
    }

    @Transactional(readOnly = true)
    public FleetResponse locatedAt(String licencePlate) {

        FleetInfo fleetInfo = fleetRepository.findByLicencePlate(licencePlate);

        return mapToFleetResponse(fleetInfo);
    }

    @Transactional(readOnly = true)
    public List<FleetResponse> carsAtLocation(String location) {
        return fleetRepository.findByLocation(location).stream()
                .map(fleetInfo ->
                        FleetResponse.builder()
                                .licencePlate(fleetInfo.getLicencePlate())
                                .location(fleetInfo.getLocation())
                                .build()
                ).toList();
    }

    private FleetResponse mapToFleetResponse(FleetInfo fleetInfo) {
        return FleetResponse.builder()
                .licencePlate(fleetInfo.getLicencePlate())
                .location(fleetInfo.getLocation())
                .build();
    }
}
