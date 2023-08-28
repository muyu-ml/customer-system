package com.lcl.galaxy.microservice.frontend.chat.degrade;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.slotchain.AbstractLinkedProcessorSlot;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;

public class SwitchSlot extends AbstractLinkedProcessorSlot {
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, Object o, int i, boolean b, Object... objects) throws Throwable {
        // 在调用资源时执行开关降级判断
        SwitchRuleChecker.checkSwitch(resourceWrapper, context);
        fireEntry(context, resourceWrapper, o, i, b, objects);
    }

    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int i, Object... objects) {
        fireExit(context, resourceWrapper, i, objects);
    }
}
