package com.lcl.galaxy.cs.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomerCounter {
    private String name;
    private String tagName;
    private MeterRegistry meterRegistry;

    public CustomerCounter(String name, String tagName, MeterRegistry meterRegistry){
        this.name = name;
        this.tagName = tagName;
        this.meterRegistry = meterRegistry;
    }

    private Map<String, Counter> counterMap = new HashMap<>();

    public void increment(String tagValue){
        Counter counter = counterMap.get(tagValue);
        if(counter == null){
            counter = Counter.builder(name).tag(tagName, tagValue).register(meterRegistry);
            counterMap.put(tagValue, counter);
        }
        counter.increment();
    }

    public double getCount(String tagValue){
        log.info("{} = {}: {}", tagName, tagValue, counterMap.get(tagValue).count());
        return counterMap.get(tagValue).count();
    }
}
