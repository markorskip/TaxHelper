package taxhelper.rental;

import taxhelper.categories.Category;
import taxhelper.model.Transactions;

import java.math.BigDecimal;
import java.util.List;

public class ScheduleEHelper {

    private BigDecimal depreciation;
    private Transactions transactions;
    private String name;

    List<Category> expenseCategories;
    List<Category> incomeCategories;
    List<Category> equityCategories;
    List<Category> escrowCategories;


    public ScheduleEHelper(Transactions transactions, BigDecimal depreciation) {
        this.name = "Copper Rental"; // TODO constructor for this
        this.transactions = transactions;
        this.depreciation = depreciation;

        incomeCategories = RentalCategory.getListByType(RentalCategory.Type.INCOME);
        expenseCategories = RentalCategory.getListByType(RentalCategory.Type.EXPENSE);
        escrowCategories = RentalCategory.getListByType(RentalCategory.Type.ESCROW);
        equityCategories = RentalCategory.getListByType(RentalCategory.Type.EQUITY);
    }

    public void print() {
        System.out.println("Rental Property: " + name);


        System.out.println("RENTAL INCOME");

        BigDecimal income = calculateAndDisplayCategory(incomeCategories);

        System.out.println("RENTAL EXPENSES");
        BigDecimal expenses = calculateAndDisplayCategory(expenseCategories);
        System.out.println("DEPRECIATION: " + this.depreciation.negate());
        System.out.println();

        System.out.println("RENTAL EQUITY");
        BigDecimal equity = calculateAndDisplayCategory(equityCategories);

        System.out.println("ESCROW CONTRIBUTION");
        calculateAndDisplayCategory(escrowCategories);

        System.out.println("TAX INCOME OR LOSS");
        System.out.println(income.add(expenses).add(depreciation.negate()));

        System.out.println("NET CASH FLOW");
        System.out.println(income.add(expenses).add(equity));

        System.out.println("ACTUAL PROFIT OR LOSS");
        System.out.println(income.add(expenses).add(equity.negate()));

        System.out.println("NOTES");
        System.out.println("Use count sizes to ensure accuracy. For example, there should be 12 rents received if it was rented every month");
    }

    private BigDecimal calculateAndDisplayCategory(List<Category> categoryList) {
        BigDecimal total = new BigDecimal(0);
        for (Category category : categoryList) {
            Transactions trs = transactions.thatHaveThisCategory(category);
            System.out.print(category.getCategory() + ": ");
            System.out.print(trs.getTotal());
            System.out.print(" | Count: " + trs.getTransactionList().size());
            System.out.println();
            total = total.add(trs.getTotal());
        }
        System.out.println("CATEGORY TOTAL: " + total);
        System.out.println();
        return total;
    }
}
