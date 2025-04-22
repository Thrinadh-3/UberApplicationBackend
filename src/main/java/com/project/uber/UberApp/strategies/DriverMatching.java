package com.project.uber.UberApp.strategies;

import com.project.uber.UberApp.entities.Driver;
import com.project.uber.UberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatching {

     List<Driver> findMatchingDriver(RideRequest rideRequest);
}
