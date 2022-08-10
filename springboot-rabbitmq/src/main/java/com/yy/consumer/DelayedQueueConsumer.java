package com.yy.consumer;

import com.yy.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author young
 * @date 2022/8/8 21:30
 * @description: 基于插件的延迟消息消费者
 */
@Slf4j
@Component
public class DelayedQueueConsumer {
    //监听消息
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void method(Message message){
        String msg = new String(message.getBody());
        
       log.info("当前时间：{}，收到延迟队列的消息：{}",new Date().toString(),msg);
    }
}
