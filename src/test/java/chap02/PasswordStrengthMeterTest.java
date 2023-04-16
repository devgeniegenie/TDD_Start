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

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStrength) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStrength, result);
    }

    @DisplayName("모든 조건을 충족")
    @Test
    void meetsAllCriteria_then_Strong() {
        assertStrength("asdf2!@4SA", PasswordStrength.STRONG);
        assertStrength("as2!SB3Asdf", PasswordStrength.STRONG);
    }

    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족")
    @Test
    void meetsOtherCriteria_except_for_length_then_Normal() {
        assertStrength("a2fw@D", PasswordStrength.NORMAL);
        assertStrength("a!sAB1", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우")
    @Test
    void meetsOtherCriteria_except_for_number_then_Normal() {
        assertStrength("sdf!$sdfAZX", PasswordStrength.NORMAL);
        assertStrength("kuy#$daAZX", PasswordStrength.NORMAL);
    }

    @DisplayName("입력이 null인 경우")
    @Test
    void nullInput_then_invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @DisplayName("입력이 empty인 경우")
    @Test
    void emptyInput_then_invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하는 경우")
    @Test
    void meetsOtherCriteria_except_for_Uppercase_then_normal() {
        assertStrength("sdf537ydf@2!", PasswordStrength.NORMAL);
    }

    @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우")
    @Test
    void meetsOnlyLengthCriteria_then_Weak() {
        assertStrength("qawadsafefgw", PasswordStrength.WEAK);
    }

    @DisplayName("숫자 포함 조건만 충족하는 경우")
    @Test
    void meetsOnlyNumCriteria_then_Weak() {
        assertStrength("15438", PasswordStrength.WEAK);
    }

    @DisplayName("대문자 포함 조건만 충족하는 경우")
    @Test
    void meetsOnlyUpperCriteria_then_Weak() {
        assertStrength("AXGEA", PasswordStrength.WEAK);
    }

    @DisplayName("아무 조건도 충족하지 않는 경우")
    @Test
    void meetsNoCriteria_then_Weak() {
        assertStrength("ab", PasswordStrength.WEAK);
    }

}
