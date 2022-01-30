package taxhelper.categories;

import org.junit.Assert;
import org.junit.Test;
import taxhelper.rental.RentalCategory;

public class CategoryFactoryTest {

    @Test
    public void testMappingFromString() {
        Category category = CategoryFactory.mapStringToCategory("Rent Received");
        System.out.println(category);
        Assert.assertEquals(category, RentalCategory.RENT_RECEIVED);
    }

}
