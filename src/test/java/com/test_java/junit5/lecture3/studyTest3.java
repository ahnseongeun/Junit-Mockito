package com.test_java.junit5.lecture3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class studyTest3 {

    @Test
    @DisplayName("create test")
    @EnabledOnOs(OS.WINDOWS) //운영체제에 따라서 테스트를 실행 시킬 수 있다.
    @DisabledOnOs(OS.MAC)
    @EnabledOnJre({JRE.JAVA_8,JRE.JAVA_11}) //특정 JRE 버전에서 테스트를 실행 할 수 있다.
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL") //테스트 전체에 환경 변수를 설정 하는 방법
    void create_new_study(){

        /**
         * 조건에 따라서 테스트를 실행하는 방법
         * 특정한 OS, 자바 버전, 환경 변수에 따라서 테스트를 실행할 경우를 정하는 방법
         */
        //OS 환경변수가 LOCAL에서만 테스트하기
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("local");
            Study actual1 = new Study(10);
            assertThat(actual1.getLimit()).isGreaterThan(0);
        });

        assumingThat("DEVELOPMENT".equalsIgnoreCase(test_env), () -> {
            System.out.println("development");
            Study actual2 = new Study(10);
            assertThat(actual2.getLimit()).isGreaterThan(0);
        });
    }
}
