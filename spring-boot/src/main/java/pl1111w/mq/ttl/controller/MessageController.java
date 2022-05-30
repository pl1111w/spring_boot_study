package pl1111w.mq.ttl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/26 14:49
 */
@RestController
@Slf4j
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("ttl/{message}")
    public void sendMsg(@PathVariable String message) {
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", LocalDateTime.now(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列: " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 40S 的队列: " + message);
    }

    @RequestMapping("ttl2/{message}/{ttlTime}")
    public void sendMsg2(@PathVariable String message, @PathVariable String ttlTime) {
        rabbitTemplate.convertAndSend("X", "XC", message, correlationData -> {
            correlationData.getMessageProperties().setExpiration(ttlTime);
            return correlationData;
        });

        log.info("当前时间：{},发送一条时长{}毫秒 TTL 信息给队列 C:{}", LocalDateTime.now(), ttlTime, message);
    }
}
