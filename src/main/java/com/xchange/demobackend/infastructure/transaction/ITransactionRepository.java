package com.xchange.demobackend.infastructure.transaction;

import com.xchange.demobackend.infastructure.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

}
