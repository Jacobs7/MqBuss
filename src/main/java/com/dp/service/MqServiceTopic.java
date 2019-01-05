package com.dp.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.util.concurrent.Callable;

public class MqServiceTopic {


    public static void main(String[] args){
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {

                ConnectionFactory connectionFactory;

                Connection connection;

                Session session;

                Destination destination;

                MessageProducer messageProducer;

                connectionFactory = new ActiveMQConnectionFactory();

                connection = connectionFactory.createConnection();

                connection.start();

                session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

                destination = session.createTopic("my-topic");

                messageProducer =session.createProducer(destination);

                int i = 0;

                while(true){

                    TextMessage textMessage = session.createTextMessage("Hello send topic msg from produtor"+i+"");

                    messageProducer.send(textMessage);

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
