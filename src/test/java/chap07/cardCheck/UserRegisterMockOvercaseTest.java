package chap07.cardCheck;

import chap07.userRegist.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegisterMockOvercaseTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private UserRepository mockRepository = Mockito.mock(UserRepository.class);
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    private MemoryUserRepository fakeRepository = new MemoryUserRepository();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker,
                /*mockRepository,*/ fakeRepository,
                mockEmailNotifier);
    }

    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        /*ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        BDDMockito.then(mockRepository).should().save(captor.capture());

        User savedUser = captor.getValue();*/
        User savedUser = fakeRepository.findById("id");

        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }
}
