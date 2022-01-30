package taxhelper.categories;

import taxhelper.rental.RentalCategory;

import java.util.*;

public class CategoryFactory {

    public static Category mapStringToCategory(String string) {
        return CategoryFactory.get(string);
    }

    private static final Map<String, Category> lookup = new HashMap<>();

    static {
        // create a lookup table of a string to the related category
        for(Category c: EnumSet.allOf(BasicCategory.class)) {
            lookup.put(c.getCategory(), c);
        }

        for(Category c: EnumSet.allOf(RentalCategory.class)) {
            lookup.put(c.getCategory(), c);
        }
    }

    private static Category get(String category) {
        Category category1 = lookup.get(category);
        if (category1 == null) {
            return BasicCategory.UNMAPPED;
        }
        return category1;
    }

}
