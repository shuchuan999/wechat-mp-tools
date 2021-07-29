package wechat.core.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Author: zhc
 * Description:发送消息
 *              https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Passive_user_reply_message.html
 * Create Time: 2021/7/2
 */
@XmlRootElement(name="xml")//根节点名称为xml
@XmlAccessorType(XmlAccessType.FIELD)//映射所有字段
public class SendMsg {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Content;

    public SendMsg() {
    }

    public SendMsg(ReceiveMsg msg) {
        ToUserName = msg.getFromUserName();
        FromUserName = msg.getToUserName();
        CreateTime = new Date().getTime();
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
