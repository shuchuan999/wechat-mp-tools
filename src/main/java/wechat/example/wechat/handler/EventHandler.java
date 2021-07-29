package wechat.example.wechat.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wechat.core.constant.MsgType;
import wechat.core.constant.WeChatConstant;
import wechat.core.entity.ReceiveMsg;
import wechat.core.entity.SendMsg;
import wechat.core.handler.DefaultEventHandler;
import wechat.core.handler.DefaultHandler;
import wechat.core.util.XMLUtil;
import wechat.example.service.CommonService;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: zhc
 * Description:事件推送处理
 *                  https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_event_pushes.html
 * Create Time: 2021/7/8
 */
@Component
public class EventHandler extends DefaultEventHandler {

    @Autowired
    private CommonService commonService;

    /**
     * Author: zhc
     * Description:用户关注事件
     *                  返回语句，查询用户详情，新增用户详情
     * Create Time: 2021/7/8
     */
    @Override
    public void doSubscribe(ReceiveMsg msg, HttpServletResponse response){
        SendMsg sendMsg=new SendMsg(msg);
        sendMsg.setMsgType(MsgType.TEXT.getType());
        sendMsg.setContent("欢迎关注我的公众号！");
        XMLUtil.writeXmlToResponse(sendMsg,SendMsg.class,response);
        commonService.insertUser(msg.getFromUserName());
    }

    /**
     * Author: zhc
     * Description:用户取关事件
     *              删除本地微信用户信息
     * Create Time: 2021/7/8
     */
    @Override
    public void doUnsubscribe(ReceiveMsg msg, HttpServletResponse response){
        commonService.deleteUser(msg.getFromUserName());
    }

    /**
     * Author: zhc
     * Description:模板消息推送完成事件
     * Create Time: 2021/7/12
     */
    @Override
    public void doTemplateSendFinish(ReceiveMsg msg, HttpServletResponse response) {
        Integer sendStatus=null;
        switch (msg.getStatus()){
            case WeChatConstant.TemplateStatus.SUCCESS:
                sendStatus=1;
                break;
            case WeChatConstant.TemplateStatus.BLOCK:
                sendStatus=2;
                break;
            case WeChatConstant.TemplateStatus.FAILED:
                sendStatus=3;
                break;
        }
        //do Insert
    }

    /**
     * Author: zhc
     * Description:菜单点击事件
     * Create Time: 2021/7/8
     */
    @Override
    public void doClick(ReceiveMsg msg, HttpServletResponse response) throws Exception{
        switch (msg.getEventKey()){
            case "my_menu1":menu1Click(msg,response);break;
            default:new DefaultHandler(){}.doHandler(msg,response);
        }
    }

    public void menu1Click(ReceiveMsg msg, HttpServletResponse response){
        SendMsg sendMsg=new SendMsg(msg);
        sendMsg.setMsgType(MsgType.TEXT.getType());
        sendMsg.setContent("您的openid为:\n"+msg.getFromUserName());
        XMLUtil.writeXmlToResponse(sendMsg,SendMsg.class,response);
    }
}
