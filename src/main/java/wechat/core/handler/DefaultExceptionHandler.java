package wechat.core.handler;


import wechat.core.constant.MsgType;
import wechat.core.entity.ReceiveMsg;
import wechat.core.entity.SendMsg;
import wechat.core.util.XMLUtil;

import javax.servlet.http.HttpServletResponse;

public interface DefaultExceptionHandler {

    default void handleException(ReceiveMsg msg, HttpServletResponse response, Exception ex){
        SendMsg sendMsg=new SendMsg(msg);
        sendMsg.setMsgType(MsgType.TEXT.getType());
        sendMsg.setContent("微信公众号异常,请稍后再试");
        XMLUtil.writeXmlToResponse(sendMsg,SendMsg.class,response);
    }
}
