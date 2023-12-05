package com.jayden.bridgenotifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

class TimeUtilTest {

    @Test
    @DisplayName("getCurrentTime should return current time")
    void test() {
        var now = LocalDateTime.now();

        try (var mockedLocalDateTime = mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(now);

            var actual = TimeUtil.getCurrentTime();

            assertThat(actual).isEqualTo(LocalDateTime.now().toString());
        }
    }
}