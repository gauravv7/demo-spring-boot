package com.example.demo.utils;

import com.example.demo.models.RateLimitData;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimitUtils {

    public static RateLimitData makeRateLimitData() {
        return new RateLimitData(new AtomicInteger(0), LocalDateTime.now());
    }

    public static boolean checkIfPastTime(LocalDateTime inputTime, long MIN_IN_SECONDS_TO_RATE_LIMIT) {
        // Get the current time
        LocalDateTime now = LocalDateTime.now();

        // Calculate the difference in minutes
        long minutesDifference = Duration.between(inputTime, now).getSeconds();

        return minutesDifference > MIN_IN_SECONDS_TO_RATE_LIMIT;
    }
}
