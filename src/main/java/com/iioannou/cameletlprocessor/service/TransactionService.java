package com.iioannou.cameletlprocessor.service;


import com.iioannou.cameletlprocessor.domain.Transactions;
import com.iioannou.cameletlprocessor.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void persistTransaction(Transactions transaction) {
        transactionRepository.save(transaction);
    }

    public void persistTransactions(List<Transactions> transactions) {
        transactions.forEach(o -> {
            log.info("Order object {}", o.toString());
            transactionRepository.save(o);
        });
    }
}
