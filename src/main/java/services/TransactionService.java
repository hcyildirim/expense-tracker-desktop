package services;

import models.Transaction;
import repositories.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionService {

    private TransactionRepository transactionRepository = new TransactionRepository();

    public void create(String description, Float amount, Transaction.Type type, String userId) {
        try {
            Transaction transaction = new Transaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setDescription(description);
            transaction.setType(type);
            transaction.setAmount(amount);
            transaction.setCreatedAt(LocalDateTime.now());
            transaction.setUserId(userId);

            transactionRepository.create(transaction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getByUserId(String uuid) throws IOException {
        Predicate<Transaction> userUuidPredicate = d -> d.getUserId().equalsIgnoreCase(uuid);

        return transactionRepository.all().stream()
                .filter(userUuidPredicate)
                .collect(Collectors.toList());
    }
}

