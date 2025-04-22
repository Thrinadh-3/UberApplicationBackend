package com.project.uber.UberApp.services;

import com.project.uber.UberApp.dto.DriverDto;
import com.project.uber.UberApp.dto.RideDto;
import com.project.uber.UberApp.dto.RiderDTO;
import com.project.uber.UberApp.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RideDto cancelRide(Long rideId);

    RideDto acceptRide(Long rideRequestId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RiderDTO rateRider(Long rideId, Integer rating );

    DriverDto getMyProfile();
    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();
    Driver updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver driver);



}
