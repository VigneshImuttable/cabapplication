package com.create.cabapplication.configs;

import com.create.cabapplication.strategies.DefaultPayStrategy;
import com.create.cabapplication.strategies.DynamicPayStrategy;
import com.create.cabapplication.strategies.PayStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;

@Configuration
public class AppConfigs {

    @Bean
    public PayStrategy selectStrategy(){
        if (isDayTime()) {
            return new DefaultPayStrategy();
        } else {
            return new DynamicPayStrategy();
        }
    }

    private boolean isDayTime() {
        LocalTime currentTime = LocalTime.now();

        LocalTime dayStartTime = LocalTime.of(6, 0);
        LocalTime dayEndTime = LocalTime.of(21, 0);

        return currentTime.isAfter(dayStartTime) && currentTime.isBefore(dayEndTime);
    }
}
