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
public class HighWarningConsumer {

    public static final String WARNING_QUEUE_NAME = "warning.queue";

    @RabbitListener(queues = WARNING_QUEUE_NAME)
    public void receiveMsg(Message message) {
        String msg = new String(message.getBody());
        log.info("warning fund not exchange message: {}", msg);
    }
}
