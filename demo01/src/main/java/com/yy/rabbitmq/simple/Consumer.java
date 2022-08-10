package com.yy.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author young
 * @date 2022/7/29 18:42
 * @description:
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1,创建连接工程
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("43.142.85.155");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        Connection connection=null;
        Channel channel=null;
        try {
            //2，创建链接
            connection=factory.newConnection("生成者");
            //3，通过连接获取Channel
            channel = connection.createChannel();
            //4，通过创建交换机声明队列，绑定关系，路由key，发送消息和接收消息
            /**
             * 消费者消费消息
             * 1.消费哪个队列
             * 2.消费成功之后是否要自动应答 truc代表的白动应答 falsc代表手动应答
             * 3.消费者未成功消费的回调
             * 4.消费者取录消费的回调
             */
           channel.basicConsume("queue1",true, new DeliverCallback() {
               @Override
               public void handle(String s, Delivery delivery) throws IOException {
                   System.out.println("收到的消息是"+new String(delivery.getBody(),"UTF-8"));
               }
           }, new CancelCallback() {
               @Override
               public void handle(String s) throws IOException {
                   System.out.println("接受失败了……");
               }
           });
            System.out.println("消息接收成功1！");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (channel!=null&&channel.isOpen()){
                channel.close();
            }
            if (connection!= null&&connection.isOpen()){
                connection.close();
            }
        }
    }
}

