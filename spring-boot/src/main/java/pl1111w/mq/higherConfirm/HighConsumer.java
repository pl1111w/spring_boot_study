package pl1111w.mq.higherConfirm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/29 18:28
 */
@Slf4j
@Component
public class HighConsumer {

    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";

    @RabbitListener(queues = CONFIRM_QUEUE_NAME)
    public void receiveMsg(Message message) {
        String msg = new String(message.getBody());
        log.info("接受到队列 confirm.queue 消息:{}", msg);
    }
}
