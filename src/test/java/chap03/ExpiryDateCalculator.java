package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    //메서드 리팩토링 나중에 해보기
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10000;
        if (payData.getFirstBillingDate() != null) {
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {
                final int dayLenOfCandiMonth = YearMonth.from(candidateExp).lengthOfMonth();
                if (dayLenOfCandiMonth < dayOfFirstBilling) {
                    return candidateExp.withDayOfMonth(dayLenOfCandiMonth);
                }
                return candidateExp.withDayOfMonth(dayOfFirstBilling);
            } else {
                return candidateExp;
            }
        }else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }
}
