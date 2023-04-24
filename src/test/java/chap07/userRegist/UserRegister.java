package chap07.userRegist;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;

    public UserRegister(StubWeakPasswordChecker passwordChecker, MemoryUserRepository userRepository) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
    }


    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
    }
}
