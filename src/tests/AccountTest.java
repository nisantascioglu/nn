package tests;

import static org.junit.Assert.*;

import domain.Account;
import domain.Transaction;
import org.junit.*;

import java.time.LocalDate;
import java.util.List;

public class AccountTest {

    private Account account;
    private Account anotherAccount;

    @Before
    public void setUp() {
        account = new Account();
        account.setId(1L);
        account.setBalance(1000.0);

        anotherAccount = new Account();
        anotherAccount.setId(2L);
        anotherAccount.setBalance(500.0);
    }

    @Test
    public void testDeposit() {
        double initialBalance = account.getBalance();
        double depositAmount = 500.0;

        boolean depositResult = account.deposit(depositAmount, anotherAccount);

        assertTrue(depositResult);
        assertEquals(initialBalance + depositAmount, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawal() {
        double initialBalance = account.getBalance();
        double withdrawAmount = 300.0;

        boolean withdrawResult = account.withdraw(withdrawAmount);

        assertTrue(withdrawResult);
        assertEquals(initialBalance - withdrawAmount, account.getBalance(), 0.001);
    }


    @Test
    public void testTransfer() {
        double senderInitialBalance = account.getBalance();
        double receiverInitialBalance = (anotherAccount != null) ? anotherAccount.getBalance() : 0.0; // Initial balance of the receiver, or 0 if anotherAccount is null
        double transferAmount = 200.0;

        // Check if the account to transfer to exists
        if (anotherAccount != null) {
            // Check if the sender has sufficient funds before initiating the transfer
            if (senderInitialBalance >= transferAmount) {
                boolean transferResult = account.transfer(transferAmount, anotherAccount, LocalDate.now());

                assertTrue(transferResult);
                assertEquals(senderInitialBalance - transferAmount, account.getBalance(), 0.001);
                assertEquals(receiverInitialBalance + transferAmount, anotherAccount.getBalance(), 0.001);
            } else {
                // If the sender doesn't have sufficient funds, the transfer should fail
                boolean transferResult = account.transfer(transferAmount, anotherAccount, LocalDate.now());

                assertFalse(transferResult);
                // Ensure that balances remain unchanged
                assertEquals(senderInitialBalance, account.getBalance(), 0.001);
                assertEquals(receiverInitialBalance, anotherAccount.getBalance(), 0.001);
            }
        } else {
            // If the account to transfer to doesn't exist, the transfer should fail
            boolean transferResult = account.transfer(transferAmount, null, LocalDate.now());

            assertFalse(transferResult);
            // Ensure that balances remain unchanged
            assertEquals(senderInitialBalance, account.getBalance(), 0.001);
            assertEquals(receiverInitialBalance, (anotherAccount != null) ? anotherAccount.getBalance() : 0.0, 0.001);
        }
    }


    @Test
    public void testShowHistory() {
        List<Transaction> transactionList = account.showHistory();

        assertNotNull(transactionList);
    }
}
