package com.yy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author young
 * @date 2022/8/9 10:22
 * @description: 回调实现类
 * ConfirmCallback 交换机给生产者确认消息
 */
@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @PostConstruct
    public void method(){
        //注入
    rabbitTemplate.setConfirmCallback(this);
    rabbitTemplate.setReturnCallback(this);
    }
    /**
     * 交换机确认回调接口
     * @param correlationData 保存会条消息的ID及相关信息
     * @param b 交换机收到消息 true
     * @param s （失败）原因 成功null
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
     String id =correlationData!=null?correlationData.getId():"";
        if (b){
            log.info("交换机已经收到id为：{}的消息",id);
        }else{
            log.info("未收到消息的id为：{}的消息，原因是：{}",id,s);
        }
    }

    /**
     * 当消息不可到达目的地时将消息返回给生产者
     * @param message 消息体
     * @param i 错误代码编号
     * @param s 原因
     * @param s1 交换机名
     * @param s2 routingKey
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.error("消息{}，被交换机{}退回，退回原因：{}，路由routingKey是：{}",new String(message.getBody()),s1,s,s2);
    }
}
