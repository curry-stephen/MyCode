package com.yy.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author young
 * @date 2022/8/10 14:02
 * @description:
 */
@Configuration
public class Config {
    //1.声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_order_exchange", true, false);
    }

    //2.声明队列，sms.fanout.queue email.fanout.queue msg.fanout.queue
    @Bean
    public Queue queueOne() {
        return new Queue("one.fanout.queue", true);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue("two.fanout.queue", true);
    }

    @Bean
    public Queue queueThree() {
        return new Queue("three.fanout.queue", true);
    }

    //3.完成绑定关系（队列与交换机完成绑定关系）
    @Bean
    public Binding smsBind() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBind() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }

    @Bean
    public Binding msgBind() {
        return BindingBuilder.bind(queueThree()).to(fanoutExchange());
    }
}
