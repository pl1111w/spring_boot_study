package pl1111w.mq.unfair;

import com.rabbitmq.client.Channel;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;
import java.util.Scanner;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/17 12:35
 */
public class DistributionProducer {

    private final static String UNFAIR_QUEUE = "unfair_queue";

    public static void main(String[] args) {
        Channel channel = RabbitChannel.getRabbitChannel();
        try {
            channel.queueDeclare(UNFAIR_QUEUE, false, false, false, null);
            Scanner scanner = new Scanner(System.in);
            String message;
            while (scanner.hasNext()) {
                message = scanner.next();
                channel.basicPublish("", UNFAIR_QUEUE, null, message.getBytes());
                System.out.println("send message: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
