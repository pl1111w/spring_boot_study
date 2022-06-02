package pl1111w.mq.priority;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/6/2 9:54
 */
public class PriorityConsumer {

    private static final String PRIORITY_QUEUE = "priority_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitChannel.getRabbitChannel();
        //设置队列的最大优先级 最大可以设置到 255 官网推荐 1-10 如果设置太高比较吃内存和 CPU
        System.out.println("消费者启动等待消费......");
        DeliverCallback deliverCallback=(consumerTag, delivery)->{
            String receivedMessage = new String(delivery.getBody());
            System.out.println("接收到消息: "+receivedMessage);
        };
        channel.basicConsume(PRIORITY_QUEUE,true,deliverCallback,(consumerTag)->{
            System.out.println("消费者无法消费消息时调用，如队列被删除");
        });
    }

}
