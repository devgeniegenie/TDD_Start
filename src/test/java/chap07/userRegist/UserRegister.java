package chap07.userRegist;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private SpyEmailNotifier spyEmailNotifier;

    public UserRegister(StubWeakPasswordChecker passwordChecker, MemoryUserRepository userRepository, SpyEmailNotifier spyEmailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.spyEmailNotifier = spyEmailNotifier;
    }


    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User(id, pw, email));

        spyEmailNotifier.sendRegisterEmail(email);
    }
}
