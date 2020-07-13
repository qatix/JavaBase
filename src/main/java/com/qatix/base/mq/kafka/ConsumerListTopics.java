package com.qatix.base.mq.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * test pass
 *
 * @Author: Logan.Tang
 * @Date: 2018/10/25 2:46 PM
 */
public class ConsumerListTopics {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); //must
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");//must
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        Map<String, List<PartitionInfo>> topics = consumer.listTopics();
        for (Map.Entry<String, List<PartitionInfo>> entry : topics.entrySet()) {
            System.out.println("topic:" + entry.getKey());
            for (PartitionInfo partitionInfo : entry.getValue()) {
                System.out.println("\tpartition:" + partitionInfo.toString());
            }
        }
        //结果见尾部
    }
}

/*
topic:javatopic
	partition:Partition(topic = javatopic, partition = 0, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
topic:__consumer_offsets
	partition:Partition(topic = __consumer_offsets, partition = 23, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 41, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 32, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 8, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 17, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 44, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 35, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 26, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 11, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 29, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 38, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 47, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 20, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 2, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 5, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 14, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 46, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 49, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 40, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 4, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 13, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 22, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 31, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 16, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 7, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 43, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 25, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 34, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 10, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 37, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 1, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 19, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 28, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 45, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 36, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 27, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 9, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 18, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 21, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 48, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 12, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 3, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 30, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 39, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 15, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 42, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 24, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 33, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 6, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
	partition:Partition(topic = __consumer_offsets, partition = 0, leader = 0, replicas = [0], isr = [0], offlineReplicas = [])
  */