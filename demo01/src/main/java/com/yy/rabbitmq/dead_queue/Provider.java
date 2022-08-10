package com.yy.rabbitmq.dead_queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.yy.rabbitmq.RabbitUtils;

/**
 * @author young
 * @date 2022/8/7 11:22
 * @description: 生产者
 */

public class Provider {
    /**  普通交换机**/
    private static final String  NORMAL_EXCHANGE="normal_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();
        //死信消息，设置TTL时间10s  单位为ms
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        for (int i = 1; i < 11; i++) {
            String message="msg"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"life",properties,message.getBytes());
            
        }
        
    }
}
