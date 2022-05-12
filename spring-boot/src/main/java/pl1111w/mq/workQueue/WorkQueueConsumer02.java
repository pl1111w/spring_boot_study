package pl1111w.mq.workQueue;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/9 18:13
 */
public class WorkQueueConsumer02 {

    private static final String WORK_QUEUE = "WorkQueue";

    public static void main(String[] args) {

        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(message);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };

        try {
            Channel channel = RabbitChannel.getRabbitChannel();
            channel.basicConsume(WORK_QUEUE, true, deliverCallback, cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
