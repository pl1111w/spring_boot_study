package pl1111w.mq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/21 15:17
 */
public class TopicConsumer02 {

    private static final String TOPIC_MODEL = "topic_model";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitChannel.getRabbitChannel();
        String queueName="Q2";
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, TOPIC_MODEL, "*.*.rabbit");
        channel.queueBind(queueName, TOPIC_MODEL, "lazy.#");
        System.out.println("等待接收消息.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Q2 :" +message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });

    }
}
