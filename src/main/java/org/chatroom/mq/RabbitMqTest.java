package org.chatroom.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author wcl
 */
public class RabbitMqTest {

    /**
     * 队列名称
     */
    public final static String QUEUE_NAME = "Hello.rabbitMQ";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 配置连接属性
        // 虚拟机地址
        factory.setHost("localhost");
        // 端口号
        factory.setPort(5672);
        // 用户名
        factory.setUsername("root");
        // 密码
        factory.setPassword("root");

        // 得到连接，创建通道
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明一个叫Hello.rabbitMQ的队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "Hello RabbitMQ";

        // 发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
