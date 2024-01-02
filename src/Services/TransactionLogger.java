package Services;

import domain.Transaction;

public class TransactionLogger {
    public void logTransaction(Transaction transaction) {
        transaction.logTransaction();
    }
}
