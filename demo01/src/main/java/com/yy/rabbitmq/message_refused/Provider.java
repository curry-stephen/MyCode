package com.yy.rabbitmq.message_refused;

import com.rabbitmq.client.Channel;
import com.yy.rabbitmq.RabbitUtils;

/**
 * @author young
 * @date 2022/8/7 21:44
 * @description:启动消费者1后，启动生产者，消费者1消费完成后，关闭消费者1实现死信后，启动消费者2可看到消息进入死信队列消费
 */

public class Provider {
    /**  普通交换机**/
    private static final String  NORMAL_EXCHANGE="normal_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitUtils.getChannel();
        //死信消息，设置TTL时间10s  单位为ms
     //   AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        for (int i = 1; i < 11; i++) {
            String message="msg"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"life",null,message.getBytes());

        }

    }
}
