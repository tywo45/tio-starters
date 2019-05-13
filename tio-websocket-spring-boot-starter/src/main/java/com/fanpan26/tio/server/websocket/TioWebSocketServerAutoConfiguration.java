package com.fanpan26.tio.server.websocket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fyp
 * @crate 2019/5/13 23:34
 * @project tio-starters
 */
@Configuration
@ConditionalOnBean(TioWebSocketServerMarkerConfiguration.Marker.class)
@EnableConfigurationProperties({ TioWebSocketServerProperties.class,
        TioWebSocketServerClusterProperties.class })
public class TioWebSocketServerAutoConfiguration {
}
