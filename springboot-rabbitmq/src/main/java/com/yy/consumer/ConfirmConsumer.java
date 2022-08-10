package com.yy.consumer;

import com.yy.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author young
 * @date 2022/8/9 10:08
 * @description: 接收消息
 */
@Component
@Slf4j
public class ConfirmConsumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE)
    public void method(Message message){
        String msg = new String(message.getBody());
        log.info("接收到的消息为：{}",msg);
    
    }
}
