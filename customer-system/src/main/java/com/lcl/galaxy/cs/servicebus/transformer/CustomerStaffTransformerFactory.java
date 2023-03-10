package com.lcl.galaxy.cs.servicebus.transformer;

import com.lcl.galaxy.cs.servicebus.transformer.beijing.BeijingCustomerStaffTransformer;
import com.lcl.galaxy.cs.servicebus.transformer.hangzhou.HangzhouCustomerStaffTransformer;
import com.lcl.galaxy.cs.servicebus.transformer.shanghai.ShanghaiCustomerStaffTransformer;

public class CustomerStaffTransformerFactory {

    private static final String OUTSOURCING_SYSTEM_BEIJING = "beijing";
    private static final String OUTSOURCING_SYSTEM_SHAGNHAI = "shanghai";
    private static final String OUTSOURCING_SYSTEM_HANGZHOU = "hangzhou";

    public static CustomerStaffTransformer createTransformer(String sysTemCode){
        if(OUTSOURCING_SYSTEM_SHAGNHAI.equals(sysTemCode)) {
            return new ShanghaiCustomerStaffTransformer();
        } else if(OUTSOURCING_SYSTEM_HANGZHOU.equals(sysTemCode)) {
            return new HangzhouCustomerStaffTransformer();
        } else if(OUTSOURCING_SYSTEM_BEIJING.equals(sysTemCode)) {
            return new BeijingCustomerStaffTransformer();
        }
        return null;
    }
}
