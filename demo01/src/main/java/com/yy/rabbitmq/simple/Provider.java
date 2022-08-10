package com.yy.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author young
 * @date 2022/7/29 18:42
 * @description: 生产者
 */
public class Provider {
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
            String queueName="queue1";
            /**
             * 生成一个队列
             * 1.队列名称
             * *2.队列里面的消息是否持久化（磁盘）默认情况消息存储在内存中
             * *3.该队列是否只供一个消费者进行消费是否进行消息共享，true可以多个消费者消费false:只能一个消费者消费*4.是否白动删除最后一个消费者端开连接以后该队一句是否白动鹏除 true白动删除false不白动删除
             * *5.其它参数
             * */
            channel.queueDeclare(queueName,false,false,false,null);
            //5，准备消息内容
            String message="黎治跃又在内卷";
            //6，发送消息给队列
            //@param1 :交换机 @param2：对列，路由key @param3：消息的状态控制 @param4：消息主题
            channel.basicPublish("",queueName,null,message.getBytes());
            System.out.println("消息发送成功！");
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
