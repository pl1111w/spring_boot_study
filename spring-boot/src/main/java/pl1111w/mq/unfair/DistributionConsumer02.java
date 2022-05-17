package pl1111w.mq.unfair;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/17 13:33
 */
public class DistributionConsumer02 {

    private final static String UNFAIR_QUEUE = "unfair_queue";

    public static void main(String[] args) throws IOException {

        Channel channel = RabbitChannel.getRabbitChannel();
        int prefetch = 1;
        //设置不公平分发 autoAck设置为false才会生效
        channel.basicQos(prefetch);
        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer02: " + message);
            channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };
        try {
            channel.basicConsume(UNFAIR_QUEUE, false, deliverCallback, cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
