package chap06;

public class BaseBallGame {
    private String answer;

    public BaseBallGame(String result) {
        this.answer = result;
    }

    public Score guess(String input) {
        return new Score(answer, input);
    }
}
