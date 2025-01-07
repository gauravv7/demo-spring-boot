package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateLimitData {

    AtomicInteger requestCounter;
    LocalDateTime lastOccuringTimeStamp;
}
