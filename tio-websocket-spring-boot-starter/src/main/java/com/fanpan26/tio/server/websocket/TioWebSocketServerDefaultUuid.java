package com.fanpan26.tio.server.websocket;

import org.tio.core.intf.TioUuid;
import org.tio.utils.hutool.Snowflake;

public class TioWebSocketServerDefaultUuid implements TioUuid {
    private Snowflake snowflake;

    /**
     *
     * @author tanyaowu
     */
    public TioWebSocketServerDefaultUuid(long workerId, long dataCenterId) {
        snowflake = new Snowflake(workerId, dataCenterId);
    }

    /**
     * @return
     * @author tanyaowu
     */
    @Override
    public String uuid() {
        return snowflake.nextId() + "";
    }
}
