package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    /**
     * 매달 비용을 지불해야 사용할 수 있는 유료 서비스
     * 1. 서비스를 사용하려면 매달 1만원을 선물로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다
     * 2. 2개월 이상 요금을 납부할 수 있다.
     * 3. 10만원을 납부하면 서비스를 1년 제공한다.
     */

    /**
     * 쉬운 경우에서 어려운 경우로 진행
     * 예외적인 경우에서 정상인 경우로 진행
     */

    @DisplayName("만원 납부하면 한달 뒤가 만료일이 됨")
    @Test
    void pay10000thenCanUse1month() {
        assertExpiryDate(PayData.build()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000).build()
                , LocalDate.of(2019, 4, 1));

        assertExpiryDate(PayData.build()
                        .billingDate(LocalDate.of(2019, 5, 1))
                        .payAmount(10_000).build()
                , LocalDate.of(2019, 6, 1));
    }

    @DisplayName("납부일과 한달 뒤 날자가 같지 않음")
    @Test
    void pay10000thenCanUse1monthExceptionCase() {
        assertExpiryDate(PayData.build()
                        .billingDate(LocalDate.of(2019, 3, 31))
                        .payAmount(10_000).build()
                , LocalDate.of(2019, 4, 30));

        assertExpiryDate(PayData.build()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000).build()
                , LocalDate.of(2019, 2, 28));
    }

    @DisplayName("첫 납부일 일자와 만료일 납부일 일자가 같지 않음")
    @Test
    //첫 납부일이 2023-1-31 > 2023-2-28에 1만원 납부 > 2023-3-31이 만료일
    void test2() {
        PayData payData = PayData.build()
                .firstBillingDate(LocalDate.of(2023, 1, 31))
                .billingDate(LocalDate.of(2023, 2, 28))
                .payAmount(10000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2023, 3, 31));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, expiryDate);
    }
}
