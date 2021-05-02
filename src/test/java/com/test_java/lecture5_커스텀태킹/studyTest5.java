package com.test_java.lecture5_커스텀태킹;

import org.junit.jupiter.api.*;

/**
 * 커스텀 태킹
 */
public class studyTest5 {

    /**
     * build.gradle에 tag를 통해서 test진행 가능
     */
    @FastTest
    public void create_new_study(){
        System.out.println("create fast");

    }

    @SlowTest
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
