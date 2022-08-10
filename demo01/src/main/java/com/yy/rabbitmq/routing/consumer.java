package com.yy.rabbitmq.routing;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author young
 * @date 2022/7/30 11:32
 * @description:
 */
@SuppressWarnings({"ALL", "AlibabaAvoidManuallyCreateThread"})
@Slf4j
public class consumer {
    public static Runnable runnable=new Runnable() {
        @Override
        public void run() {
            //1,创建连接工程
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("43.142.85.155");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            Connection connection = null;
            Channel channel = null;
            try {
                //2，创建链接
                connection = factory.newConnection("生成者");
                //3，通过连接获取Channel
                channel = connection.createChannel();
                //4，通过创建交换机声明队列，绑定关系，路由key，发送消息和接收消息
                channel.basicConsume("queue1", true, new DeliverCallback() {
                    @Override
                    public void handle(String s, Delivery delivery) throws IOException {
                        System.out.println("收到的消息是" + new String(delivery.getBody(), "UTF-8"));
                    }
                }, new CancelCallback() {
                    @Override
                    public void handle(String s) throws IOException {
                        System.out.println("接受失败了……");
                    }
                });
                
                log.info("消息接收成功1！");
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (channel != null && channel.isOpen()) {
                    try {
                        channel.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null && connection.isOpen()) {
                    try {
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };


    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {
        //noinspection AlibabaAvoidManuallyCreateThread
        new Thread(runnable,"queue1").start();
        new Thread(runnable,"queue2").start();
        new Thread(runnable,"queue3").start();
    }
}
