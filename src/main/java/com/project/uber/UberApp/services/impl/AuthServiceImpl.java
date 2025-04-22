package com.project.uber.UberApp.services.impl;

import com.project.uber.UberApp.dto.DriverDto;
import com.project.uber.UberApp.dto.SignupDto;
import com.project.uber.UberApp.dto.UserDto;
import com.project.uber.UberApp.entities.Driver;
import com.project.uber.UberApp.entities.User;
import com.project.uber.UberApp.entities.enums.Roles;
import com.project.uber.UberApp.exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.exceptions.RuntimeConflictException;
import com.project.uber.UberApp.repositories.UserRepository;
import com.project.uber.UberApp.security.JWTService;
import com.project.uber.UberApp.services.AuthService;
import com.project.uber.UberApp.services.DriverService;
import com.project.uber.UberApp.services.RiderService;
import com.project.uber.UberApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
private final UserRepository userRepository;
    private  final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {

       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
       User user = (User) authentication.getPrincipal();
       String accessToken= jwtService.generateAccessToken(user);
       String refreshToken= jwtService.generateRefreshToken(user);

      return  new String[] {accessToken, refreshToken};

    }

    @Override
    public UserDto signup(SignupDto signupDto) {
       User user =  userRepository.findByEmail(signupDto.getEmail()).orElse(null);
       if(user!=null)
           throw new RuntimeConflictException("Cannot signup, user already exists with mail"+ signupDto.getEmail());
        User mappedUser= modelMapper.map(signupDto,User.class );
        mappedUser.setRoles(Set.of(Roles.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);
        // creating user related entities
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);






        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id"+ userId));
        if(user.getRoles().contains(Roles.DRIVER)) throw  new RuntimeConflictException("User with id"+ userId+" is already driver");

        Driver createNewDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(Roles.DRIVER);
        userRepository.save(user);

       Driver savedDriver = driverService.createNewDriver(createNewDriver);
       return modelMapper.map(savedDriver, DriverDto.class);

    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id not found"+ userId));

        return jwtService.generateAccessToken(user);
    }
}
