package pl1111w.mq.workQueueConfirm;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import pl1111w.mq.util.RabbitChannel;

import java.io.IOException;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/9 18:13
 */
public class WorkQueueConsumerConfirm01 {

    private static final String WORK_QUEUE = "WorkQueueConfirm";

    public static void main(String[] args) {

        Channel channel = RabbitChannel.getRabbitChannel();

        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            //消费信息失败 通知queue
            /**
             *  1：消息标记tag
             *  2：是否批量确认
             *  3：是否重新入队
             */
            channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
            System.out.println("WorkQueueConfirm01");
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };
        //采用手动应答
        boolean autoAck = false;
        try {
            channel.basicConsume(WORK_QUEUE, autoAck, deliverCallback, cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
