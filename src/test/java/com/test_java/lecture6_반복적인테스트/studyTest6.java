package com.test_java.lecture6_반복적인테스트;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @EmptySource //빈 값 넣어 주기
    @NullSource //Null 값 넣어 주기
    //@NullAndEmptySource 빈 값과 Null 값 동시에 넣어주기
    void parameterizedTest(String message){
        System.out.println(message);
    }

    /**
     * value int 사용
     * @param message
     */
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 30})
    void parameterizedTest2(Integer message){
        System.out.println(message);
    }

    /**
     * converter를 사용해서 ValueSource로 받은 int를 객체로 변환이 가능하다.
     * @param study
     */
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 30})
    void parameterizedTest2(@ConvertWith(StudyConverter.class) Study study){
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter{

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType,"Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    /**
     * CsvSource를 사용하면 다양한 타입의 인자값들을 넘겨줄 수 있다.
     */
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 30})
    void parameterizedTest3(Integer message){
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
