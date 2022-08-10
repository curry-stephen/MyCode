package com.yy.rabbitmq.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.yy.rabbitmq.RabbitUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author young
 * @date 2022/8/7 9:34
 * @description: 直接发送模式，指定发给绑定的路由key的消费者
 */
public class Provider {
    /** 交换机名称 **/
    private static final String  EXCHANGE_NAME="topic" ;

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        Map<String,String> bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("quick.orrange.rabbit","被对列1对列2接收到");
        bindingKeyMap.put("lazy.orange.ele","对列12接收到");
        bindingKeyMap.put("quick.orangex.fox","对列1接收到");
        bindingKeyMap.put("lazy.brown.fox","对列2接收到");
        bindingKeyMap.put("lazy.pink.rabbit","2接受一次");
        bindingKeyMap.put("quick.brown.fox","没有匹配被丢弃");
        bindingKeyMap.put("quick.orange.pdd.rabbit","没有匹配被丢弃");
        bindingKeyMap.put("lazy.orange.ssl.rabbit","匹配2");
        for (Map.Entry<String, String> entry : bindingKeyMap.entrySet()) {
            String routingKey = entry.getKey();
            String message = entry.getValue();
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息："+message);

        }


    }
}
