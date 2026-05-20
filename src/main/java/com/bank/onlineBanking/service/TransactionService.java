package com.bank.onlineBanking.service;

import com.bank.onlineBanking.model.Transaction;
import com.bank.onlineBanking.model.User;
import com.bank.onlineBanking.repository.TransactionRepository;
import com.bank.onlineBanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transferMoney(String senderUsername, String receiverUsername, BigDecimal amount) throws Exception {
        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new Exception("Sender not found"));
        User receiver = userRepository.findByUsername(receiverUsername)
                .orElseThrow(() -> new Exception("Receiver not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Transfer amount must be greater than zero");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient balance");
        }

        // Deduct from sender
        sender.setBalance(sender.getBalance().subtract(amount));
        userRepository.save(sender);

        // Add to receiver
        receiver.setBalance(receiver.getBalance().add(amount));
        userRepository.save(receiver);

        // Create transaction for sender
        Transaction senderTx = new Transaction();
        senderTx.setAmount(amount);
        senderTx.setDate(LocalDateTime.now());
        senderTx.setType("DEBIT");
        senderTx.setDetails("Sent to <strong>" + receiver.getUsername() + "</strong>");
        senderTx.setUser(sender);
        transactionRepository.save(senderTx);

        // Create transaction for receiver
        Transaction receiverTx = new Transaction();
        receiverTx.setAmount(amount);
        receiverTx.setDate(LocalDateTime.now());
        receiverTx.setType("CREDIT");
        receiverTx.setDetails("Received from <strong>" + sender.getUsername() + "</strong>");
        receiverTx.setUser(receiver);
        transactionRepository.save(receiverTx);
    }

    public List<Transaction> getTransactionHistory(Long userId) {
        return transactionRepository.findByUserIdOrderByDateDesc(userId);
    }
}
