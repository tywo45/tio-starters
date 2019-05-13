package com.fanpan26.tio.server.websocket;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.fanpan26.tio.server.websocket.TioWebSocketServerClusterProperties.PREFIX;

/**
 * @author fyp
 * @crate 2019/5/13 23:36
 * @project tio-starters
 */
@ConfigurationProperties(PREFIX)
public class TioWebSocketServerClusterProperties {
    public static final String PREFIX = "tio.websocket.cluster";
}
