package com.fanpan26.tio.server.websocket.starter.samples;


import com.fanpan26.tio.server.websocket.EnableTioWebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTioWebSocketServer
public class SamplesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SamplesApplication.class,args);
    }
}
