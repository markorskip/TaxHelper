package taxhelper.model;

import lombok.Getter;
import lombok.ToString;
import taxhelper.categories.Category;
import taxhelper.categories.CategoryFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@ToString
public class Transaction {

    private final String description;
    private final String originalDescription;
    private final BigDecimal amount;
    private final TransactionType transactionType;
    private final Category category;
    private final String accountName;
    private final List<Label> labels;
    private final String notes;
    private final int year;
    private final int month;
    private final int day;
    private final RawEntry rawEntry;

    public enum TransactionType { DEBIT, CREDIT };

    public enum Label { TRADEWINDS, EFFICIENT_SOFTWARE, PERSONAL, QUESTIONABLE, COPPER_RENTAL, HOME, AUTO, CHARITY, TAXABLE_INCOME, NOLABEL }

    public Transaction (RawEntry rawEntry) {
        this.description = rawEntry.getDescription();
        this.originalDescription = rawEntry.getOriginalDescription();
        this.amount = new BigDecimal(rawEntry.getAmount());
        this.transactionType = TransactionType.valueOf(rawEntry.getTransactionType().toUpperCase());
        this.labels = parseLabels(rawEntry.getLabels());
        this.category = CategoryFactory.mapStringToCategory(rawEntry.getCategory());
        this.accountName = rawEntry.getAccountName();
        this.notes = rawEntry.getNotes();
        this.year = parseYear(rawEntry.getDate());
        this.month = parseMonth(rawEntry.getDate());
        this.day = parseDay(rawEntry.getDate());
        this.rawEntry = rawEntry;
    }

    private String[] parseDate(String date) {
        return date.split("/");
    }

    public int parseYear(String date) {
        return Integer.parseInt(parseDate(date)[2]);
    }

    public int parseMonth(String date) {
        return Integer.parseInt(parseDate(date)[0]);
    }

    public int parseDay(String date) {
        return Integer.parseInt(parseDate(date)[1]);
    }


    public List<Label> parseLabels(String labels) {
        // TODO move mapping into labels
        HashMap<String, Label> labelMapping = new HashMap<>();
        labelMapping.put("Copper-Rental", Label.COPPER_RENTAL);
        labelMapping.put("Tradewinds", Label.TRADEWINDS);
        labelMapping.put("Efficient-Software", Label.EFFICIENT_SOFTWARE);
        labelMapping.put("Questionable?", Label.QUESTIONABLE);
        labelMapping.put("Home", Label.HOME);
        labelMapping.put("Taxable-Income", Label.TAXABLE_INCOME);
        labelMapping.put("Charity", Label.CHARITY);
        labelMapping.put("Personal", Label.PERSONAL);
        labelMapping.put(null, Label.NOLABEL);

        List<Label> labelList = new ArrayList<>();
        if (labels != null) {
            String[] labelsArr = labels.split(" ");
            for (String label : labelsArr) {
                String str = label.trim();
                Label thisLabel = labelMapping.get(str);
                if (thisLabel != null) labelList.add(thisLabel);
            }
        }
        if (labelList.size() == 0) labelList.add(Label.NOLABEL);

        return labelList;
    }

    BigDecimal getValue() {
        if (this.transactionType.equals(TransactionType.CREDIT)) {
            return this.amount;
        }
        if (this.transactionType.equals(TransactionType.DEBIT)) {
            return this.amount.negate();
        }
        throw new NullPointerException("CREDIT OR DEBIT not definited");

    }

    public void print() {
        System.out.println(this);
    }
}
