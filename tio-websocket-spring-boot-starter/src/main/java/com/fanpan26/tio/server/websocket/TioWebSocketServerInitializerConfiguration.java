package com.fanpan26.tio.server.websocket;

import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author fyp
 * @crate 2019/5/13 23:42
 * @project tio-starters
 */
@Configuration
public class TioWebSocketServerInitializerConfiguration
        implements SmartLifecycle, Ordered {

    private int order = 1;
    private boolean running = false;


    @Override
    public void start() {
        new Thread(() -> {

        }).start();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public int getOrder() {
        return order;
    }
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {

    }

}
