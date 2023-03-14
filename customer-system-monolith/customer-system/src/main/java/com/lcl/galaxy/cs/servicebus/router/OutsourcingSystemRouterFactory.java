package com.lcl.galaxy.cs.servicebus.router;

import com.lcl.galaxy.cs.servicebus.router.beijing.BeijingOutsourcingSystemRouter;
import com.lcl.galaxy.cs.servicebus.router.hangzhou.HangzhouOutsourcingSyetemRouter;
import com.lcl.galaxy.cs.servicebus.router.shanghai.ShangHaiOutSourcingSystemRouter;
import org.springframework.stereotype.Component;

@Component
public class OutsourcingSystemRouterFactory {

    private static final String OUTSOURCING_SYSTEM_BEIJING = "beijing";
    private static final String OUTSOURCING_SYSTEM_SHAGNHAI = "shanghai";
    private static final String OUTSOURCING_SYSTEM_HANGZHOU = "hangzhou";

    public static OutsourcingSystemRouter createRouter(String sysTemCode){
        if(OUTSOURCING_SYSTEM_SHAGNHAI.equals(sysTemCode)) {
            return new ShangHaiOutSourcingSystemRouter();
        } else if(OUTSOURCING_SYSTEM_HANGZHOU.equals(sysTemCode)) {
            return new HangzhouOutsourcingSyetemRouter();
        } else if(OUTSOURCING_SYSTEM_BEIJING.equals(sysTemCode)) {
            return new BeijingOutsourcingSystemRouter();
        }
        return null;
    }
}
