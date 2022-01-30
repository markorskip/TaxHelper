package taxhelper.auto;

import java.util.Calendar;

public class Trip {

    private double miles;
    private BIZ_OR_PERSONAL biz_or_personal;
    private Calendar date;

    public enum BIZ_OR_PERSONAL { BUSINESS , PERSONAL }

    public Trip(double miles, BIZ_OR_PERSONAL biz_or_personal, Calendar date) {
        this.miles = miles;
        this.biz_or_personal = biz_or_personal;
        this.date = date;
    }

}
