package com.yy.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.yy.rabbitmq.RabbitUtils;

import java.util.Scanner;

/**
 * @author young
 * @date 2022/8/2 9:09
 * @description:
 */
public class Provider {
    public static final String QUEUE_NAME="对列1";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();
        //对列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println("输入消息：");
            String message=scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("消息发送完成："+message);
        }
    }
}
