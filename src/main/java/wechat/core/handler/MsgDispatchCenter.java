package wechat.core.handler;


import wechat.core.constant.MsgType;
import wechat.core.entity.ReceiveMsg;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhc
 * Description:消息调度中心
 *              将微信服务器推送过来的消息转发给对应的消息处理类
 * Create Time: 2021/7/7
 */
public class MsgDispatchCenter {

    private Map<String,DefaultHandler> handlerMap;
    private ExceptionHandler exceptionHandler;

    public void doHandler(ReceiveMsg msg, HttpServletResponse response) {
        try {
            if(handlerMap.containsKey(msg.getMsgType())) handlerMap.get(msg.getMsgType()).doHandler(msg, response);//转发操作
            else new DefaultHandler(){}.doHandler(msg, response);//没有则使用默认的回复机制
        } catch (Exception e) {
            exceptionHandler.handleException(msg, response, e);//异常回复
        }
    }

    public MsgDispatchCenter() {
        handlerMap = new HashMap<>();
        for (MsgType msgType : MsgType.values()) {
            if (msgType.getScope().contains("in")) handlerMap.put(msgType.getType(), new DefaultHandler() {
            });
        }
        exceptionHandler = new ExceptionHandler();
    }

    public void setHandler(DefaultHandler handler,MsgType type){
        if(handlerMap.containsKey(type.getType())) handlerMap.replace(type.getType(),handler);
        else throw new RuntimeException("No Such MsgType!");
    }

    public ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }
}
