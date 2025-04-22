package com.project.uber.UberApp.strategies.impl;

import com.project.uber.UberApp.entities.Driver;
import com.project.uber.UberApp.entities.Payment;
import com.project.uber.UberApp.entities.Rider;
import com.project.uber.UberApp.entities.enums.PaymentStatus;
import com.project.uber.UberApp.entities.enums.TransactionMethod;
import com.project.uber.UberApp.repositories.PaymentRepository;
import com.project.uber.UberApp.services.WalletService;
import com.project.uber.UberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//Rider wallet 230, uber cost 100 and 30 is the commission, rider=230-100, and driver= 500+(100-30)=570
@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        walletService.dedcutMoneyFromWallet(rider.getUser(), payment.getAmount(),null, payment.getRide(), TransactionMethod.RIDE);
        double driversCut= payment.getAmount()*(1-PLATFORM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(), driversCut,null, payment.getRide(),TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
