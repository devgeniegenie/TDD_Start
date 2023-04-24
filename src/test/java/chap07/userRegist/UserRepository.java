package chap07.userRegist;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
