package com.fanpan26.tio.server.websocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tio.cluster.redisson.RedissonTioClusterTopic;


/**
 * @author fyp
 * @crate 2019/5/13 23:34
 * @project tio-starters
 */
@Configuration
@Import(TioWebSocketServerInitializerConfiguration.class)
@ConditionalOnBean(TioWebSocketServerMarkerConfiguration.Marker.class)
@EnableConfigurationProperties({ TioWebSocketServerProperties.class,
        TioWebSocketServerClusterProperties.class,
        TioWebSocketServerClusterProperties.RedisConfig.class })
public class TioWebSocketServerAutoConfiguration {

    /**
     * 消息集群的频道
     * */
    private static final String CLUSTER_TOPIC_CHANNEL = "tio_ws_spring_boot_starter";

    @Autowired(required = false)
    private TioWebSocketMsgHandler tioWebSocketMsgHandler;

    @Autowired(required = false)
    private TioWebSocketIpStatListener tioWebSocketIpStatListener;

    @Autowired(required = false)
    private TioWebSocketGroupListener tioWebSocketGroupListener;

    @Autowired
    private TioWebSocketServerClusterProperties clusterProperties;

    @Autowired
    private TioWebSocketServerClusterProperties.RedisConfig redisConfig;

    @Autowired
    private TioWebSocketServerProperties serverProperties;

    @Autowired(required = false)
    private RedissonTioClusterTopic redissonTioClusterTopic;

    @Bean
    public TioWebSocketServerBootstrap webSocketServerBootstrap() {
        return new TioWebSocketServerBootstrap(serverProperties,
                clusterProperties,
                redissonTioClusterTopic,
                tioWebSocketMsgHandler,
                tioWebSocketIpStatListener,
                tioWebSocketGroupListener);
    }

    @Bean
    @ConditionalOnProperty(value = "tio.websocket.cluster.enabled",havingValue = "true",matchIfMissing = true)
    public RedisInitializer redisInitializer(){
        return new RedisInitializer(redisConfig);
    }

    @Bean
    @ConditionalOnBean(RedisInitializer.class)
    public RedissonTioClusterTopic redissonTioClusterTopic(RedisInitializer redisInitializer) {
        return new RedissonTioClusterTopic(CLUSTER_TOPIC_CHANNEL,redisInitializer.getRedissonClient());
    }



}
