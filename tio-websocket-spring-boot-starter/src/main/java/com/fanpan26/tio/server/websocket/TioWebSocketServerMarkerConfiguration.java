package com.fanpan26.tio.server.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fyp
 * @crate 2019/5/13 23:33
 * @project tio-starters
 */

@Configuration
public class TioWebSocketServerMarkerConfiguration {

    @Bean
    public Marker tioWebSocketServerMarkBean() {
        return new Marker();
    }

    class Marker {
    }
}