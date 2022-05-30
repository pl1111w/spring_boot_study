package pl1111w.mq.ttl.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/26 14:51
 */
@Slf4j
@Component
public class MessageListener {

    @RabbitListener(queues = "QD")
    public void receiveD(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到死信队列信息: {}", LocalDateTime.now().toString(), msg);
    }
}
