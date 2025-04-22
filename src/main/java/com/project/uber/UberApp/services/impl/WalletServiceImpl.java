package com.project.uber.UberApp.services.impl;

import com.project.uber.UberApp.entities.Ride;
import com.project.uber.UberApp.entities.User;
import com.project.uber.UberApp.entities.Wallet;
import com.project.uber.UberApp.entities.WalletTransactions;
import com.project.uber.UberApp.entities.enums.TransactionMethod;
import com.project.uber.UberApp.entities.enums.TransactionType;
import com.project.uber.UberApp.exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.repositories.WalletRepository;
import com.project.uber.UberApp.services.WalletService;
import com.project.uber.UberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()+ amount);

        WalletTransactions walletTransactions = WalletTransactions
                .builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransactions);




        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet dedcutMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()- amount);

        WalletTransactions walletTransactions = WalletTransactions
                .builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        //walletTransactionService.createNewWalletTransaction(walletTransactions);
        wallet.getTransactions().add(walletTransactions);

        return walletRepository.save(wallet);

    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(()-> new ResourceNotFoundException("Wallet not found with id:" +walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet= new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);

    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Wallet not found with id:" +user));
    }
}
