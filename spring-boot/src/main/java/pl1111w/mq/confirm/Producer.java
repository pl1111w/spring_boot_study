package pl1111w.mq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import pl1111w.mq.util.RabbitChannel;

/**
 * @title: pl1111w
 * @description: //逐条确认 批量确认 异步确认
 * @author: Kris
 * @date 2022/5/17 16:57
 */
public class Producer {

    public static final int PATCH_COUNT = 2000;

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.singleConfirm();
        producer.batchConfirm();
        producer.asyConfirm();
    }

    public void batchConfirm() {
        Channel channel = RabbitChannel.getRabbitChannel();
        long startTime = System.currentTimeMillis();
        String UUID = java.util.UUID.randomUUID().toString();
        try {
            channel.confirmSelect();
            channel.queueDeclare(UUID, false, false, false, null);
            for (int i = 0; i < PATCH_COUNT; i++) {
                channel.basicPublish("", UUID, null, (i + "").getBytes());
                if (i % 200 == 0) {
                    channel.waitForConfirms();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("batchConfirm total:" + (endTime - startTime));
    }

    public void singleConfirm() {
        Channel channel = RabbitChannel.getRabbitChannel();
        long startTime = System.currentTimeMillis();
        String UUID = java.util.UUID.randomUUID().toString();
        try {
            channel.confirmSelect();
            channel.queueDeclare(UUID, false, false, false, null);
            for (int i = 0; i < PATCH_COUNT; i++) {
                channel.basicPublish("", UUID, null, (i + "").getBytes());
                boolean b = channel.waitForConfirms();
                if (b) {
                    System.out.println("success: " + i);
                } else {
                    System.out.println("failed: " + i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("singleConfirm total:" + (endTime - startTime));
    }

    public void asyConfirm() {
        Channel channel = RabbitChannel.getRabbitChannel();
        long startTime = System.currentTimeMillis();
        String UUID = java.util.UUID.randomUUID().toString();
        ConfirmCallback ackCallback = (long var, boolean var3) -> {
            System.out.println("ackCallback: " + var);
        };
        ConfirmCallback nackCallback = (long var, boolean var3) -> {
            System.out.println("nackCallback: " + var);
        };
        channel.addConfirmListener(ackCallback, nackCallback);
        try {
            channel.confirmSelect();
            channel.queueDeclare(UUID, false, false, false, null);
            for (int i = 0; i < PATCH_COUNT; i++) {
                channel.basicPublish("", UUID, null, (i + "").getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("asyConfirm total:" + (endTime - startTime));
    }
}
