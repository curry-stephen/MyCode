package com.yy.rabbitmq.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.yy.rabbitmq.RabbitUtils;

import java.util.Scanner;

/**
 * @author young
 * @date 2022/8/7 9:34
 * @description: 直接发送模式，指定发给绑定的路由key的消费者
 */
public class Provider {
    /** 交换机名称 **/
    private static final String  EXCHANGE_NAME="direct" ;

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message= "消息"+scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"key2",null,message.getBytes());
            System.out.println("生产者发出消息："+message);
        }
    }
}
