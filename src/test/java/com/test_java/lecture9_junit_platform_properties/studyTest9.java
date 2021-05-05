package com.test_java.lecture9_junit_platform_properties;

import com.test_java.lecture5_커스텀태킹.FastTest;
import com.test_java.lecture5_커스텀태킹.SlowTest;
import com.test_java.lecture7_테스트인스턴스.Study;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * test의 resources를 설정한다.
 * File -> Project Structure -> module -> test resource
 * resources에서는
 * 테스트 인스턴스 라이프사이클 설정
 * 확장팩 자동 감지 기능
 * @Disabled 무시하고 실행하기
 * 테스트 이름 표기 전략 실행
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class studyTest9 {

    int value = 0;

    @FastTest
    //@DisplayName("스터디_만들기_fast")
    void create_new_study(){
        com.test_java.lecture7_테스트인스턴스.Study actual = new Study(++value);
        assertThat(actual.getLimit()).isGreaterThan(0);
        System.out.println("스터디 만들기 fast" + value);
        //객체의 해시코드보기
        System.out.println(this);
    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
        //fast에서 value값을 증가 시켜줬지만 증가되지 않은 0이 출력된다.
    void create_new_study_again(){
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
