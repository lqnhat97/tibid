package org.tibid.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${spring.kafka.topic.name.bidorder}")
    private String topicBidOrderName;

    @Value(value = "${spring.kafka.topic.name.bidticket}")
    private String topicBidTicketName;


    public void sendMessageBidOrderStatus(String message) {

        send(message, topicBidOrderName);
    }

    public void sendMessageBidTicketStatus(String message) {

        send(message, topicBidTicketName);
    }

    private void send(String message, String topicBidOrderName) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicBidOrderName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }



}
