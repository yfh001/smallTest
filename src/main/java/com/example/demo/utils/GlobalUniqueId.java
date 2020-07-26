package com.example.demo.utils;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class GlobalUniqueId {

    @Autowired
    RedissonClient redissonClient;

    public String generateGuid() {
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong("GUID_FOR_TRADEID");
        Long longValue = rAtomicLong.getAndIncrement();
        String surffix = String.format("%06d",longValue);
        String  prefix = currentTimeString();
        return prefix+surffix;
    }

    public String currentTimeString() {
        LocalDateTime arrivalDate = LocalDateTime.now();
        String landing = "";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        landing = arrivalDate.format(format);
        System.out.printf("Arriving at :  %s %n", landing);
        return landing;
    }
}