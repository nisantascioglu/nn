package Services;

import domain.Transaction;

public class TransactionProcessor {
    public boolean processTransaction(Transaction transaction) {
        return transaction.makeTransaction();
    }
}
