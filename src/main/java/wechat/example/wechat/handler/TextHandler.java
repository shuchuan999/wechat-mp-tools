package wechat.example.wechat.handler;

import wechat.core.constant.MsgType;
import wechat.core.entity.ReceiveMsg;
import wechat.core.entity.SendMsg;
import wechat.core.handler.DefaultHandler;
import wechat.core.util.XMLUtil;

import javax.servlet.http.HttpServletResponse;

public class TextHandler implements DefaultHandler {
    @Override
    public void doHandler(ReceiveMsg msg, HttpServletResponse response) {
        SendMsg sendMsg=new SendMsg(msg);
        sendMsg.setContent("您好，欢迎关注本公众号!");
        sendMsg.setMsgType(MsgType.TEXT.getType());
        XMLUtil.writeXmlToResponse(sendMsg,SendMsg.class,response);
    }
}
