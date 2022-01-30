package taxhelper.categories;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


// TODO re-evaluate this design
// A giant enum might be hard to manage
public enum BasicCategory implements Category {
    WEB_HOSTING("Web Hosting"),
    UTILITIES("Utilities"),
    UNCATEGORIZED("Uncategorized"),
    UNMAPPED("UNMAPPED"),
    GROCERIES("Groceries");

    private String category;

    BasicCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCategory() {
        return this.category;
    }
}
