package taxhelper.home;

// For calculating home office deduction

import lombok.Data;

@Data
public class Form8829Helper {

    private int areaUsedForBusiness;
    private int totalAreaOfHome;

    private double getBusinessPercentage() {

        return this.areaUsedForBusiness / this.totalAreaOfHome;
    }


}
