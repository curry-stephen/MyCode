package com.yy.rabbitmq.dead_queue;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.yy.rabbitmq.RabbitUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author young
 * @date 2022/8/7 11:30
 * @description: 消费者2
 */
public class Consumer02 {

    /** 死信队列的名称 **/
    private static final String DEAD_QUEUE ="dead_queue" ;

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();
      
        System.out.println("等待接收消息……");
        
        channel.basicConsume(DEAD_QUEUE,true,(consumerTag,message)->{
            System.out.println("Consumer02接收的消息："+"\n"+new String(message.getBody(),"UTF-8"));
        },consumerTag->{});
    }
}
