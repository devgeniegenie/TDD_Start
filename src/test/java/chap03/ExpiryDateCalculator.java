package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {


    public LocalDate calculateExpiryDate(LocalDate billingDate, int pay) {
        return billingDate.plusMonths(1);
    }
}
