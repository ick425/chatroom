package org.chatroom.webscoket;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单websocket实现的聊天室
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
@Slf4j
public class WebsocketTest {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
     */
    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象
     */
    private static ConcurrentHashMap<String, WebsocketTest> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 当前连接用户昵称
     */
    private String userId;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) throws Throwable {
        this.session = session;
        if (webSocketMap.containsKey(userId)) {
            // 限制当前人登录
            sendMessage("当前昵称已存在，请修改昵称");
            session.close();
            return;
        }
        this.userId = userId;
        log.info("session id:{}", session.getId());
        //加入map
        webSocketMap.put(userId, this);
        addOnlineCount();           //在线数加1
        log.info("【{}】进入聊天室,当前在线人数：{}", userId, getOnlineCount());
        sendGroupMsg(String.format("【%s】进入聊天室,当前在线人数：%d", userId, getOnlineCount()));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从map中删除
        if (userId == null) {
            return;
        }
        webSocketMap.remove(userId);
        subOnlineCount();           //在线数减1
        log.info("【{}】退出聊天室！当前在线人数为：{}", userId, getOnlineCount());
        sendGroupMsg(String.format("【%s】退出聊天室！当前在线人数为：%s", userId, getOnlineCount()));
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端用户：{} 消息:{}", userId, message);
        //群发消息
        sendGroupMsgExceptUserId(String.format("【%s】：%s", userId, message), userId);
    }

    /**
     * 发生错误时调用
     *
     * @OnError
     */
    @OnError
    public void onError(Session session, Throwable error) throws Throwable {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        throw error;
    }

    /**
     * 向客户端发送消息
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("websocket发送消息异常：", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过userId向客户端发送消息
     */
    public void sendMsgByUserId(String userId, String message) {
        log.info("服务端发送消息到{},消息：{}", userId, message);
        if (StrUtil.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            log.error("用户{}不在线", userId);
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendGroupMsg(String message) {
        for (String item : webSocketMap.keySet()) {
            webSocketMap.get(item).sendMessage(message);
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendGroupMsgExceptUserId(String message, String userId) {
        for (String item : webSocketMap.keySet()) {
            if (item.equals(userId)) {
                continue;
            }
            webSocketMap.get(item).sendMessage(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }

}

