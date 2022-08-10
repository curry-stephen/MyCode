package com.yy.rabbitmq.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.yy.rabbitmq.RabbitUtils;

/**
 * @author young
 * @date 2022/8/7 9:34
 * @description:
 */
public class Consumer2 {
    //交换机名称
    private static final String EXCHANGE_NAME="direct";
    private static final String  QUEUE_NAME= "队列direct2";
    public static void main(String[] args) throws Exception {

        Channel channel = RabbitUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //声明一个对列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        /**
         * 绑定交换机与对列
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"key2");
        System.out.println(QUEUE_NAME+"等待消息接收消息，把消息打印……");

        //接收消息
        DeliverCallback deliverCallback = (consumerTag, message)->{
            System.out.println(QUEUE_NAME+"控制台打印接收消息："+new String(message.getBody(),"UTF-8"));
        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag->{});
    }
}
