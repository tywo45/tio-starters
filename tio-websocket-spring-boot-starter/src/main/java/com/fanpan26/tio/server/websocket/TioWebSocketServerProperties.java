package com.fanpan26.tio.server.websocket;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.fanpan26.tio.server.websocket.TioWebSocketServerProperties.PREFIX;

/**
 * @author fyp
 * @crate 2019/5/13 23:36
 * @project tio-starters
 */
@ConfigurationProperties(PREFIX)
public class TioWebSocketServerProperties {
    public static final String PREFIX = "tio.websocket.server";

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getHeartbeatTimeout() {
        return heartbeatTimeout;
    }

    public void setHeartbeatTimeout(Integer heartbeatTimeout) {
        this.heartbeatTimeout = heartbeatTimeout;
    }

    private Integer port = 9876;
    private Integer heartbeatTimeout = 60000;


}
