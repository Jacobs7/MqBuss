package com.dp.service;
import com.dp.constants.constants;
import org.apache.activemq.ActiveMQConnectionFactory;
import sun.applet.Main;

import javax.jms.*;
import java.util.concurrent.Callable;

public class MqServiceQueue {




    public static void main(String[] args){





            Callable callable = new Callable() {
                @Override
                public Object call() throws Exception {

                    //连接工厂
                    ConnectionFactory connectionFactory;
                    //连接对象
                    Connection connection ;
                    //收发对象
                    Session session;
                    //发消息的目标
                    Destination destination;
                    //消息生产者
                    MessageProducer messageProducer;

                    //连接工厂实例化
                    connectionFactory =new ActiveMQConnectionFactory(constants.MQ_NAME, constants.MQ_PASSWORD,constants.MQ_BROKETURL);

                    //连接对象实例化
                    connection = connectionFactory.createConnection();

                    connection.start();

                    //会话对象实例化
                    session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

                    destination = session.createQueue("my-act");

                    messageProducer = session.createProducer(destination);

                    TextMessage message;

                    int i=0;
                    while(true){

                        message =session.createTextMessage("队列点对点消息"+i+"");

                        messageProducer.send(message);

                        i++;

                        Thread.sleep(2000);
                    }


                }
            };

        try {
            callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
