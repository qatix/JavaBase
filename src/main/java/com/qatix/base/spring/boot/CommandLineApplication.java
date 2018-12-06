package com.qatix.base.spring.boot;

//import com.qatix.base.entity.ChannelEntity;
//import com.qatix.base.service.ChannelService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.List;
//
///**
// * @Author: Logan.Tang
// * @Date: 2018/12/4 5:55 PM
// */
//@Slf4j
//@SpringBootApplication
//public class CommandLineApplication implements CommandLineRunner {
//
//    @Autowired
//    private ChannelService channelService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        log.info("run....");
//        List<ChannelEntity> channels = channelService.selectList(null);
//        System.out.println(channels.size());
//        System.exit(1);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(CommandLineApplication.class, args);
//    }
//}
