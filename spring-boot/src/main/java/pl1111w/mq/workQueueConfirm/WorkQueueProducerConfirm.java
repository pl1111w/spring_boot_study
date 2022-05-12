package pl1111w.mq.workQueueConfirm;

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
public class WorkQueueProducerConfirm {

    private static final String WORK_QUEUE = "WorkQueueConfirm";

    public static void main(String[] args) {


        Channel channel = RabbitChannel.getRabbitChannel();
        try {
            /**
             * 生成一个队列
             * 1.队列名称
             * 2.队列里面的消息是否持久化 默认消息存储在内存中
             * 3.该队列是否只供一个消费者进行消费 是否进行共享 true 可以多个消费者消费
             * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 true 自动删除
             * 5.其他参数
             */
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
