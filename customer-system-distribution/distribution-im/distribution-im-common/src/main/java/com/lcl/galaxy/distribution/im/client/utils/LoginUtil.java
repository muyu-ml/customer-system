package com.lcl.galaxy.distribution.im.client.utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLgin(Channel channel) {
        Attribute<Boolean> login = channel.attr(Attributes.LOGIN);
        if(login.get() != null){
            return true;
        }
        return false;
    }
}
