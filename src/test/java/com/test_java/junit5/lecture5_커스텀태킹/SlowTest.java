package com.test_java.junit5.lecture5_커스텀태킹;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//타켓은 메소드이다.
@Target(ElementType.METHOD)
@Test
@Tag("slow")
public @interface SlowTest {
}
