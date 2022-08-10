package com.yy.rabbitmq.fanout;

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
    private static final String EXCHANGE_NAME="123";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //声明一个临时对列
        String queueName=channel.queueDeclare().getQueue();
        /**
         * 绑定交换机与对列
         */
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println("等待消息接收消息，把消息打印……");

        //接收消息
        DeliverCallback deliverCallback = (consumerTag, message)->{
            System.out.println("控制台打印接收消息："+new String(message.getBody(),"UTF-8"));
        };
        channel.basicConsume(queueName,true,deliverCallback,consumerTag->{});
    }
}
