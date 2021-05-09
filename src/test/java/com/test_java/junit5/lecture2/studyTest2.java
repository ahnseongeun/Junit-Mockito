package com.test_java.junit5.lecture2;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */


//전체 테스트 이름에 적용하기 -> 언더바를 공백으로 치환해준다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class studyTest2 {

    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31") //하나 만 적용, 이모지도 사용 가능하다.
    public void create_new_study(){
        Study study = new Study();
        assertNotNull(study);  // null확인 테스트

        /**
         * 3가지 방법
         * 1. message 바로 삽입
         * 2. Supplier를 통해서 오버라이딩을 이용한 메시지 출력
         * 3. 람다식으로 통해서 생략형
         * 왜 message로 바로 삽입하지 않고 람다식으로 하는가 문자열 연산이 있을 경우 람다식으로 만들어주면
         * 최대한 문자열 연산을 피할수 있다. -> 최대한 필요한 순간에만 할 수 있다 ex) 테스트가 실패 했을 경우에만 한다.
         */
        //왼쪽 기대값 , 오른쪽 실제값
        assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다");
        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "test에 실패 했습니다.";
            }
        });
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "test에 실패 했습니다.");

        /**
         * assertTrue
         * 조건이 참인지 확인
         */
        Study study1 = new Study(10);
        assertTrue(1 < 2);
        assertTrue(study1.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.");

        /**
         * assertAll
         * 중간에 테스트를 통과하지 못하면 다음 테스트들은 실행되지 않지만
         * assertAll로 묶어 준다면 해당 테스트들은 모두 실행 한다.
         * assertAll <- Executable <- functionalInterFace
         * functionalInterface는 메소르닥 하나만 있는 인터페이스 이다.
         */
        assertAll(
                () -> assertNotNull(study.getStatus()),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다"),
                () -> assertTrue(1 < 2)
        );
        System.out.println("create");
    }

    @Test
    //@Disabled disable 어노테이션을 작성하면 테스트를 하지 않는다.
    public void create_test_1(){
        System.out.println("create1");
    }

    @Test
    public void create_test_2(){

        /**
         * assertThrows 예외처리 테스트
         */
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = illegalArgumentException.getMessage();
        assertEquals(message, "limit은 0보다 커야 합니다.");

        /**
         * assertTimeout
         */
        //sleep을 다 기다리고 테스트가 끝난다.
        assertTimeout(Duration.ofMillis(10), () -> {
            new Study(10);
            Thread.sleep(5);
        });

        //제한 시간이 초과되면 바로 테스트를 종료 한다.
        //테스트를 실행 할 때 { } 블록은 별도의 스레드로 실행 하기 때문에 사용에 조심 해야 한다.
        //ThreadLocal을 사용하는데 ThreadLocal은 다른 스레드에서 공유가 안되는데 스프링에서 만든 트랜잭션이 제대로 적용 안 될 수 있다.
        //트랜잭션을 가지고 있는 스레드라서 테스트 종료시 롤백이 안될 수도 있다.
        //스레드와 관련없는 코드를 사용하면 괜찮지만 스레드와 관련이 있을 경우 사용에 주의 해야 한다.
        assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            new Study(10);
            Thread.sleep(9);
        });
    }

    /**
     * @Test 전에 딱 한번만 사용하며 , 반드시 static method를 사용 해야한다. return 타입도 안된다.
     */
    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll");
    }

    /**
     * @Test 후에 딱 한번만 사용하며, 반드시 static method를 사용 해야 한다.
     */
    @AfterAll
    static void afterAll(){
        System.out.println("afterAll");
    }

    /**
     * 각각의 @Test가 한번 실행 되기 전에 실행 된다. static일 필요는 없다.
     */
    @BeforeEach
    void beforeEach(){
        System.out.println("beforeEach");
    }

    /**
     * 각각의 @Test가 한번 실행 된 후에 실행 된다. static일 필요는 없다.
     */
    @AfterEach
    void afterEach(){
        System.out.println("afterEach");
    }

}