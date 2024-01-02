package domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private Long id;
    private String accountType;
    private double balance;
    private User owner;
    private List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
        this.owner = new User();
    }

    public Account(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean deposit(double amount, Account sender) {
        Transaction depositTransaction = new Transaction(
                LocalDate.now(),
                amount,
                "Deposit",
                sender,
                this,
                "Deposit"
        );

        this.transactions.add(depositTransaction);
        if (amount > 0) {
            this.balance += amount;



            System.out.println("Deposit of " + amount + " made successfully.");
            return true;
        } else {
            System.out.println("Invalid deposit amount. Please provide a positive value.");
            return false;
        }
    }


    public boolean withdraw(double amount) {
        Date date = new Date();
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;

            Transaction withdrawTransaction = new Transaction(
                    LocalDate.now(),
                    amount,
                    "Withdrawal amount: " + amount,
                    this,
                    null,
                    "Withdrawal"
            );

            this.transactions.add(withdrawTransaction);

            System.out.println("Withdrawal of " + amount + " made successfully.");
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
            return false;
        }
    }

    public boolean transfer(double amount, Account receiver, LocalDate date) {
        if (withdraw(amount)) {
            receiver.deposit(amount, this);
            Transaction transferTransaction = new Transaction(
                    date,
                    amount,
                    "Transfer to " + receiver.getOwner().getName(),
                    this,
                    receiver,
                    "Transfer"
            );
            this.transactions.add(transferTransaction);
            System.out.println("Transfer of " + amount + " to account " + receiver.getId() + " made successfully.");
            return true;
        } else {
            System.out.println("Transfer failed: Insufficient balance or invalid amount.");
            return false;
        }
    }

    public List<Transaction> showHistory(){
        for(Transaction transaction: transactions){
            System.out.println(transaction.getDescription());
        }
        return this.transactions;
    }

    public boolean cancelTransaction(Long transactionId) {
        Transaction transactionToCancel = null;

        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                transactionToCancel = transaction;
                break;
            }
        }

        if (transactionToCancel != null) {
            System.out.println("Transaction is cancelled.");
            System.out.println("Now transaction is going to be refunded");
            refundTransaction(transactionToCancel.getId());
            return true;
        }

        System.out.println("Transaction with the given ID not found.");
        return false;
    }

    public boolean refundTransaction(Long transactionId) {
        Transaction transactionToRefund = null;

        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                transactionToRefund = transaction;
                break;
            }
        }

        if (transactionToRefund != null) {
            if (transactionToRefund.getType().equals("Deposit")) {
                if (this.balance >= transactionToRefund.getAmount()) {
                    this.balance -= transactionToRefund.getAmount();
                    transactions.remove(transactionToRefund);
                    System.out.println("Deposit transaction refunded successfully.");
                    return true;
                } else {
                    System.out.println("Insufficient balance to refund deposit.");
                    return false;
                }
            } else if (transactionToRefund.getType().equals("Withdrawal")) {
                this.balance += transactionToRefund.getAmount();
                transactions.remove(transactionToRefund);
                System.out.println("Withdrawal transaction refunded successfully.");
                return true;
            } else if (transactionToRefund.getType().equals("Transfer")) {
                Account senderAccount = transactionToRefund.getSenderAccount();
                Account receiverAccount = transactionToRefund.getReceiverAccount();

                if (senderAccount != null && senderAccount.getTransactions().contains(transactionToRefund)) {
                    senderAccount.deposit(transactionToRefund.getAmount(), receiverAccount);
                    transactions.remove(transactionToRefund);
                    System.out.println("Transfer transaction refunded successfully.");
                    return true;
                } else {
                    System.out.println("Failed to refund transfer transaction.");
                    return false;
                }
            }
        }

        System.out.println("Transaction with the given ID not found.");
        return false;
    }



}
