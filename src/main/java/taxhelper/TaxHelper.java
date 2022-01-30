package taxhelper;

import taxhelper.auto.MileageLog;
import taxhelper.model.Transactions;
import taxhelper.rental.ScheduleEHelper;
import taxhelper.smallbiz.ScheduleCHelper;

public interface TaxHelper {

    // Use the label
    ScheduleCHelper generateScheduleC(String businessTag, Transactions transactions, int taxYear);

    ScheduleEHelper generateScheduleE(String rentalTag, Transactions transactions, int taxYear);

}
