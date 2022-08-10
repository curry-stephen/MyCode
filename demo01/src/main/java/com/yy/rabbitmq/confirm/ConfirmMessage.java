package com.yy.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.yy.rabbitmq.RabbitUtils;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author young
 * @date 2022/8/4 10:50
 * @description: 发布确认方式对比
 */

public class ConfirmMessage {
    private static final int MESSAGE_COUNT = 100;

    public static void main(String[] args) throws Exception {
        //1,单个确认 3539ms
    //    ConfirmMessage.publishMessage();
        //2，批量确认  514ms
    //    ConfirmMessage.publishMoreMessage();
        //3，异步批量确认 12ms
        ConfirmMessage.publishAsyncMessage();
    }


    //单个确认
    private static void publishMessage() throws Exception {
        Channel channel = RabbitUtils.getChannel();

        //队列声明
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long start = System.currentTimeMillis();
        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());

            //单个消息马上发布确认
            boolean flag = channel.waitForConfirms();
            if (flag) {
                System.out.println("消息发送成功！" + message);
            }
        }
        //结束时间
        long end = System.currentTimeMillis();

        System.out.println("发布" + MESSAGE_COUNT + "个单个确认消息，消耗的时间是：" + (end - start) + "ms");
    }

    //批量确认
    private static void publishMoreMessage() throws Exception {
        Channel channel = RabbitUtils.getChannel();

        //队列声明
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long start = System.currentTimeMillis();
        //批量确认消息大小
        int batchSize = 10;

        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            //判断达到10条消息时候 批量确认一次
            if (i % batchSize == 0) {
                //发布确认
                channel.waitForConfirms();
            }
        }

        //结束时间
        long end = System.currentTimeMillis();

        System.out.println("批量发布" + MESSAGE_COUNT + "个批量确认消息，消耗的时间是：" + (end - start) + "ms");

    }

    //异步确认发布消息
    public static void publishAsyncMessage() throws Exception {
        Channel channel = RabbitUtils.getChannel();

        //队列声明
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();

        /**
         * 线程安全有序的一个哈希表，使用与高并发的情况
         * 1，将序号与消息进行关联
         * 2，批量删除条目
         * 3，支持高并发，多线程
         */
        ConcurrentSkipListMap<Long,String> outstandingConfirms = new ConcurrentSkipListMap<>();
        //开始时间
        long start = System.currentTimeMillis();
        //消息确认成功，回调函数
        ConfirmCallback confirmCallback = (deliveryTag, multiple) -> {
            if (multiple){
                //删除到已经确认的消息 剩下的就是为确认的消息
                ConcurrentNavigableMap<Long, String> confirmd = outstandingConfirms.headMap(deliveryTag);
                confirmd.clear();
            }else {
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认的消息:" + deliveryTag);
        };
        //消息确认失败，回调函数
        /**
         * 1,消息的标记
         * 2，是否为批量标记
         */
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            String s = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认的消息是："+s+"未确认的消息条目：" + deliveryTag);
        };
        //准备消息监听器，监听那些消息成功了，那些消息失败了
        channel.addConfirmListener(confirmCallback, ackCallback);

        //批量发布
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            //记录下所有要发送的消息 消息的总和
            outstandingConfirms.put(channel.getNextPublishSeqNo(),message);
        }

        //结束时间
        long end = System.currentTimeMillis();

        System.out.println("异步确认发布" + MESSAGE_COUNT + "个异步确认消息，消耗的时间是：" + (end - start) + "ms");

    }
}
