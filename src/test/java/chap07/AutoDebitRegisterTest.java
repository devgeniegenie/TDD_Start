package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chap07.CardValidity.THEFT;
import static chap07.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTest {

    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() {
        //업체에서 받은 테스트용 유효한 카드번호라고 가정
        AutoDebitReq req = new AutoDebitReq("user1", "546489189489499");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        //업체에서 받은 테스트용 유효한 카드번호라고 가정
        AutoDebitReq req = new AutoDebitReq("user1", "1894521894894984");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT, result.getValidity());
    }

}
