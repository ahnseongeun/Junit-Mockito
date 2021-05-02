package com.test_java.lecture4_태깅과필터링;

import org.junit.jupiter.api.*;
import org.springframework.context.annotation.Profile;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 태킹과 필터링
 * 테스트 그룹을 만들고 원하는 테스트 그룹만 테스트를 실행할 수 있는 기능
 * @Tag
 * 모듈 , 단위, 통합 등 여러가지 조건으로 태그를 붙혀서 사용할 수 있다.
 */


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class studyTest4 {

    /**
     * build.gradle에 tag를 통해서 test진행 가능
     */
    @Test
    @DisplayName("스터디 만들기 fast")
    @Tag("fast") // 실행하는데 빨라서 로컬에서 테스트하기 적합
    public void create_new_study(){
        System.out.println("create fast");

    }

    @Test
    @DisplayName("스터디 만들기 slow")
    @Tag("slow") // 실행하는데 느려서 로컬에서 테스트하는데 부적합 / CI 환경에서만 테스트
    public void create_new2_study(){

        System.out.println("create slow");
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
