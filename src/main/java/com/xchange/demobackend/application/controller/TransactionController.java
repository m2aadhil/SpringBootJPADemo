package com.xchange.demobackend.application.controller;

import com.xchange.demobackend.application.exception.InventoryNotAvailableException;
import com.xchange.demobackend.domain.transaction.ITransactionService;
import com.xchange.demobackend.infastructure.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping("")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionDTO transaction) throws InventoryNotAvailableException {
        return ResponseEntity.ok(transactionService.processTransaction(transaction));
    }
}
