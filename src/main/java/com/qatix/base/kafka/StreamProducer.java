package com.qatix.base.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class StreamProducer {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Producer<String, String> producer = new KafkaProducer<>(properties);

        IntStream.rangeClosed(1, 10)
                .boxed()
                .map(number -> new ProducerRecord<>(
                        "druidtest",
                        number.toString(),
                        number.toString()))
                .map(record -> producer.send(record))
                .forEach(result -> printMetadata(result));
        producer.close();
    }

    private static void printMetadata(Future<RecordMetadata> result) {
        try {
            System.out.println("meta:" + result.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
