package pl1111w.mq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/9 18:11
 */
public class RabbitChannel {


    public static Channel getRabbitChannel() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.13");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
        Channel channel = null;
        try {
            Connection connection = connectionFactory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }

}
