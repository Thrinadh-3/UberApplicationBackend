package com.project.uber.UberApp.strategies;

import com.project.uber.UberApp.entities.enums.PaymentMethod;
import com.project.uber.UberApp.strategies.impl.CashPaymentsStrategy;
import com.project.uber.UberApp.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentsStrategy cashPaymentsStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return  switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH ->  cashPaymentsStrategy;
        };
    }
}
