package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    /**
     * 암호 검사기
     * 1. 길이가 8글자 이상
     * 2. 0부터 9사이의 숫자를 포함
     * 3. 대문자 포함
     * 세 규칙을 모두 충족하면 암호는 강함
     * 2개 규칙을 충족하면 보통
     * 1개 이하의 규칙을 충족하면 약함
     */

    @DisplayName("모든 조건을 충족")
    @Test
    void meetsAllCriteria_Then_Strong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("asdf2!@4SA");
        assertEquals(PasswordStrength.STRONG, result);
        PasswordStrength result2 = meter.meter("as2!SB3Asdf");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족")
    @Test
    void meetsOtherCriteria_except_for_length_then_Normal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("a!s2f@D");
        assertEquals(PasswordStrength.NORMAL, result);
        PasswordStrength result2 = meter.meter("a!sAB1");
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우")
    @Test
    void meetsOtherCriteria_except_for_number_then_Normal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("sdf!$sdfAZX");
        assertEquals(PasswordStrength.NORMAL, result);
    }
}
