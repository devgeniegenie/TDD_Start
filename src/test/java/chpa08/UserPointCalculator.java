package chpa08;

import java.time.LocalDate;

import static chpa08.Grade.GOLD;

public class UserPointCalculator {
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    public int calculatePoint(User u) {
        Subscription s = subscriptionDao.selectByUser(u.getId());
        if (s == null) throw new NoSubscriptionException();
        Product p = productDao.selectById(s.getProductId());
        LocalDate now = LocalDate.now();
        int point = calculate(s, p, now); //테스트하고 싶은 코드만 별도로 분리해서 테스트. <-> 포인트 계산 기능 자체를 대역으로 변경하고 싶으면 세터 주입
        return point;
    }

    private int calculate(Subscription s, Product p, LocalDate now) {
        int point = 0;
        if (s.isFinished(now)) {
            point += p.getDefaultPoint();
        } else {
            point += p.getDefaultPoint() + 10;
        }
        if (s.getGrade() == GOLD) {
            point += 100;
        }
        return point;
    }
}