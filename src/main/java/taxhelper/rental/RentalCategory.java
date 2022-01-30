package taxhelper.rental;

import taxhelper.categories.Category;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RentalCategory implements Category {

    RENT_RECEIVED("Rent Received", Type.INCOME),
    RENTAL_COMMISSION("Rental Commission", Type.EXPENSE),
    CLEANING_AND_MAINTENANCE("Rental Clean Maint", Type.EXPENSE),
    RENTAL_INSURANCE("Rental Insurance", Type.EXPENSE),
    RENTAL_MGMT_FEE("Rental Mgmt Fee", Type.EXPENSE),
    RENTAL_MTG_INTEREST("Rental Mtg Interest", Type.EXPENSE),
    RENTAL_REPAIRS("Rental Repairs", Type.EXPENSE),
    RENTAL_TAXES("Rental Taxes", Type.EXPENSE),
    RENTAL_HOA("Rental HOA", Type.EXPENSE),
    RENTAL_ESCROW("Rental Escrow", Type.ESCROW),
    RENTAL_PRINCIPLE("Rental Principal", Type.EQUITY);

    private final String category;
    private final Type type;

    public enum Type { EXPENSE, INCOME, ESCROW, EQUITY}

    RentalCategory(String category, Type type) {
        this.category = category;
        this.type = type;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    public Type getType() {
        return this.type;
    }

    public static List<Category> getListByType(Type type) {
        return Arrays.stream(RentalCategory.values()).filter(c->c.getType().equals(type)).collect(Collectors.toList());
    }
}
