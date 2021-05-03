package com.test_java.lecture8_테스트순서;

import com.test_java.lecture5_커스텀태킹.FastTest;
import com.test_java.lecture5_커스텀태킹.SlowTest;
import com.test_java.lecture7_테스트인스턴스.Study;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 단위 테스트는 각각의 테스트가 독립적이기 때문에 순서에 큰 의미가 없다.
 * 하지만 경우의 따라서 원하는 순서가 있을 수도 있다.
 * ex) 시나리오 테스트 / 회원 가입 -> 로그인 -> 데이터 변경 같이 순서가 필요한 것
 */
//MethodOrder를 넘겨주면 되는데 Alphanumeric, OrderAnnotation, Random
//OrderAnnotation 어노테이션을 사용하면 Test에 순서를 부여 할 수 있다.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class studyTest8 {

    int value = 0;

    @Order(2)
    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study(){
        com.test_java.lecture7_테스트인스턴스.Study actual = new Study(++value);
        assertThat(actual.getLimit()).isGreaterThan(0);
        System.out.println("스터디 만들기 fast" + value);
        //객체의 해시코드보기
        System.out.println(this);
    }

    @Order(1)
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
