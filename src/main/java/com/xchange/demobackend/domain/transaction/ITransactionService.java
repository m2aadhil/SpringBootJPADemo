package com.xchange.demobackend.domain.transaction;

import com.xchange.demobackend.infastructure.dto.TransactionDTO;

public interface ITransactionService {

    String processTransaction(TransactionDTO transaction);
}
