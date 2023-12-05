package com.jayden.bridgenotifier;

import java.time.LocalDateTime;

public class TimeUtil {
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }
}
