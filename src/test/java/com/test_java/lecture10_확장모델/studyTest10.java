package com.test_java.lecture10_확장모델;

import com.test_java.lecture5_커스텀태킹.FastTest;
import com.test_java.lecture5_커스텀태킹.SlowTest;
import com.test_java.lecture7_테스트인스턴스.Study;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 확장 모델은 Extension으로 조절해야한다.
 * 확장팩 등록 방법
 * - 선언적인 등록 @ExtendWith
 * - 프로그래밍 등록 @RegisterExtension
 * - 자동 등록 자바 ServiceLoader 사용
 *
 * 확장팩 만드는 방법
 * - 테스트 실행 조건
 * - 테스트 인스턴스 팩토리
 * - 테스트 인스턴스 후 - 처리기
 * - 테스트 매개변수 리졸버
 *      - 어떻게 테스트코드에 DI를 해줄 것인가.
 * - 테스트 라이프사이클 콜백
 *      - 모든 테스트가 실행 되기 전후 처리리 * - 예외처리
 */

//@ExtendWith(FindSlowTestExtension.class) // 선언적인 등록 - 해당 class의 인스턴스를 커스텀 할 수 없다. EX) THRESHOLD의 값을 변경하고 싶을 때 못함
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class studyTest10 {

    int value = 0;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension =
            new FindSlowTestExtension(900L);

    @FastTest
    //@DisplayName("스터디_만들기_fast")
    void create_new_study() throws InterruptedException {
        Study actual = new Study(++value);
        assertThat(actual.getLimit()).isGreaterThan(0);
        System.out.println("스터디 만들기 fast" + value);
        //객체의 해시코드보기
        System.out.println(this);
        Thread.sleep(1005L);
    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
        //fast에서 value값을 증가 시켜줬지만 증가되지 않은 0이 출력된다.
    void create_new_study_again() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("스터디 만들기 slow" + value);
        System.out.println(this);
    }


    @BeforeAll
    static void beforeTestAll(){
        System.out.println("before all test");
    }

    @AfterAll
    static void afterTestAll(){
        System.out.println("after all test");
    }
}
