package com.yy.rabbitmq.work;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.yy.rabbitmq.RabbitUtils;

/**
 * @author young
 * @date 2022/8/2 9:09
 * @description: 创建工作线程
 */
public class Work01 {
    public static final String QUEUE_NAME="对列1";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();

        //接收消息
        DeliverCallback deliverCallback=(consumer,message)->{
            System.out.println("接收到的消息："+new String(message.getBody()));
        };
        //消息接收被取消是时，执行下面的内容
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println(consumerTag+"消息被消费者取消消费接口回调逻辑");
        };
        System.out.println("第一个线程等待接收消息……");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
