package com.qatix.base.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * test pass
 *
 * @Author: Logan.Tang
 * @Date: 2018/10/25 2:46 PM
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-group-id");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("auto.offset.reset", "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        String mytopic = "javatopic";
        consumer.subscribe(Collections.singletonList(mytopic));

        System.out.println("Subscribed to topic " + mytopic);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("offset = %s, key = %s, value = %s", record.offset(), record.key(), record.value()));
                consumer.commitAsync();
            }
        }
    }
}
