package taxhelper;

import org.easybatch.core.processor.RecordProcessor;
import org.easybatch.core.record.GenericRecord;
import org.easybatch.core.record.Header;
import org.easybatch.core.record.Record;
import taxhelper.model.RawEntry;
import taxhelper.model.Transaction;

public class TransactionProcessor implements RecordProcessor {
    @Override
    public Record processRecord(Record record) throws Exception {
        if (record.getPayload() instanceof RawEntry) {
            Header header = record.getHeader();
            Transaction transaction = new Transaction((RawEntry) record.getPayload());
            Record record1 = new GenericRecord(header, transaction);
            return record1;
        }
        return null;
    }
}
