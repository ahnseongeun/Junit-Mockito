package com.test_java.junit5.lecture6_반복적인테스트;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
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

    /**
     * CsvSource를 이용해서 여러개의 인자를 동시에 받기
     */
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @CsvSource({"10, '자바 스터디'", "20, '스프링 스터디'"})
    void parameterizedTest3(Integer limit, String name){
        Study study = new Study(limit,name);
        System.out.println(study);


    }

    /**
     * 여러개의 인자를 받아 올 때 객체로 만들어서 사용할 때
     */
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @CsvSource({"10, '자바 스터디'", "20, '스프링 스터디'"})
    void parameterizedTest4(ArgumentsAccessor argumentsAccessor){
        Study study = new Study(
                argumentsAccessor.getInteger(0),
                argumentsAccessor.getString(1)
        );
        System.out.println(study);
    }

    /**
     * 여러개의 인자를 받아 올 때 커스텀한 Argument를 만들어서 사용 할 때
     */
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @CsvSource({"10, '자바 스터디'", "20, '스프링 스터디'"})
    void parameterizedTest5(@AggregateWith(StudyAggregator.class) Study study){
        System.out.println(study);
    }

    /**
     * Aggregator 만들 때 제약 조건은 public 이거나 inner static 이여야 한다.
     */
    static class StudyAggregator implements ArgumentsAggregator{

        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
                throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0),accessor.getString(1));
        }
    }

    /**
     * ArgumentConverter는 하나의 argument에만 적용이 된다.
     */
    static class StudyConverter extends SimpleArgumentConverter{

        /**
         * Object source에는 ValueSource의 값들이 하나씩 들어온다.
         * @param source
         * @param targetType
         * @return
         * @throws ArgumentConversionException
         */
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
