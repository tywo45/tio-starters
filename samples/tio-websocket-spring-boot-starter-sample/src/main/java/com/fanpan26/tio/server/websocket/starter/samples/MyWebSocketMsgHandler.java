package com.fanpan26.tio.server.websocket.starter.samples;

import com.fanpan26.tio.server.websocket.TioWebSocketMsgHandler;
import com.fanpan26.tio.server.websocket.WebSocketMsgHandler;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;

@WebSocketMsgHandler
public class MyWebSocketMsgHandler implements TioWebSocketMsgHandler {

    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        return httpResponse;
    }

    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        System.out.println("握手成功");
    }

    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        System.out.println("接收到bytes消息");
        return null;
    }

    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    @Override
    public Object onText(WsRequest wsRequest, String s, ChannelContext channelContext) throws Exception {
        System.out.println("接收到文本消息："+s);
        return "服务器收到了消息："+s;
    }
}
