CREATE TABLE `im_message` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `from_user_id` bigint(20) NOT NULL COMMENT '消息发送方用户Id',
                              `from_username` varchar(45) DEFAULT NULL COMMENT '消息发送方用户名',
                              `to_user_id` bigint(20) NOT NULL COMMENT '消息接收方用户Id',
                              `to_username` varchar(45) DEFAULT NULL COMMENT '消息接收方用户名',
                              `business_type_code` varchar(45) DEFAULT NULL COMMENT '业务类型编码',
                              `business_type_name` varchar(45) DEFAULT NULL COMMENT '业务类型名称',
                              `message` varchar(255) NOT NULL COMMENT '聊天消息',
                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聊天记录表';