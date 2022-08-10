package com.yy.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author young
 * @date 2022/7/30 11:32
 * @description:
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
           
            //5，准备消息内容
            String message="黎治跃又在内卷";
            //6，准备交换机
            String exchangeName="fanout-exchange";
            //定义路由key
            String routeKey="";
            //指定交换机类型
            String type="fanout";
            //@param1 :交换机 @param2：对列，路由key @param3：消息的状态控制 @param4：消息主题
            channel.basicPublish(exchangeName,routeKey,null,message.getBytes());
            System.out.println("消息发送成功！");
        } catch (Exception e) {
            System.out.println("消息发送异常");
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
