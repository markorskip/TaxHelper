package taxhelper;

import junit.framework.TestCase;
import org.easybatch.core.record.GenericRecord;
import org.easybatch.core.record.Header;
import org.easybatch.core.record.Record;
import org.junit.Test;
import taxhelper.categories.BasicCategory;
import taxhelper.model.RawEntry;
import taxhelper.model.Transaction;

import java.util.Date;

public class TransactionProcessorTest extends TestCase {

    public static RawEntry generateRawEntry() {
        RawEntry rawEntry = new RawEntry();
        rawEntry.setAmount("100");
        rawEntry.setTransactionType("debit");
        rawEntry.setDate("11/11/2020");
        return rawEntry;
    }


    @Test
    public void testConvertingRawEntryIntoTransacation() throws Exception {
        Header header = new Header(1L,"SOURCE", new Date());
        GenericRecord record = new GenericRecord(header, generateRawEntry());

        TransactionProcessor processor = new TransactionProcessor();
        Record returnRecord = processor.processRecord(record);

        Transaction transaction = (Transaction) returnRecord.getPayload();
        assertTrue(transaction.getCategory() == BasicCategory.UNCATEGORIZED);
        System.out.println(returnRecord);

    }

}
