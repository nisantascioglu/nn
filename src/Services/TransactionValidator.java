package Services;

import domain.Transaction;

public class TransactionValidator {
    public boolean validateTransaction(Transaction transaction){
        return transaction.validate();
    }
}
