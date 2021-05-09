package com.test_java.junit5.lecture7_테스트인스턴스;

import com.test_java.junit5.lecture5_커스텀태킹.FastTest;
import com.test_java.junit5.lecture5_커스텀태킹.SlowTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Junit5부터는 필드를 공유하는 방법
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class studyTest7 {

    /**
     * BeforeAll이나 AfterAll은 반드시 static 메소드여야 한다.
     * 하지만 @TestInstance(TestInstance.Lifecycle.PER_CLASS)를 사용하게 되면
     * 더 이상 static일 필요가 없다.
     */
    @BeforeAll
    void beforeTestAll(){
        System.out.println("before all test");
    }

    @AfterAll
    void afterTestAll(){
        System.out.println("after all test");
    }

    int value = 0;

    /**
     * System.out.println(this)를 하게 되면
     * 각 Test마다 Hash값이 다른데 이 말의 의미는 Test마다 새로운 studyTest7의 객체를 생성해서 테스트를 진행한다는 의미이다.
     * -> Test마다 의존성을 없애기 위해서이다.
     * Junit5부터는 필드를 공유하는 방법이 나왔다.
     * -> @TestInstance(TestInstance.Lifecycle.PER_CLASS)
     * -> 메소드가 같은 해시 값을 가지는 것을 볼 수 있다.
     */
    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study(){
        Study actual = new Study(++value);
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
}
