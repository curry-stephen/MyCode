package com.yy.consumer;

import com.yy.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author young
 * @date 2022/8/9 11:15
 * @description: 报警消费者
 */
@Slf4j
@Component
public class WarningConsumer {
    //接受报警消息
    @RabbitListener(queues = ConfirmConfig.WARN_QUEUE)
    public void method(Message message){
    String msg = new String(message.getBody());
    log.error("接收到的报警消息为：{}",msg);
    }
}
