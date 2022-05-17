package pl1111w.mq.durable;

import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;
import java.util.Scanner;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/16 15:55
 */
public class PersistQueueProducer {

    private static final String DURABLE_QUEUE = "PersistentQueue";

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
            channel.queueDeclare(DURABLE_QUEUE, true, false, false, null);
            Scanner scanner = new Scanner(System.in);
            String message;
            while (scanner.hasNext()) {
                message = scanner.next();
                //MessageProperties.PERSISTENT_TEXT_PLAIN：消息持久化
                channel.basicPublish("", DURABLE_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
                System.out.println("send message: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("消息发送完毕");


    }
}
