package pl1111w.mq.dead;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;
import java.util.Scanner;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/23 21:50
 */
public class DeadProducer {

    public final static String NORMAL_EXCHANGE = "normal_exchange";
    public final static String ABNORMAL_EXCHANGE = "abnormal_exchange";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitChannel.getRabbitChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT, false, false, null);
        //设置过期时间10s
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        Scanner scanner = new Scanner(System.in);
        String message;
        while (scanner.hasNext()) {
            message = scanner.next();
            channel.basicPublish(NORMAL_EXCHANGE, "normal", properties, message.getBytes());
            System.out.println("send message: " + message);
        }
    }
}
