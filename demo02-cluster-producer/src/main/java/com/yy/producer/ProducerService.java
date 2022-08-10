package com.yy.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author young
 * @date 2022/8/10 14:01
 * @description:
 */
@Service
public class ProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void method() {
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功：" + orderId);
        String exchange_name = "fanout_order_exchange";
        String routeingKey = "";
        rabbitTemplate.convertAndSend(exchange_name, routeingKey, orderId);
    }
}
