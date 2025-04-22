package com.project.uber.UberApp.services.impl;

import com.project.uber.UberApp.dto.DriverDto;
import com.project.uber.UberApp.dto.RideDto;
import com.project.uber.UberApp.dto.RideRequestDto;
import com.project.uber.UberApp.dto.RiderDTO;
import com.project.uber.UberApp.entities.*;
import com.project.uber.UberApp.entities.enums.RideRequestStatus;
import com.project.uber.UberApp.entities.enums.RideStatus;
import com.project.uber.UberApp.exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.repositories.RideRequestRepository;
import com.project.uber.UberApp.repositories.RiderRepository;
import com.project.uber.UberApp.services.DriverService;
import com.project.uber.UberApp.services.RatingService;
import com.project.uber.UberApp.services.RideService;
import com.project.uber.UberApp.services.RiderService;
import com.project.uber.UberApp.strategies.RideStrategyManger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService{

    private final ModelMapper modelMapper;

    private final RideStrategyManger rideStrategyManger;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest=modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare= rideStrategyManger.rideFareCalculation().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest= rideRequestRepository.save(rideRequest);

      List<Driver> drivers= rideStrategyManger.driverMatching(rider.getRating()).findMatchingDriver(rideRequest);


        return modelMapper.map(savedRideRequest, RideRequestDto.class);

    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride= rideService.getRideById(rideId);
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not own this ride"+rideId);
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status"+ ride.getRideStatus());
        }
         Ride savedRide =rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);
        return modelMapper.map(savedRide, RideDto.class);

    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider  is not the owner of this ride");

        }
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not Ended hence start rating,status:"+ride.getRideStatus());

        }
        return ratingService.rateDriver(ride,rating);

    }

    @Override
    public RiderDTO getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDTO.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider= getCurrentRider();

        return rideService.getAllRidesOfRider(currentRider,pageRequest).map(ride -> modelMapper.map(ride,RideDto.class));
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }
    @Override
    public Rider getCurrentRider()
    {
        User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return riderRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Rider not associated with  with id: "+user.getId()));

    }
}
