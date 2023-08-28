package com.lcl.galaxy.microservice.frontend.chat.degrade;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SwitchException extends BlockException {
    public SwitchException(String ruleLimitApp, String message) {
        super(ruleLimitApp, message);
    }
}
