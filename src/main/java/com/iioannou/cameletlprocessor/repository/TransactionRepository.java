package com.iioannou.cameletlprocessor.repository;

import com.iioannou.cameletlprocessor.domain.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
