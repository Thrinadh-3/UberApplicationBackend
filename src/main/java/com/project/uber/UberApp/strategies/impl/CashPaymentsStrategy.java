package com.project.uber.UberApp.strategies.impl;

import com.project.uber.UberApp.entities.Driver;
import com.project.uber.UberApp.entities.Payment;
import com.project.uber.UberApp.entities.enums.PaymentStatus;
import com.project.uber.UberApp.entities.enums.TransactionMethod;
import com.project.uber.UberApp.repositories.PaymentRepository;
import com.project.uber.UberApp.services.WalletService;
import com.project.uber.UberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Rider gave 100, say 30 is the commission for the app deduct 30 from driver wallet

@Service
@RequiredArgsConstructor
public class CashPaymentsStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;
        walletService.dedcutMoneyFromWallet(driver.getUser(), platformCommission,null,payment.getRide(), TransactionMethod.RIDE );
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);






    }
}
