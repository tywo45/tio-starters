# tio-starters
tio spring boot starter，仅供学习，未发布到maven仓库。

## 快速开始
* 引入 jar 包
```
        <dependency>
            <groupId>com.fanpan26</groupId>
            <artifactId>tio-websocket-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

* 添加注解
```
@SpringBootApplication
@EnableTioWebSocketServer
public class SamplesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SamplesApplication.class,args);
    }
}
```

* 修改配置文件
```
tio:
  websocket:
    server:
      port: 9876
      heartbeat-timeout: 60000
      #是否支持集群，集群开启需要redis
    cluster:
      enabled: false
    redis:
      ip: 192.168.1.225
      port: 6379
```

* 编写消息处理类
```
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
        return null;
    }
}

```

* 编写简单客户端
```
<script>
        var ws =new WebSocket("ws://localhost:9876");
        ws.onopen = function (event) {
            console.log("opened");
            ws.send("Hello Tio WebSocket");
        }
        ws.onmessage=function (p1) {
            console.log(p1.data);
        }
    </script>

```

* 运行程序，打开浏览器,Console 打印
```
opened
服务器收到了消息：Hello Tio WebSocket
```
* 你学会了吗？