package com.yy.rabbitmq.max_queue_length;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.yy.rabbitmq.RabbitUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author young
 * @date 2022/8/7 11:03
 * @description: 消费者1
 */
public class Consumer01 {
    
    /**  普通交换机**/
    private static final String  NORMAL_EXCHANGE="normal_exchange";
    /**  死信交换机**/
    private static final String  DEAD_EXCHANGE="dead_exchange";
    
    /** 普通队列名称 **/
    private static final String NORMAL_QUEUE ="normal_queue" ;
    
    /** 死信队列的名称 **/
    private static final String DEAD_QUEUE ="dead_queue" ;

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();
        //声明死信和普通交换机，类型为direct
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);
        
        //声明死信和普通队列
        Map<String, Object> arguments = new HashMap<>();
        //过期时间,可由生产者指定
        //arguments.put("x-message-time",10000);
        
        //正常队列设置过期后的死信交换机
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //设置死信routingKey
        arguments.put("x-dead-letter-routing-key","dead");
        
        //设置正常的队列的长度限制
        arguments.put("x-max-length",6);
        
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,arguments);
        
        //声明死信队列
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);
        
        //绑定普通交换机与普通队列
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"life");
        //绑定死信交换机与死信队列
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"dead");

        System.out.println("等待接收消息……");
        
        
        channel.basicConsume(NORMAL_QUEUE,true,(consumerTag,message)->{
            System.out.println("Consumer01接收的消息："+"\n"+new String(message.getBody(), StandardCharsets.UTF_8));
        },consumerTag->{});
    }
}
