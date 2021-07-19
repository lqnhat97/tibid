package org.tibid.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.topic.name.bidorder}")
    private String bidOrderStatus;

    @Value(value = "${spring.kafka.topic.name.bidticket}")
    private String bidTicketStatus;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        configs.put("security.protocol", "SSL");
//        configs.put("ssl.truststore.location", "/Users/lap13517/tibid/BE/tibid/certificates/store/kafka.keystore.jks");
//        configs.put("ssl.truststore.password", "TEST1234");
//
//        configs.put("ssl.key.password", "TEST1234");
//        configs.put("ssl.keystore.password", "TEST1234");
//        configs.put("ssl.keystore.location", "/Users/lap13517/tibid/BE/tibid/certificates/store/kafka.truststore.jks");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicBidOrderStatus() {
        return new NewTopic(bidOrderStatus, 1, (short) 1);
    }

    @Bean
    public NewTopic topicBidTicketStatus() {
        return new NewTopic(bidTicketStatus, 1, (short) 1);
    }

}
