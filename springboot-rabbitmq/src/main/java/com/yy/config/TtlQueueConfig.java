package com.yy.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author young
 * @date 2022/8/8 9:55
 * @description: TTL队列:两个普通队列，一个死信队列
 */
@Configuration
public class TtlQueueConfig {
    
    /** 普通交换机 **/
    private static final String N_EXCHANGE ="N" ;
    /** 死信交换机 **/
    private static final String  D_EXCHANGE="D" ;
    /** 普通队列1 **/
    private static final String  N_QUEUE1="q1" ;
    /** 普通队列2 **/
    private static final String  N_QUEUE2="q2";
    /** 死信队列 **/
    private static final String  D_QUEUE="d1" ;

    /**
     * 延时优化方案，增加一个没有延时的队列
     */
    private static final String  QUEUE3="q3";
    @Bean("queue3")
    public Queue queue3(){
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("x-dead-letter-exchange",D_EXCHANGE);
        map.put("x-dead-letter-routing-key","dead");
        return QueueBuilder.durable(QUEUE3).withArguments(map).build();
    }
    @Bean
    public Binding queue3Binding(@Qualifier("queue3")Queue queue3,@Qualifier("nExchange")DirectExchange nexchange){
        return BindingBuilder.bind(queue3).to(nexchange).with("life3");
    }
     //声明普通交换机
    @Bean("nExchange")
    public DirectExchange nExchange(){
        return new DirectExchange(N_EXCHANGE);
    }
    @Bean("dExchange")
    public DirectExchange dExchange(){
        return new DirectExchange(D_EXCHANGE);
    }
    @Bean("nQueue1")
    public Queue queueLife(){
        Map<String, Object> arguments = new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",D_EXCHANGE);
        //设置死信routingKey
        arguments.put("x-dead-letter-routing-key","dead");
        //设置TTL
        arguments.put("x-message-ttl",10000);
        return  QueueBuilder.durable(N_QUEUE1).withArguments(arguments).build();
    }
    @Bean("nQueue2")
    public Queue queueLife2(){
        Map<String, Object> arguments = new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",D_EXCHANGE);
        //设置死信routingKey
        arguments.put("x-dead-letter-routing-key","dead");
        //设置TTL
        arguments.put("x-message-ttl",40000);
        return  QueueBuilder.durable(N_QUEUE2).withArguments(arguments).build();
    }
    @Bean("dQueue")
    public Queue queueDead(){
            return  QueueBuilder.durable(D_QUEUE).build();
        }
        //绑定交换机与队列
     @Bean
    public Binding queueBindNormal1(@Qualifier("nQueue1")Queue queue1,
                                    @Qualifier("nExchange")DirectExchange n_exchange){
        return BindingBuilder.bind(queue1).to(n_exchange).with("life1");
     }
    @Bean
    public Binding queueBindNormal2(@Qualifier("nQueue2")Queue queue2,
                                    @Qualifier("nExchange")DirectExchange n_exchange){
        return BindingBuilder.bind(queue2).to(n_exchange).with("life2");
    }
    @Bean
    public Binding queueBindDead(@Qualifier("dQueue")Queue queueDead,
                                    @Qualifier("dExchange")DirectExchange d_exchange){
        return BindingBuilder.bind(queueDead).to(d_exchange).with("dead");
    }
}
