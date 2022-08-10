package com.yy.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author young
 * @date 2022/8/8 10:34
 * @description:
 */
@Slf4j
@Component
public class DeadLetterQueueConsumer {
    /**
    接收消息
     */
    @RabbitListener(queues = "d1")
    public void method(Message message, Channel channel){
        String msg= new String(message.getBody());
        log.info("当前时间：{}，收到死信队列的消息：{}",new Date().toString(),msg);
    
    }
}
