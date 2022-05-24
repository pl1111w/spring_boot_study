package pl1111w.mq.dead;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/23 21:50
 */
public class DeadNormalConsumer {

    public final static String NORMAL_QUEUE = "normal_queue";
    public final static String NORMAL_EXCHANGE = "normal_exchange";
    public final static String ABNORMAL_EXCHANGE = "abnormal_exchange";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitChannel.getRabbitChannel();
        Map<String, Object> params = new HashMap<>();
        //正常队列设置死信交换机 参数 key 是固定值
        params.put("x-dead-letter-exchange", ABNORMAL_EXCHANGE);
        //正常队列设置死信 routing-key 参数 key 是固定值
        params.put("x-dead-letter-routing-key", "abnormal");
        params.put("x-max-length", 10);
        channel.queueDeclare(NORMAL_QUEUE, false, false, false, params);
        channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "normal");

        System.out.println("等待接收消息.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            if ("77".equals(message)) {
                System.out.println("normal-拒绝消息读取：" + message);
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(),false);
            }else {
                System.out.println("normal-消息读取成功：" + message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        };
        boolean autoAck =false;
        channel.basicConsume(NORMAL_QUEUE, autoAck, deliverCallback, consumerTag -> {
        });

    }
}
