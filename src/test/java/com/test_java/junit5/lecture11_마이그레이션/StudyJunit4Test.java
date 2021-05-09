package com.test_java.junit5.lecture11_마이그레이션;


import org.junit.Before;
import org.junit.Test;

/**
 * junit4는 public class가 아니면 실행이 안된다.
 * Junit-vintage-engine을 의존성으로 추가해야만 juint4로 작성된 테스트를 실행 할 수 있다.
 * Junit5는 platform, jupitor, vintage를 가지고 있어 junit4도 호환이 가능하다.
 * 즉, junit4와 junit5를 동시에 작성이 가능한데 전체 실행을 하면 jupitor로 junit5를 실행하고
 * vintage로 junit4를 실행한다.
 * 하지만 제약사항이 존재한다.
 * 1. Junit4에서 사용하던 @Rule은 기본적으로 지원하지 않지만 junit-jupiter-migrationsupport 모듈이
 * 제공하는 @EnableRuleMigrationSupport를 사용하면 다음 타입의 Rule을 지원한다.
 * - ExternalResource
 * - Verifier
 * - ExpectedException
 *
 *
 * Junit4 -> Junit5 마이그레이션
 * -> @RunWith 제외
 *      -> SpringBootTest에 이미 @ExtendWith가 존재한다.
 * -> @Ignore
 *      -> @disabled
 * -> @Before대신에 @BeforeEach
 */

public class StudyJunit4Test {

    @Before
    public void before(){
        System.out.println("before");
    }

    @Test
    public void createTest(){
        System.out.println("test");
    }

    @Test
    public void createTest2(){
        System.out.println("test2");
    }
}