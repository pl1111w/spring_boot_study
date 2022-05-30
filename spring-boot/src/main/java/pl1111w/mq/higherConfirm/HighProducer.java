package pl1111w.mq.higherConfirm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static pl1111w.mq.higherConfirm.HighComponent.CONFIRM_EXCHANGE_NAME;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/5/29 18:27
 */
@Slf4j
@RestController
public class HighProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("hc/{message}")
    public void sendMessage(@PathVariable String message) {
        //指定消息 id 为 1
        CorrelationData correlationData1 = new CorrelationData("1");
        String routingKey = "key1";
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, routingKey, message + routingKey, correlationData1);

        /**
         * 交换机出错 ack=false
         */
        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME + "00", routingKey, message + routingKey, correlationData2);

        /**
         *  RoutingKey 与队列的 BindingKey 不一致，也没有其它队列能接收这个消息，所有第二条
         * 消息被直接丢弃了 但回调ack=true
         */
//        routingKey = "key2";
//        CorrelationData correlationData3 = new CorrelationData("3");
//        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, routingKey, message + routingKey, correlationData3);

        log.info("发送消息内容:{}", message);
    }
}
