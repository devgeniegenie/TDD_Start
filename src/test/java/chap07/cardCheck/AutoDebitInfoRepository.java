package chap07.cardCheck;

public interface AutoDebitInfoRepository {
    void save(AutoDebitInfo info);
    AutoDebitInfo findOne(String userId);
}