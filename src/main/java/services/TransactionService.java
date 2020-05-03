package services;

import models.Transaction;
import repositories.TransactionRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import java.io.IOException;

public class TransactionService {

    private TransactionRepository transactionRepository = new TransactionRepository();

    public void create(String description, BigDecimal amount, Transaction.Type type, String userId) {
        try {
            Transaction transaction = new Transaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setDescription(description);
            transaction.setType(type);
            transaction.setAmount(amount);
            transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            transaction.setUserId(userId);

            transactionRepository.create(transaction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

