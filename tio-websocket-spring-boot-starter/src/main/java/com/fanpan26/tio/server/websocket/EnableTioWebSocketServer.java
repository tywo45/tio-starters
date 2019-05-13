package com.fanpan26.tio.server.websocket;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author fyp
 * @crate 2019/5/13 23:31
 * @project tio-starters
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(TioWebSocketServerMarkerConfiguration.class)
public @interface EnableTioWebSocketServer {
}
