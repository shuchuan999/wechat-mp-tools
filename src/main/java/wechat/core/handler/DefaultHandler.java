package wechat.core.handler;

import wechat.core.constant.MsgType;
import wechat.core.entity.ReceiveMsg;
import wechat.core.entity.SendMsg;
import wechat.core.util.XMLUtil;

import javax.servlet.http.HttpServletResponse;

public interface DefaultHandler {

    default void doHandler(ReceiveMsg msg, HttpServletResponse response) throws Exception{
        SendMsg sendMsg=new SendMsg(msg);
        sendMsg.setMsgType(MsgType.TEXT.getType());
        sendMsg.setContent("服务开发中~请耐心等候哦~");
        XMLUtil.writeXmlToResponse(sendMsg,SendMsg.class,response);
    }
}
