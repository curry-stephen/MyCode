package com.yy.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.yy.rabbitmq.RabbitUtils;

import java.util.Scanner;

/**
 * @author young
 * @date 2022/8/7 9:34
 * @description: 发布订阅模式
 */
public class Provider {
    /** 交换机名称 **/
    private static final String  EXCHANGE_NAME="123" ;

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message= "消息"+scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println("生产者发出消息："+message);
        }
    }
}
