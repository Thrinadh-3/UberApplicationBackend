package com.project.uber.UberApp.strategies.impl;

import com.project.uber.UberApp.entities.Driver;
import com.project.uber.UberApp.entities.RideRequest;
import com.project.uber.UberApp.repositories.DriverRepository;
import com.project.uber.UberApp.strategies.DriverMatching;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRated implements DriverMatching {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
