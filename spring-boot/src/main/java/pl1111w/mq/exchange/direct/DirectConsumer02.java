package pl1111w.mq.exchange.direct;

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
public class DirectConsumer02 {

    private static final String DIRECT_MODEL = "direct_model";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitChannel.getRabbitChannel();
        String queueName = "console";
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, DIRECT_MODEL, "info");
        channel.queueBind(queueName, DIRECT_MODEL, "warning");
        System.out.println("等待接收消息.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Consumer01-消息读取成功" + message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });

    }
}
