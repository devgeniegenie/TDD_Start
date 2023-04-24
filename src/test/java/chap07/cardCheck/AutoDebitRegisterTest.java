package chap07.cardCheck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static chap07.cardCheck.CardValidity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTest {

    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
//    private StubAutoDebitInfoRepository stubRepository;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
//        stubRepository = new StubAutoDebitInfoRepository();
        repository = new MemoryAutoDebitInfoRepository();
//        register = new AutoDebitRegister(stubValidator, stubRepository);
        register = new AutoDebitRegister(stubValidator, repository);
    }

    @Test
    void invalidCard() {
        stubValidator.setInvalidNo("1234551231");

        AutoDebitReq req = new AutoDebitReq("user1", "1234551231");
        RegisterResult result = register.register(req);

        assertEquals(INVALID, result.getValidity());
    }

    @Test
    void theftCard() {
        stubValidator.setTheftNo("1234551231");

        AutoDebitReq req = new AutoDebitReq("user1", "1234551231");
        RegisterResult result = register.register(req);

        assertEquals(THEFT, result.getValidity());
    }

    @Test
    void alreadyRegistered_InfoUpdated() {
        repository.save(new AutoDebitInfo("user1", "1234551231", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("user1", "222222");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("222222", saved.getCardNumber());
    }

    @Test
    void notYetRegistered_newInfoRegisterd() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234551231");
        /*RegisterResult result = */this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("1234551231", saved.getCardNumber());
    }

}
