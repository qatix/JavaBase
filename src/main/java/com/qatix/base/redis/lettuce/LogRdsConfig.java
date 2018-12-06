package com.qatix.base.redis.lettuce;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Logan
 * @Date: 31/07/2018 11:44 AM
 */
//@ConfigurationProperties(prefix = "logsrv.redis")
@Data
@AllArgsConstructor
public class LogRdsConfig {

    private String host;

    private int port;

    private String password;

    private int maxTotal;

    private int maxIdle;

    private int minIdle;

    private int maxWaitMillis;

    private int minEvictableIdleTimeMillis;
}