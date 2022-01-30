package taxhelper.model;

import junit.framework.TestCase;
import taxhelper.TransactionProcessorTest;

public class TransactionTest extends TestCase {

    private static Transaction generateTestData() {
        RawEntry rawEntry = TransactionProcessorTest.generateRawEntry();
        rawEntry.setLabels("Tradewinds Copper-Rental Efficient-Software Questionable? Home Taxable-Income Charity Personal");
        rawEntry.setDate("9/3/2020");
        Transaction transaction = new Transaction(rawEntry);
        transaction.print();
        return transaction;
    }

    public void testParseYear() {
        assertEquals(2020,generateTestData().getYear());
    }

    public void testParseMonth() {
        assertEquals(9, generateTestData().getMonth());
    }

    public void testParseDay() {
        assertEquals(3, generateTestData().getDay());
    }

    public void testParseLabels() {
        System.out.println(generateTestData().getLabels());
        assertEquals(8, generateTestData().getLabels().size());
    }

    public void testGetValue() {
    }
}
