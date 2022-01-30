package taxhelper.home;

import taxhelper.categories.Category;
import taxhelper.rental.RentalCategory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum HomeCategory implements Category {


    INSURANCE("Home Insurance"),
    RENT("Rent"),
    REPAIRS_AND_MAINTENANCE

    private final String category;

    HomeCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

}
