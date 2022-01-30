package taxhelper;

import org.easybatch.core.filter.HeaderRecordFilter;
import org.easybatch.core.job.Job;
import org.easybatch.core.job.JobBuilder;
import org.easybatch.core.job.JobExecutor;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.mapper.RecordMapper;
import org.easybatch.core.writer.CollectionRecordWriter;
import org.easybatch.extensions.apache.common.csv.ApacheCommonCsvRecordMapper;
import org.easybatch.flatfile.FlatFileRecordReader;
import taxhelper.model.RawEntry;
import taxhelper.model.Transaction;
import taxhelper.model.Transactions;
import taxhelper.rental.ScheduleEHelper;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tax Helper");
        System.out.println("This tool with help you calculate your different tax forms");
        String path = "/Users/marksorenson/IdeaProjects/TaxHelper/src/main/resources/transactions(3).csv";

        Transactions transactions = Ingest(path);

        //transactions.printInfo();

        //transactions.thatAreInThisYear(2020).printInfo();

        copperRentalCompute(transactions);
    }

    public static void copperRentalCompute(Transactions transactions) {
        Transactions copperRentalTransactions = transactions.thatAreInThisYear(2020).thatHaveThisLabel(Transaction.Label.COPPER_RENTAL);
        copperRentalTransactions.printInfo();
        ScheduleEHelper scheduleEHelper = new ScheduleEHelper(copperRentalTransactions, new BigDecimal(5200));
        scheduleEHelper.print();

    }


    public static RecordMapper getMapper() {
        ApacheCommonCsvRecordMapper<RawEntry> mapper =
                new ApacheCommonCsvRecordMapper<>(RawEntry.class,
                        "date",
                        "description",
                        "originalDescription",
                        "amount",
                        "transactionType",
                        "category",
                        "accountName",
                        "labels",
                        "notes");

        mapper.setTrim(true);
        mapper.setDelimiter(',');
        mapper.setQuote('"');
        return mapper;
    }


    // Ingest a workbook and create a List<EntrieS>
    public static Transactions Ingest(String path) {
        Path inputFile = Paths.get(path);
        List<Transaction> transactionList = new ArrayList<>();

        Job job = new JobBuilder()
                .errorThreshold(0)
                .reader(new FlatFileRecordReader(inputFile))
                .filter(new HeaderRecordFilter())
                .mapper(getMapper())
                .processor(new TransactionProcessor())
                .writer(new CollectionRecordWriter(transactionList))
                .build();

        JobExecutor jobExecutor = new JobExecutor();
        JobReport report = jobExecutor.execute(job);
        jobExecutor.shutdown();
        printJobSummary(report);

        return new Transactions(transactionList);

    }

    private static void printJobSummary (JobReport report) {
        System.out.println("Ingest file errors count:" + report.getMetrics().getErrorCount());
        System.out.println("Read count: " + report.getMetrics().getReadCount());
        System.out.println("Write count: " + report.getMetrics().getWriteCount());
        System.out.println("---------------------");
        System.out.println();
    }


}
