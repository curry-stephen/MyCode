package com.yy.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author young
 * @date 2022/8/10 14:09
 * @description:
 */
@RabbitListener(queues = "one.fanout.queue")
@Component
public class OneConsumer {
    @RabbitHandler
    public void messageService(String message) {
        System.out.println("fanout one ==>" + message);
    }
}
