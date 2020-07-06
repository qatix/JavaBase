package com.qatix.base.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/25 2:18 PM
 */
public class ProducerStream {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();

        props.put("bootstrap.servers", "127.0.0.1:9092");//该地址是集群的子集，用来探测集群。
        props.put("acks", "all");// 记录完整提交，最慢的但是最大可能的持久化
        props.put("retries", 3);// 请求失败重试的次数
        props.put("batch.size", 16384);// batch的大小
        props.put("linger.ms", 1);// 默认情况即使缓冲区有剩余的空间，也会立即发送请求，设置一段时间用来等待从而将缓冲区填的更多，单位为毫秒，producer发送数据会延迟1ms，可以减少发送到kafka服务器的请求数据
        props.put("buffer.memory", 33554432);// 提供给生产者缓冲内存总量
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        String topic = "dutest";

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        int i = 1;
        while (i < 100000) {
            JSONObject jo = new JSONObject();
            jo.put("name", "test-" + i);
            jo.put("price", RandomUtils.nextDouble(100, 3000));
            jo.put("no", RandomUtils.nextInt(100000, 999999));
            jo.put("timestamp", System.currentTimeMillis());
            producer.send(new ProducerRecord<>(topic, JSON.toJSONString(jo)));
            i++;
            Thread.sleep(400);
            System.out.println(i + " >> " + JSON.toJSONString(jo));
        }
    }
}

