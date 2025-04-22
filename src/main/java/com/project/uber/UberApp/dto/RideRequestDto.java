package com.project.uber.UberApp.dto;

import com.project.uber.UberApp.entities.enums.PaymentMethod;
import com.project.uber.UberApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {


    private Long Id;

    private PointDto pickupLocation;

    private PointDto dropOffLocation;


    private LocalDateTime requestedTime;


    private RiderDTO rider;


    private PaymentMethod paymentMethod;
    private Double fare;


    private RideRequestStatus rideRequestStatus;
}
