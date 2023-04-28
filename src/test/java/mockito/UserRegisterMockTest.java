package mockito;

import chap07.userRegist.EmailNotifier;
import chap07.userRegist.MemoryUserRepository;
import chap07.userRegist.StubWeakPasswordChecker;
import chap07.userRegist.UserRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, mockEmailNotifier);
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
}
