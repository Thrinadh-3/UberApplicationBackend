package com.project.uber.UberApp.repositories;

import com.project.uber.UberApp.entities.WalletTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransactions, Long>{
}
