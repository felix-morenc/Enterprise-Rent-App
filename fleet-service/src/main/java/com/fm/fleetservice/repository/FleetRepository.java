package com.fm.fleetservice.repository;

import com.fm.fleetservice.model.FleetInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FleetRepository extends JpaRepository<FleetInfo, Integer> {
    FleetInfo findByLicencePlate(String licencePlate);
    List<FleetInfo> findByLocation(String location);
}
