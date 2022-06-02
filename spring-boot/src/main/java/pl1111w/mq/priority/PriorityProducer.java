package pl1111w.mq.priority;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/6/2 9:53
 */
public class PriorityProducer {
    private static final String PRIORITY_QUEUE = "priority_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitChannel.getRabbitChannel();
        Map<String, Object> params = new HashMap();
        params.put("x-max-priority", 10);
        channel.queueDeclare(PRIORITY_QUEUE, false, false, false, params);
        for (int i = 0; i < 10; i++) {
            String message = i + "";
            if (i == 7) {
                AMQP.BasicProperties properties = new
                        AMQP.BasicProperties().builder().priority(8).build();
                channel.basicPublish("", PRIORITY_QUEUE, properties, message.getBytes());
            } else {
                channel.basicPublish("", PRIORITY_QUEUE, null, message.getBytes());

            }
        }
    }
}
