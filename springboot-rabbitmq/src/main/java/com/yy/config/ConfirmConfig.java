package com.yy.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author young
 * @date 2022/8/9 9:58
 * @description: 发布确认（高级）配置
 */
@Configuration
public class ConfirmConfig {
    /**
     * 交换机
     **/
    public static final String CONFIRM_EXCHANGE = "confirm_exchange";
    /**
     * 队列
     **/
    public static final String CONFIRM_QUEUE = "confirm_queue";
    /**
     * routingkey
     **/
    public static final String CONFIRM_ROUTING_KEY = "confirm1";

    /**
     * 备份交换机
     **/
    private static final String BACKUP_EXCHANGE = "backup_exchange";
    /**
     * 备份队列
     **/
    private static final String BACKUP_QUEUE = "backup_queue";
    /**
     * 报警队列
     **/
    public static final String WARN_QUEUE = "warn_queue";

    /**
     * 确认交换机须转发至备份交换机
     * 测试时手动在rabbimqWeb界面删除confirm_exchange交换机
     *
     * @return
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        //   return new DirectExchange(CONFIRM_EXCHANGE);
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE).durable(true)
                .withArgument("alternate-exchange", BACKUP_EXCHANGE).build();
    }

    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE).build();
    }

    //备份交换机
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE);
    }

    //备份队列
    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE).build();
    }

    //警报队列
    @Bean("warnQueue")
    public Queue warnQueue() {
        return QueueBuilder.durable(WARN_QUEUE).build();
    }

    /**
     * 采用发布确认fanout交换机
     *
     * @param backupQueue
     * @param backupExchange
     * @return 绑定备份交换机与备份队列
     */
    @Bean
    public Binding backQueueBindingExchange(@Qualifier("backupQueue") Queue backupQueue,
                                            @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(backupQueue).to(backupExchange);

    }

    /**
     * 采用发布确认fanout交换机
     *
     * @param warnQueue
     * @param warnExchange
     * @return 绑定备份交换机与警报队列
     */
    @Bean
    public Binding warnQueueBindingExchange(@Qualifier("warnQueue") Queue warnQueue,
                                            @Qualifier("backupExchange") FanoutExchange warnExchange) {
        return BindingBuilder.bind(warnQueue).to(warnExchange);

    }

    /**
     * 采用发布确认direct交换机
     *
     * @param confirmQueue
     * @param confirmExchange
     * @return
     */
    @Bean
    public Binding queueBindingExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                        @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);

    }
}
