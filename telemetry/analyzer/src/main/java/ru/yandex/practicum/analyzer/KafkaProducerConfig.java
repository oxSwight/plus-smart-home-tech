package ru.yandex.practicum.analyzer;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
public class KafkaProducerConfig {

    private final Environment env;

    public KafkaProducerConfig(Environment env) {
        this.env = env;
    }

    @Bean(destroyMethod = "close")
    public Producer<String, SpecificRecordBase> analyzerProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                env.getProperty("spring.kafka.bootstrap-servers"));
        props.put(ProducerConfig.CLIENT_ID_CONFIG,
                env.getProperty("spring.kafka.producer.client-id", "AnalyzerProducer"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                env.getProperty("spring.kafka.producer.key-serializer"));
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                env.getProperty("spring.kafka.producer.value-serializer"));
        props.put(ProducerConfig.ACKS_CONFIG,
                env.getProperty("spring.kafka.producer.acks", "all"));
        props.put(ProducerConfig.LINGER_MS_CONFIG,
                env.getProperty("spring.kafka.producer.linger-ms", "5"));
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,
                env.getProperty("spring.kafka.producer.compression-type", "snappy"));
        return new KafkaProducer<>(props);
    }
}
