package com.lcl.galaxy.microservice.frontend.chat.degrade;

import com.alibaba.csp.sentinel.slotchain.ProcessorSlotChain;
import com.alibaba.csp.sentinel.slots.DefaultSlotChainBuilder;

public class SwitchSlotChainBuilder extends DefaultSlotChainBuilder {

    @Override
    public ProcessorSlotChain build() {
        ProcessorSlotChain chain = super.build();
        chain.addLast(new SwitchSlot());
        return chain;
    }
}
