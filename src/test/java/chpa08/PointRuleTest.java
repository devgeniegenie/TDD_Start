package chpa08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointRuleTest {

    @DisplayName("만료전 GOLD등급은 130포인트")
    @Test
    void test1() {
        PointRule rule = new PointRule();
        Subscription s = new Subscription(LocalDate.of(2019, 5, 5), Grade.GOLD);
        Product p = new Product();
        p.setDefaultPoint(20);

        int point = rule.calculate(s, p, LocalDate.of(2019, 5, 1));
        assertEquals(130, point);
    }
}
