package org.tibid.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    private CountDownLatch latch = new CountDownLatch(3);


    @KafkaListener(topics = "${kafka.topic.name.bidorder}", groupId="order",containerFactory = "orderKafkaListenerContainerFactory")
    public void listenBidOrderStatus(String message) {
        LOGGER.info("Received Message: " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${kafka.topic.name.bidticket}",groupId="ticket",containerFactory = "ticketKafkaListenerContainerFactory")
    public void listenBidTicketStatus(String message) {
        LOGGER.info("Received Message: " + message);
        latch.countDown();
    }

}
