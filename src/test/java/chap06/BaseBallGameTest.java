package chap06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseBallGameTest {

    /**
     * 숫자야구게임 만들어보기
     * 1. 1~9까지의 숫자 3개를 선택
     * 2. 순서와 값 모두 일치하면 스트라이크 +1
     * 3. 값만 일치하면 볼+1
     * 4. 아무것도 일치하지 않으면 아웃 (no count)
     */

    @DisplayName("순서와 값 모두 불일치")
    @Test
    void perfectOut(){
        BaseBallGame game = new BaseBallGame("123");
        Score score = game.guess("456");
        assertEquals(0, score.balls());
        assertEquals(0, score.strikes());

        BaseBallGame game2 = new BaseBallGame("256");
        Score score2 = game2.guess("137");
        assertEquals(0, score2.balls());
        assertEquals(0, score2.strikes());
    }

    @DisplayName("순서만 모두 불일치")
    @Test
    void perfectNumberAllSortInconsistent(){
        BaseBallGame game = new BaseBallGame("123");
        Score score = game.guess("312");
        assertEquals(3, score.balls());
        assertEquals(0, score.strikes());

        BaseBallGame game2 = new BaseBallGame("957");
        Score score2 = game2.guess("795");
        assertEquals(3, score2.balls());
        assertEquals(0, score2.strikes());
    }

    @DisplayName("순서 모두 일치, 값 모두 일치")
    @Test
    void perfectNumberPerfectSort(){
        BaseBallGame game = new BaseBallGame("123");
        Score score = game.guess("123");
        assertEquals(0, score.balls());
        assertEquals(3, score.strikes());

        BaseBallGame game2 = new BaseBallGame("957");
        Score score2 = game2.guess("957");
        assertEquals(0, score2.balls());
        assertEquals(3, score2.strikes());
    }

    @DisplayName("순서 일부 일치, 값 일부 일치")
    @Test
    void someInconsistent(){
        BaseBallGame game = new BaseBallGame("123");
        Score score = game.guess("293");
        assertEquals(1, score.balls());
        assertEquals(1, score.strikes());

        BaseBallGame game2 = new BaseBallGame("957");
        Score score2 = game2.guess("927");
        assertEquals(0, score2.balls());
        assertEquals(2, score2.strikes());

        BaseBallGame game3 = new BaseBallGame("651");
        Score score3 = game3.guess("561");
        assertEquals(2, score3.balls());
        assertEquals(1, score3.strikes());
    }
}
