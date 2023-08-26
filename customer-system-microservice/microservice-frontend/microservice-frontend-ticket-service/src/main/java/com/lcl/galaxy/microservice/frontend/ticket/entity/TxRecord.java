package com.lcl.galaxy.microservice.frontend.ticket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("tx_record")
public class TxRecord implements Serializable {
    private String txNo;
    private LocalDateTime createTime;
}
