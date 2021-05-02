package com.test_java.lecture6_반복적인테스트;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class studyTest6 {

    /**
     * 반복적인 테스트를 할 때
     * @RepeatedTest 사용
     * RepetitionInfo라는 인자를 받을 수 있다.
     */
    @DisplayName("스터디 만들기 반복 테스트")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void reapTest(RepetitionInfo repetitionInfo){
        System.out.println("repeat test");
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

    /**
     * 다른 값을 가지고 테스트 하고 싶을 때 사용하는 방법
     */
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    void parameterizedTest(String message){
        System.out.println(message);
    }


    @Test
    @DisplayName("스터디 만들기 fast")
    public void create_new_study(){
        System.out.println("create fast");

    }

    @Test
    @DisplayName("스터디 만들기 slow")
    public void create_new2_study(){

        System.out.println("create slow");
    }

}
