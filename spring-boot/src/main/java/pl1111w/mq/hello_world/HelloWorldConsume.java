package pl1111w.mq.hello_world;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/8 16:42
 */
public class HelloWorldConsume {

    public static final String QUEUE_NAME = "HelloWorld";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.13");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");

        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback=(consumerTag,delivery)->{
            String message= new String(delivery.getBody());
            System.out.println(message);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println("消息消费被中断");
        };

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
