package com.jfcorp.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jfcorp.picpaysimplificado.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
