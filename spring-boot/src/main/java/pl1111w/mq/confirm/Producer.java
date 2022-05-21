package pl1111w.mq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import pl1111w.mq.util.RabbitChannel;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

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
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();
        ConfirmCallback ackCallback = (long sequenceNumber, boolean multiple) -> {
            if (multiple) {//批量确认
                //返回的是小于等于当前序列号的未确认消息 是一个 map
                ConcurrentNavigableMap<Long, String> confirmed =
                        outstandingConfirms.headMap(sequenceNumber, true);
                //清除该部分未确认消息
                confirmed.clear();
            }else{
                //只清除当前序列号的消息
                outstandingConfirms.remove(sequenceNumber);
            }
            System.out.println("ackCallback: " + sequenceNumber);
        };
        ConfirmCallback nackCallback = (long var, boolean var3) -> {
            String message = outstandingConfirms.get(var);
            System.out.println("nackCallback: " + message);
        };
        channel.addConfirmListener(ackCallback, nackCallback);
        try {
            channel.confirmSelect();
            channel.queueDeclare(UUID, false, false, false, null);
            for (int i = 0; i < PATCH_COUNT; i++) {
                outstandingConfirms.put(channel.getNextPublishSeqNo(), String.valueOf(i));
                channel.basicPublish("", UUID, null, (i + "").getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("asyConfirm total:" + (endTime - startTime));
    }
}
