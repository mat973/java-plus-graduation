package ru.practicum.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@Getter
@Setter
public class KafkaConfig {

    @Value("${analyzer.kafka.bootstrapServers}")
    private String bootstrapServers;

    @Value("${analyzer.kafka.consumer.key-deserializer}")
    private String keyDeserializer;

    @Value("${analyzer.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${analyzer.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;

    @Value("${analyzer.kafka.action.group-id}")
    private String actionGroupId;

    @Value("${analyzer.kafka.consumer.actions.value-deserializer}")
    private String actionValueDeserializer;

    @Value("${analyzer.kafka.event.group-id}")
    private String eventGroupId;

    @Value("${analyzer.kafka.consumer.events.value-deserializer}")
    private String eventValueDeserializer;

    @Value("${spring.kafka.consumer.poll-timeout-ms:1000}")
    private int pollTimeoutMs;


    @Bean
    public Properties actionConsumerProps() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, actionValueDeserializer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, actionGroupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 200);
        return props;
    }


    @Bean
    public Properties eventConsumerProps() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, eventValueDeserializer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, eventGroupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);
        return props;
    }
}
