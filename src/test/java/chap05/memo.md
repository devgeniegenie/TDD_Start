### # org.junit.jupiter.api.<u>Assertions</u> 클래스가 제공하는 단언 메서드
###
>assertEzuals(expected, actual) : actual이 expected와 같은지 검사  
assertNotEzuals(expected, actual) : actual이 unexpected와 같지 않은지 검사  
assertSame(Object expected, Object actual) : 두 객체가 동일한 객체인지 검사  
assertNotSame(Object expected, Object actual) : 두 객체가 동일하지 않은 객체인지 검사  
assertTrue(boolean condition) : 값이 true인지 검사  
assertFalse(boolean condition) : 값이 false인지 검사  
assertNull(Object actual) : 값이 null인지 검사  
assertNotNull(Object actual) : 값이 null이 아닌지 검사  
fail() : 테스트를 실패 처리  
>>(익셉션 발생 유무 검새 메서드)  
assertThrows(Class<T> expectedType, Executeable executable) : executable을 실행한 결과로 지정한 타입의 익셉션이 발생하는지 검사  
assertDoesNotThrow(Executable executable) : executable을 실행한 결과로 익셉션이 발생하지 않는지 검사
>
>> assertAll : 일단 모든 검증을 실행하고 그중에 실패한 것이 있는지 확인할 때 사용  
>>> 예시  
> assertAll(  
> &nbsp;&nbsp; () -> assertEquals(3, 5/2),  
> &nbsp;&nbsp; () -> assertEquals(4, 8*2),  
> &nbsp;&nbsp; () -> assertEquals(7, 15/2)  
> );