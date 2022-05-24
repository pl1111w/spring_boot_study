package pl1111w.mq.dead;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/23 21:51
 */
public class DeadUnNormalConsumer {

    public final static String ABNORMAL_EXCHANGE = "abnormal_exchange";
    public final static String ABNORMAL_QUEUE = "abnormal_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitChannel.getRabbitChannel();
        channel.exchangeDeclare(ABNORMAL_EXCHANGE, BuiltinExchangeType.DIRECT, false, false, null);
        channel.queueDeclare(ABNORMAL_QUEUE, false, false, false, null);
        channel.queueBind(ABNORMAL_QUEUE, ABNORMAL_EXCHANGE, "abnormal");
        System.out.println("等待接收消息.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("abnormal-消息读取成功" + message);
        };
        channel.basicConsume(ABNORMAL_QUEUE, true, deliverCallback, consumerTag -> {
        });
    }
}
