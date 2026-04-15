package com.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class BasicSingletonTest {

    @Test
    void getInstanceReturnsNonNull() {
        assertNotNull(BasicSingleton.getInstance());
    }

    @Test
    void getInstanceReturnsSameInstanceOnRepeatedCalls() {
        BasicSingleton first = BasicSingleton.getInstance();
        BasicSingleton second = BasicSingleton.getInstance();
        assertSame(first, second);
    }
}
