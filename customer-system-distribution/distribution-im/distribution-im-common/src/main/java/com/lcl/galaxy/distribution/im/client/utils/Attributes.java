package com.lcl.galaxy.distribution.im.client.utils;

import io.netty.util.AttributeKey;

public interface Attributes {
    /**
     * 登录状态，使用channel.attr()方法保存
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
