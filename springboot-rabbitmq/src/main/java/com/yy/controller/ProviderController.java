package com.yy.controller;

import com.yy.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author young
 * @date 2022/8/9 10:06
 * @description:
 */
@RestController
@Slf4j
@RequestMapping("/confirm")
public class ProviderController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/senfMessage/{message}")
    public void method(@PathVariable String message){
        CorrelationData correlationData = new CorrelationData("1");
        
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE,ConfirmConfig.CONFIRM_ROUTING_KEY,message,correlationData);
    log.info("发送消息内容为：{}",message);
    }
}
