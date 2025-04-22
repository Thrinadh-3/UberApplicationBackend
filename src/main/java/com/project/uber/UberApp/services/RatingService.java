package com.project.uber.UberApp.services;

import com.project.uber.UberApp.dto.DriverDto;
import com.project.uber.UberApp.dto.RiderDTO;
import com.project.uber.UberApp.entities.Ride;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating );
    RiderDTO rateRider(Ride ride , Integer rating);
    void createNewRating(Ride ride);
}
