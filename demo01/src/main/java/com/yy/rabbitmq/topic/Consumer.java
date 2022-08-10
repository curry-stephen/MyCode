package com.yy.rabbitmq.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.yy.rabbitmq.RabbitUtils;

/**
 * @author young
 * @date 2022/8/7 9:23
 * @description: 消费者1
 */
public class Consumer {
    //交换机名称
    private static final String EXCHANGE_NAME="topic";
/** 队列名称 **/
private static final String  QUEUE_NAME= "队列topic1";
    public static void main(String[] args) throws Exception {

        Channel channel = RabbitUtils.getChannel();
//        声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //声明一个对列
        /**
         * @param1,队列名
         * @param2，是否持久化
         * @param3，是否共享
         * @param4，是否自动删除
         * @param5，参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        /**
         * 绑定交换机与对列
         * @param1,队列名
         * @param2，交换机名
         * @param3，routingKey规则
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"*.orange.*");
        System.out.println(QUEUE_NAME+"等待消息接收消息，把消息打印……");
        
        //接收消息
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println(QUEUE_NAME+"的routingKey是"+message.getEnvelope().getRoutingKey()+"\n"+"控制台打印接收消息："+new String(message.getBody(),"UTF-8"));
        };
        /**
         * @param1,队列名
         * @param2，是否自动应答
         * @param3，接收消息回调接口
         * @param4，取消回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag->{
            System.out.println("接受失败");
        });
    }
}
