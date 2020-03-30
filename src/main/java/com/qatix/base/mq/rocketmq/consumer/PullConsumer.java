package com.qatix.base.mq.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.PullResultExt;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class PullConsumer {
    //    private static final Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();
    private static ConsumerOffsetConfig consumerOffsetConfig;

    public static final String CONFIG_FILE_NAME = "./tmp/consumer-config011.json";

    private static boolean configInitialized = false;

    public static void main(String[] args) throws Exception {
        String nameSrv = "127.0.0.1:9876";
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("TestPullConsumer");
        consumer.setNamesrvAddr(nameSrv);
        consumer.start();

        try {
            while (true) {
                Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("test_task");
                for (MessageQueue mq : mqs) {
                    System.out.println("Consume from the queue: " + mq);
                    // long offset = consumer.fetchConsumeOffset(mq, true);
                    // PullResultExt pullResult =(PullResultExt)consumer.pull(mq, null, getMessageQueueOffset(mq), 32);
                    //消息未到达默认是阻塞10秒，private long consumerPullTimeoutMillis = 1000 * 10;
                    PullResultExt pullResult = (PullResultExt) consumer.pullBlockIfNotFound(mq, "image_process2", getMessageQueueOffset(mq), 3);
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    log.info("pull-status:{}", pullResult.getPullStatus());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                            log.info("get-msg-size:{}", messageExtList.size());
                            for (MessageExt m : messageExtList) {
                                log.info("msg:{}", new String(m.getBody()));
                            }
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                    Thread.sleep(2000);
                }
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } finally {
            consumer.shutdown();
        }
    }


    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        consumerOffsetConfig.getOffsetTable().put(mq, offset);
        try {
            syncOffsetToLocalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        if (!configInitialized) {
            try {
                initFromLocalFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Long offset = consumerOffsetConfig.getOffsetTable().get(mq);
        if (offset != null) {
            return offset;
        }
        return 0;
    }

    private static void syncOffsetToLocalFile() throws IOException {
        File configFile = new File(CONFIG_FILE_NAME);
        FileWriter fw = new FileWriter(configFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(JSON.toJSONString(consumerOffsetConfig));
        bw.close();
        fw.close();
        log.info("sync done");
    }

    private static void initFromLocalFile() throws IOException {
        configInitialized = true;
        File configFile = new File(CONFIG_FILE_NAME);
        if (!configFile.exists()) {
            consumerOffsetConfig = new ConsumerOffsetConfig();
            consumerOffsetConfig.setOffsetTable(new HashMap<>());
            log.info("empty-config-file initial ram");
            return;
        }
        FileReader fr = new FileReader(configFile);
        BufferedReader br = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        br.close();
        fr.close();

        consumerOffsetConfig = JSON.parseObject(sb.toString(), ConsumerOffsetConfig.class);
        log.info("load-config-done");
        log.info("history-config:{}", consumerOffsetConfig.toString());
    }
}


