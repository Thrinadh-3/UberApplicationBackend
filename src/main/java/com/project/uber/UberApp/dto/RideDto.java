package com.project.uber.UberApp.dto;

import com.project.uber.UberApp.entities.enums.PaymentMethod;
import com.project.uber.UberApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long Id;

    private PointDto pickupLocation;

    private PointDto dropOffLocation;


    private LocalDateTime createdTime;


    private RiderDTO rider;


    private DriverDto driver;


    private PaymentMethod paymentMethod;


    private RideStatus rideStatus;
    private String otp;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
