package chap07.cardCheck;

public class AutoDebitReq {
    private String userId;
    private String cardNumber;

    public AutoDebitReq(String userId, String cardNumber) {
        this.userId = userId;
        this.cardNumber = cardNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
