package com.lcl.galaxy.microservice.frontend.chat.degrade;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class SwitchRule {
    public static final String SWITCH_KEY_OPEN = "open";
    public static final String SWITCH_KEY_CLOSE = "close";
    // 开关状态
    private String status = SWITCH_KEY_OPEN;
    // 开关控制的资源
    private Resources resources;

    @Data
    @ToString
    public static class Resources {
        // 包含的资源
        private Set<String> include;
        // 排除的资源
        private Set<String> exclude;
    }
}
