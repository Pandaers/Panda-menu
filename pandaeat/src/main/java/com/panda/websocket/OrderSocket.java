package com.panda.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.panda.common.response.RespCode;
import com.panda.common.response.ResponseEntity;
import com.panda.mapper.OrderMapper;
import com.panda.model.OrderForCMS;
import com.panda.service.catNameReform.CatNameReform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-30 23:15
 * @Description:
 */

@ServerEndpoint(value = "/getordering")
@Component
public class OrderSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<OrderSocket> webSocketSet = new CopyOnWriteArraySet<OrderSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    boolean flag = true;

    static  OrderMapper orderMapper;

    static  CatNameReform catNameReform;

    @Autowired
    public OrderSocket(OrderMapper orderMapper,  CatNameReform catNameReform){
        this.orderMapper = orderMapper;
        this.catNameReform = catNameReform;
    }

    public OrderSocket(){}


    public List<OrderForCMS> getData(int currentPage,Integer storeid){
        List<OrderForCMS> result = new ArrayList();
        PageHelper.startPage(currentPage, 20);
        result=orderMapper.selectOrderForCMS(storeid);

        if(result==null){
           return null;
        }
        for(OrderForCMS list:result){
            list.setOrdercontent(catNameReform.catNameReform(list.getOrdercontent()));
        }

        return  result;
    }


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("有新连接加入！当前在线人数为" + getOnlineCount());

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }




    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        flag = !flag;
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //群发消息
        for (OrderSocket item : webSocketSet) {
            while (flag ){
                try {
//                   item.sendMessage(message);
                    sendMessage(JSON.toJSONString(getData(1,Integer.parseInt(message))));
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (OrderSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        OrderSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        OrderSocket.onlineCount--;
    }
}
