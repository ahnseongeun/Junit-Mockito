package com.test_java.lecture5_커스텀태킹;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 애노테이션을 사용한 코드는 이 애노테이션 정보를 런타임까지 유지 해야한다.
@Retention(RetentionPolicy.RUNTIME)
//타켓은 메소드이다.
@Target(ElementType.METHOD)
@Test
@Tag("fast")
public @interface FastTest {

}
