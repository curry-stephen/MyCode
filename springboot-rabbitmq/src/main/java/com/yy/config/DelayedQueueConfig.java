package com.yy.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author young
 * @date 2022/8/8 21:07
 * @description: 基于插件的延时队列实现
 */
@Configuration
public class DelayedQueueConfig {
    /** 队列，交换机，routingKey **/
    public static final String  DELAYED_QUEUE_NAME= "delayed.queue";
    public static final String  DELAYED_EXCHANGE_NAME= "delayed.exchange";
    public static final String  DELAYED_ROUTINGKEY_NAME= "delayed.routingkey";

    @Bean
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }
//声明自定义基于插件的交换机
    @Bean
    public CustomExchange delayedExchange(){
       Map<String,Object> arguments = new HashMap<>();
       arguments.put("x-delayed-type","direct");
        /**
         * 1,交换机名称
         * 2，交换机类型
         * 3，是否持久化
         * 4，是否自动删除
         * 5，其他参数
         */
        return new CustomExchange(DELAYED_EXCHANGE_NAME,"x-delayed-message",true,false,arguments);
    }
    
    //绑定
    @Bean
    public Binding delayedQueueBindingDelayedExchange(@Qualifier("delayedQueue")Queue delayedQueue,
                                                      @Qualifier("delayedExchange")CustomExchange delayedExchange){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTINGKEY_NAME).noargs();
        
    }
}
