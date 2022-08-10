package com.yy.controller;

import com.yy.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author young
 * @date 2022/8/8 10:25
 * @description: 发送消息，延时队列实现
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 开始发消息
     * @param message
     */
    @GetMapping("/sendMsg/{message}")
    public void method(@PathVariable String message){
        log.info("当前时间：{}，发送一条消息给两个TTL队列：{}",new Date().toString(),message);
        rabbitTemplate.convertAndSend("N","life1","消息来自ttl为10s的队列："+message);
        rabbitTemplate.convertAndSend("N","life2","消息来自ttl为40s的队列："+message);
    }


    /**
     * 开始发消息 优化后的结果
     * @param message 消息体
     * @param ttlTime 设置自主控制的的ttl时间
     */
    @GetMapping("/senExpirationMsg/{message}/{ttlTime}")
    public void method(@PathVariable String message,@PathVariable String ttlTime){
      log.info("当前时间：{}，发送一条时常{}毫秒TTL信息给队列queue3：{}",new Date().toString(),ttlTime,message);
      rabbitTemplate.convertAndSend("N","life3",message,(msg -> {
          //发送消息时候延时时长
          msg.getMessageProperties().setExpiration(ttlTime);
          return msg;
      }));
/*存在问题：
在最开始的时候，就介绍过如果使用在消息属性上设置TTL的方式，消息可能并不会按时“死亡“，
因为RabbitMQ只会检查第一个消息是否过期，如果过期则丢到死信队列,如果第一个消息的延时时长很长，
而第二个消息的延时时长很短，第二个消息并不会优先得到执行。
*/
    }

    /**
     * 基于插件的延迟消息发送 :当第一个延时消息时间比第二个消息长时会优先消费延时较短的
     * @param message 消息体
     * @param delayedTime 延迟时间
     */
    @GetMapping("/sendDelayedMsg/{message}/{delayedTime}")
    public void method(@PathVariable String message,@PathVariable Integer delayedTime){
        log.info("带你给钱时间：{}，发送一条时长{}毫秒的消息给延迟队列delayed.queue：{}",new Date().toString(),delayedTime,message);
    
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME,DelayedQueueConfig.DELAYED_ROUTINGKEY_NAME,message,msg->{
            //发送消息的时候 延迟时长 单位：ms2
            msg.getMessageProperties().setDelay(delayedTime);
            return msg;
        } );
    }
        
}
