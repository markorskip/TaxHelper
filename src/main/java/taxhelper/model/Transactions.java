package taxhelper.model;

import lombok.Getter;
import taxhelper.categories.BasicCategory;
import taxhelper.categories.Category;
import taxhelper.util.HelperUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Transactions {

    @Getter
    private List<Transaction> transactionList;

    public Transactions(List<Transaction> entries) {
        this.transactionList = entries;
    }

    public Transactions thatAreInThisYear(int year) {
        List<Transaction> returnEntries = transactionList.stream().filter(e->e.getYear() == year).collect(Collectors.toList());
        return new Transactions(returnEntries);
    }

    public Transactions thatHaveThisLabel(Transaction.Label label) {
        List<Transaction> result = new ArrayList<>();
        transactionList.forEach(transaction ->{
                if (transaction.getLabels().contains(label)) {
                    result.add(transaction);
                }
        });
        return new Transactions(result);
    }

    public BigDecimal getTotal() {
        BigDecimal result = new BigDecimal(0);
        for (Transaction transaction : this.transactionList) {
            result = result.add(transaction.getValue());
        }
        return result;
    }

    protected Transactions thatAreUncategorized() {
        return new Transactions(this.transactionList.stream().filter(t -> t.getCategory().equals(BasicCategory.UNCATEGORIZED)).collect(Collectors.toList()));
    }

    public Transactions thatHaveThisCategory(Category category) {
        return new Transactions(this.transactionList.stream().filter(t -> t.getCategory().equals(category)).collect(Collectors.toList()));
    }

    public void print() {
        System.out.println("Printing Transactions");
        System.out.println("Transaction count: " + transactionList.size());
        for (Transaction transaction : transactionList) {
            System.out.println(transaction);
        }
    }

    public void printInfo() {
        System.out.println("Printing Transactions");
        System.out.println("Transaction count: " + transactionList.size());
        System.out.println("Transaction total: " + getTotal());

        if (transactionList.size() > 5) {
            for (int i = 0; i < 5; i++) {
                transactionList.get(i).print();
            }
        }
    }

    public void printUncategorizedCategoriesWithCount() {
        Transactions transactions = this.thatAreUncategorized();
        Map<String, Integer> map = new HashMap<>();
        transactions.getTransactionList().forEach(t-> {
            String rawCategory = t.getRawEntry().getCategory();
            if (map.get(rawCategory) == null) {
                map.put(rawCategory, 1);
            } else {
                Integer count = map.get(rawCategory);
                map.put(rawCategory, count + 1);
            }
        });

        Map<String, Integer> sortedMap = HelperUtil.getSortedMapByValues(map);

        for (String key : sortedMap.keySet()) {
            System.out.println(key + ": " + sortedMap.get(key));
        }
    }



    public void exportToCsv() {

        //TODO implement this
    }

}
