package com.project.uber.UberApp.services.impl;

import com.project.uber.UberApp.entities.Payment;
import com.project.uber.UberApp.entities.Ride;
import com.project.uber.UberApp.entities.enums.PaymentStatus;
import com.project.uber.UberApp.exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.repositories.PaymentRepository;
import com.project.uber.UberApp.services.PaymentService;
import com.project.uber.UberApp.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;
    @Override
    public void processPayment(Ride ride) {
        Payment payment= paymentRepository.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("Payment not found for ride wirh id:"+ride));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);

    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);

    }
}
