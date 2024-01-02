package domain;


import Services.TransactionLogger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Transaction {
    private Long id;
    private LocalDate date;
    private double amount;
    private String description;
    private Account senderAccount;
    private Account receiverAccount;
    private String type;

    private List<Transaction> transactionHistory;

    public Transaction() {
        this.transactionHistory = new ArrayList<>();
    }


    public Transaction(LocalDate date, double amount, String description, Account senderAccount, Account receiverAccount, String type) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.type = type;
        this.transactionHistory = new ArrayList<>();
    }
    public void generateRandomTransactions(int count) {
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            Transaction randomTransaction = new Transaction(
                    LocalDate.now().minusDays(random.nextInt(30)),
                    random.nextDouble() * 500,
                    "Random Transaction " + i,
                    senderAccount, receiverAccount,
                    (random.nextBoolean() ? "Deposit" : "Withdrawal")
            );
            randomTransaction.setId((long) (i + 2)); // Avoid ID conflicts with existing transactions
            transactionHistory.add(randomTransaction);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean makeTransaction() {
        TransactionLogger transactionLogger = new TransactionLogger();
        if (senderAccount != null && receiverAccount != null && amount > 0) {
            double initialSenderBalance = senderAccount.getBalance();
            double initialReceiverBalance = receiverAccount.getBalance();

            if (senderAccount.withdraw(amount) && receiverAccount.deposit(amount, senderAccount)) {
                transactionLogger.logTransaction(this);
                return true;
            } else {
                senderAccount.setBalance(initialSenderBalance);
                receiverAccount.setBalance(initialReceiverBalance);
                return false;
            }
        }
        return false;
    }

    public List<Transaction> showHistory() {
        for(Transaction t : transactionHistory){
            System.out.println("Transaction id:");
            System.out.println(t.getId());
            System.out.println("Transaction date:");
            System.out.println(t.getDate());
            System.out.println("Transaction amount:");
            System.out.println(t.getAmount());
            System.out.println("Transaction description:");
            System.out.println(t.getDescription());
            System.out.println("Transaction type:");
            System.out.println(t.getType());
        }
        System.out.println("Transaction history displayed.");
        return transactionHistory;
    }


    public boolean validate() {
        return id != null && date != null && amount >= 0 && senderAccount != null && receiverAccount != null && !type.isEmpty();
    }

    public void logTransaction() {
        System.out.println("Transaction ID: " + id);
        System.out.println("Date: " + date);
        System.out.println("Amount: " + amount);
        System.out.println("Description: " + description);
        System.out.println("Sender Account: " + senderAccount.getOwner().getName());
        System.out.println("Receiver Account: " + receiverAccount.getOwner().getName());
        System.out.println("Type: " + type);
    }

    public List<Transaction> getTransactionsByDate(LocalDate date) {
        List<Transaction> matchingTransactions = new ArrayList<>();

        for (Transaction transaction : transactionHistory) {
            if (transaction.getDate().equals(date)) {
                matchingTransactions.add(transaction);
            }
        }

        return matchingTransactions;
    }

    public List<Transaction> getTransactionsByAmount(double amount) {
        List<Transaction> matchingTransactions = new ArrayList<>();

        for (Transaction transaction : transactionHistory) {
            if (transaction.getAmount() == amount) {
                matchingTransactions.add(transaction);
            }
        }

        return matchingTransactions;
    }

    public List<Transaction> getTransactionsByDescription(String description) {
        List<Transaction> matchingTransactions = new ArrayList<>();

        for (Transaction transaction : transactionHistory) {
            if (transaction.getDescription().equals(description)) {
                matchingTransactions.add(transaction);
            }
        }

        return matchingTransactions;
    }

    public List<Transaction> getTransactionsBySenderAccount(Account senderAccount) {
        List<Transaction> matchingTransactions = new ArrayList<>();

        for (Transaction transaction : transactionHistory) {
            if (transaction.getSenderAccount().equals(senderAccount)) {
                matchingTransactions.add(transaction);
            }
        }

        return matchingTransactions;
    }

    public List<Transaction> getTransactionsByReceiverAccount(Account receiverAccount) {
        List<Transaction> matchingTransactions = new ArrayList<>();

        for (Transaction transaction : transactionHistory) {
            if (transaction.getReceiverAccount().equals(receiverAccount)) {
                matchingTransactions.add(transaction);
            }
        }

        return matchingTransactions;
    }

    public List<Transaction> getTransactionsByType(String type) {
        List<Transaction> matchingTransactions = new ArrayList<>();

        for (Transaction transaction : transactionHistory) {
            if (transaction.getType().equals(type)) {
                matchingTransactions.add(transaction);
            }
        }

        return matchingTransactions;
    }

    public List<Transaction> getTransactionsByAllCriteria(
            LocalDate date, double amount, String description,
            Account senderAccount, Account receiverAccount, String type) {
        List<Transaction> matchingTransactions = new ArrayList<>();

        for (Transaction transaction : transactionHistory) {
            if (transaction.getDate().equals(date) &&
                    transaction.getAmount() == amount &&
                    transaction.getDescription().equals(description) &&
                    transaction.getSenderAccount().equals(senderAccount) &&
                    transaction.getReceiverAccount().equals(receiverAccount) &&
                    transaction.getType().equals(type)) {
                matchingTransactions.add(transaction);
            }
        }

        return matchingTransactions;
    }



}
