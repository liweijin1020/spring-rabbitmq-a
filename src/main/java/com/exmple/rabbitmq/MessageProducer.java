package com.exmple.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/***
 * 消息生产者
 * @author norhtking
 */
public class MessageProducer {
    public static void main(String[] args) {
        /**1加载配置文件*/
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/rabbitmq-context.xml");
        /**2定义rabbitmq模板*/
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        /**3发送消息*/
        String date = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
        HashMap<String, Object> msg = new HashMap<String, Object>();
        msg.put("type","1");
        msg.put("date",date);
        rabbitTemplate.convertAndSend(JSON.toJSON(msg));
        //rabbitTemplate.convertAndSend("type", JSON.toJSON(msg));
        /**4销毁*/
        context.destroy();
    }
}
