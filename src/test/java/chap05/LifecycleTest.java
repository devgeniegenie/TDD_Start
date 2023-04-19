package chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LifecycleTest {
    /**
     * 1. 테스트 메서드를 포함한 객체 생성
     * 2. (존재하면) @BeforeEach 애노테이션이 붙은 메서드 실행
     * 3. @Test 애노테이션이 붙은 메서드 실행
     * 4. (존재하면) @AfterEach 애노테이션이 붙은 메서드 실행
     *
     * @BeforeAll : 한 클래스의 모든 테스트 메서드가 실행되기 전에 특정 작업을 수행해야 할 때 사용
     *            : 클래스의 모든 테스트 메서드를 실행하기 전에 한 번 실행된다
     * @AfterAll
     *
     * @Disabled : 특정 테스트를 실행하지 않고 싶을 때
     */

    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    void a(){
        System.out.println("A");
    }

    @Test
    void b(){
        System.out.println("B");
    }

    @AfterEach
    void testDown() {
        System.out.println("testDown");
    }
}
