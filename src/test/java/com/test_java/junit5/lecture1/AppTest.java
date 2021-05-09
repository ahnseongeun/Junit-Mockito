package com.test_java.junit5.lecture1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    public void 테스트1(){
        App app = new App();
        assertNotNull(app);
    }
}