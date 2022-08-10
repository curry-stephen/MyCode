package com.yy.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author young
 * @date 2022/8/10 14:11
 * @description:
 */
@Component
@RabbitListener(queues = "three.fanout.queue")
public class ThreeConsumer {
    @RabbitHandler
    public void messageService(String message) {
        System.out.println("fanout three ==> " + message);
    }
}
