package taxhelper.model;

import junit.framework.TestCase;
import taxhelper.TransactionProcessorTest;

import java.util.ArrayList;
import java.util.List;

public class TransactionsTest extends TestCase {

    public void testThatHaveThisLabel() throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        RawEntry rawEntry = TransactionProcessorTest.generateRawEntry();
        rawEntry.setLabels("Tradewinds");
        Transaction transaction = new Transaction(rawEntry);
        transactionList.add(transaction);

        RawEntry rawEntry2 = TransactionProcessorTest.generateRawEntry();
        rawEntry2.setLabels("Copper-Rental");
        Transaction transaction2 = new Transaction(rawEntry2);
        transactionList.add(transaction2);

        Transactions transactions = new Transactions(transactionList);
        transactions.print();
        assertTrue(transactions.getTransactionList().size()==2);

        // Testing label filter
        Transactions transactions1 = transactions.thatHaveThisLabel(Transaction.Label.TRADEWINDS);
        transactions1.print();
        assertTrue(transactions1.getTransactionList().size()==1);
    }
}
