package mockito;

import chap07.userRegist.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker,
                fakeRepository,
                mockEmailNotifier);
    }

    /**
     * 인자 캡쳐
     * 모의 객체를 호출할 때 사용한 인자를 검증해야 할 때
     */
    @DisplayName("가입하면 메일을 전송")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        /*ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class); //String타입의 인자를 보관할수 있는 ArgumentCaptor 생성
        then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture()); //인자로 전달할 때는 captor.capture() 메서드 전달*/

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.verify(mockEmailNotifier).sendRegisterEmail(captor.capture());

        assertEquals("email@email.com", captor.getValue());
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");
        then(mockPasswordChecker).should().checkPasswordWeak(anyString());
    }
}
