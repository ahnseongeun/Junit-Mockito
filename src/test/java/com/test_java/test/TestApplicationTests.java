package com.test_java.test;

import com.test_java.junit5.lecture4_태깅과필터링.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        studyTest4.class
})
public class TestApplicationTests {

    @Test
    public void contextLoad(){

    }

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

}
