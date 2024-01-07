package com.fm.fleetservice;

import com.fm.fleetservice.dto.FleetResponse;
import com.fm.fleetservice.model.FleetInfo;
import com.fm.fleetservice.repository.FleetRepository;
import com.fm.fleetservice.service.FleetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FleetServiceUnitTests {

    @InjectMocks
    private FleetService fleetService;

    @Mock
    private FleetRepository fleetRepository;


    @Test
    public void testLocatedAt(){
        FleetInfo fleetInfo = new FleetInfo();
        fleetInfo.setId(0);
        fleetInfo.setLicencePlate("123abc");
        fleetInfo.setLocation("Amsterdam");

        when(fleetRepository.findByLicencePlate("123abc")).thenReturn(fleetInfo);

        // Act
        FleetResponse fleetInfo1 = fleetService.locatedAt("123abc");

        // Assert
        assertEquals(0, fleetInfo1.getId());
        assertEquals("123abc", fleetInfo1.getLicencePlate());
        assertEquals("Amsterdam", fleetInfo1.getLocation());

        verify(fleetRepository, times(1)).findByLicencePlate(fleetInfo.getLicencePlate());
    }

    @Test
    public void testCarsAtLocation(){
        FleetInfo fleetInfo = new FleetInfo();
        fleetInfo.setId(0);
        fleetInfo.setLicencePlate("123abc");
        fleetInfo.setLocation("Amsterdam");
        List<FleetInfo> fleetInfos = Arrays.asList(fleetInfo);

        when(fleetRepository.findByLocation("Amsterdam")).thenReturn(fleetInfos);

        // Act
        List<FleetResponse> fleetInfos1 = fleetService.carsAtLocation("Amsterdam");

        // Assert
        assertEquals(0, fleetInfos1.get(0).getId());
        assertEquals("123abc", fleetInfos1.get(0).getLicencePlate());
        assertEquals("Amsterdam", fleetInfos1.get(0).getLocation());

        verify(fleetRepository, times(1)).findByLocation(fleetInfo.getLocation());
    }
}
