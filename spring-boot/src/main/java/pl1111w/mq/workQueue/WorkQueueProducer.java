package pl1111w.mq.workQueue;

import com.rabbitmq.client.Channel;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;
import java.util.Scanner;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/9 18:13
 */
public class WorkQueueProducer {

    private static final String WORK_QUEUE = "WorkQueue";

    public static void main(String[] args) {


        Channel channel = RabbitChannel.getRabbitChannel();
        try {
            channel.queueDeclare(WORK_QUEUE, false, false, false, null);
            Scanner scanner = new Scanner(System.in);
            String message;
            while (scanner.hasNext()) {
                message = scanner.next();
                channel.basicPublish("", WORK_QUEUE, null, message.getBytes());
                System.out.println("send message: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("消息发送完毕");


    }


}
