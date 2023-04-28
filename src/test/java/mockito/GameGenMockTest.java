package mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {
    /**
     * 인자 매칭 처리 예제
     */
    @Test
    void mockTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        //BDDMockito.given() 메서드를 이용해서 모의 객체의 메서드가 특정값을 리턴하도록 설정
        //genMock.generate(GameLevel.EASY) 메서드가 불리면 "123"을 리턴하라고 설정
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @Test
    void voidMethodWillThrowTest() {
        List<String> mockList = mock(List.class);
        //BDDMockito.willThrow() 메서드로 익셉션을 발생하게 설정
        willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        assertThrows(
                UnsupportedOperationException.class,
                () -> mockList.clear()
        );
    }

    @Test
    void anyMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("456"); //임의의 값에 일치하도록 설정

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals("456", num2);
    }

    @Test
    void mixAnyAndEq() {
        /**
         * anyInt(), anyBoolean() ...
         * anyString()
         * any()
         * anyList(), anyMap(), anyCollection() ...
         * matches(String), matches(Pattern)...
         * eq(값)
         */
        List<String> mockList = mock(List.class);
        given(mockList.get(anyInt())).willReturn("test");
        String temp = mockList.get(4);
        assertEquals("test", temp);

        given(mockList.set(anyInt(), eq("123"))).willReturn("456");
        String old = mockList.set(8, "123");
        assertEquals("456", old);
    }

    /**
     * 행위 검증 예제
     */
    @Test
    void init() {
        /**
         * only(), times(int), never(), atLeast(int), atLeastOnce() = atLeast(1), atMost(int)
         */
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock).should().generate(GameLevel.EASY); //generate()가 GameLevel.EASY로 호출되었는지 검증
        then(genMock).should().generate(any()); //generate(any) 메서드가 불렸는지 검증
        then(genMock).should(only()).generate(any()); //generate(any) 메서드가 한번 호출되었는지 검증
    }
}
