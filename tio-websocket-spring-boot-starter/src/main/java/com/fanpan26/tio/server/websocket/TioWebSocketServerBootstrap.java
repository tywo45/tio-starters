package com.fanpan26.tio.server.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.cluster.TioClusterConfig;
import org.tio.cluster.redisson.RedissonTioClusterTopic;
import org.tio.core.intf.GroupListener;
import org.tio.core.stat.IpStatListener;
import org.tio.server.ServerGroupContext;
import org.tio.utils.Threads;
import org.tio.websocket.server.WsServerConfig;
import org.tio.websocket.server.WsServerStarter;

import java.io.IOException;

/**
 * @author fanpan26
 * */
public class TioWebSocketServerBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(TioWebSocketServerBootstrap.class);

    private static final String GROUP_CONTEXT_NAME = "tio-websocket-spring-boot-starter";

    private TioWebSocketServerProperties serverProperties;
    private TioWebSocketServerClusterProperties clusterProperties;
    private RedissonTioClusterTopic redissonTioClusterTopic;
    private WsServerConfig wsServerConfig;
    private TioClusterConfig clusterConfig;
    private WsServerStarter wsServerStarter;
    private ServerGroupContext serverGroupContext;
    private TioWebSocketMsgHandler tioWebSocketMsgHandler;
    private IpStatListener ipStatListener;
    private GroupListener groupListener;

    public TioWebSocketServerBootstrap(TioWebSocketServerProperties serverProperties,
                                       TioWebSocketServerClusterProperties clusterProperties,
                                       RedissonTioClusterTopic redissonTioClusterTopic,
                                       TioWebSocketMsgHandler tioWebSocketMsgHandler,
                                       IpStatListener ipStatListener,
                                       GroupListener groupListener) {

        if (tioWebSocketMsgHandler == null){
            throw new RuntimeException("no bean for tio websocket server : TioWebSocketMsgHandler");
        }
        this.serverProperties = serverProperties;
        this.clusterProperties = clusterProperties;
        if(redissonTioClusterTopic == null){
            logger.info("cluster mod closed");
        }
        this.redissonTioClusterTopic = redissonTioClusterTopic;
        this.tioWebSocketMsgHandler = tioWebSocketMsgHandler;
        this.ipStatListener = ipStatListener;
        this.groupListener = groupListener;
    }

    public void contextInitialized() {
        logger.info("initialize tio websocket server");
        try {
            initTioWebSocketConfig();
            initTioWebSocketServer();
            initTioWebSocketServerGroupContext();

            start();
        }
        catch (Throwable e) {
            logger.error("Cannot bootstrap tio websocket server :", e);
            throw new RuntimeException("Cannot bootstrap tio websocket server :", e);
        }
    }

    private void initTioWebSocketConfig() {
        this.wsServerConfig = new WsServerConfig(serverProperties.getPort());
        if (redissonTioClusterTopic != null && clusterProperties.isEnabled()) {
            this.clusterConfig = new TioClusterConfig(redissonTioClusterTopic);
            this.clusterConfig.setCluster4all(clusterProperties.isAll());
            this.clusterConfig.setCluster4bsId(true);
            this.clusterConfig.setCluster4channelId(clusterProperties.isChannel());
            this.clusterConfig.setCluster4group(clusterProperties.isGroup());
            this.clusterConfig.setCluster4ip(clusterProperties.isIp());
            this.clusterConfig.setCluster4user(clusterProperties.isUser());
        }
    }

    private void initTioWebSocketServer() throws Exception {
        wsServerStarter = new WsServerStarter(wsServerConfig,
                tioWebSocketMsgHandler,
                new TioWebSocketServerDefaultUuid(1L,1L),
                Threads.getTioExecutor(),
                Threads.getGroupExecutor());
    }

    private void initTioWebSocketServerGroupContext() {
        serverGroupContext = wsServerStarter.getServerGroupContext();
        serverGroupContext.setName(GROUP_CONTEXT_NAME);
        if (ipStatListener != null) {
            serverGroupContext.setIpStatListener(ipStatListener);
        }
        //serverGroupContext.setServerAioListener();
        if (groupListener != null) {
            serverGroupContext.setGroupListener(groupListener);
        }
        if (serverProperties.getHeartbeatTimeout() > 0) {
            serverGroupContext.setHeartbeatTimeout(serverProperties.getHeartbeatTimeout());
        }
        if (clusterConfig != null) {
            serverGroupContext.setTioClusterConfig(clusterConfig);
        }
    }

    private void start() throws IOException {
        wsServerStarter.start();
    }
}
