package taxhelper.categories;

import junit.framework.TestCase;
import taxhelper.rental.RentalCategory;

import java.util.List;

public class RentalCategoryTest extends TestCase {

    public void testGetListByType() {
        List<Category> categoryList = RentalCategory.getListByType(RentalCategory.Type.EXPENSE);

        categoryList.forEach(t-> System.out.println(t));
        assertTrue(categoryList.size() > 2);
    }
}
