package com.project.uber.UberApp.strategies;

import com.project.uber.UberApp.strategies.impl.DriverMatchingHighestRated;
import com.project.uber.UberApp.strategies.impl.DriverMatchingNearestDriver;
import com.project.uber.UberApp.strategies.impl.RideFareDefaultFare;
import com.project.uber.UberApp.strategies.impl.RideFareSurgePricingFare;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManger {
    private  final DriverMatchingHighestRated driverMatchingHighestRated;
    private final DriverMatchingNearestDriver driverMatchingNearestDriver;
    private final RideFareDefaultFare rideFareDefaultFare;
    private final RideFareSurgePricingFare rideFareSurgePricingFare;

    public DriverMatching driverMatching(double riderRating){
        if(riderRating >= 4.8){
            return driverMatchingHighestRated;
        }
        else{
            return driverMatchingNearestDriver;
        }

    }
    public RideFareCalculation rideFareCalculation(){
        // 6PM TO 9 PM
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();
         boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
         if(isSurgeTime){
             return rideFareSurgePricingFare;
         }else{
             return rideFareDefaultFare;
         }

    }

}
