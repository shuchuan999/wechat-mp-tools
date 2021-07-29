package wechat.core.handler;

import org.springframework.stereotype.Component;
import wechat.core.constant.WeChatConstant;
import wechat.core.entity.ReceiveMsg;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: zhc
 * Description:默认事件处理
 *              所有方法调用默认方法,这样配置了默认方法均可生效
 * Create Time: 2021/7/11
 */
@Component
public class DefaultEventHandler implements DefaultHandler {

    @Override
    public void doHandler(ReceiveMsg msg, HttpServletResponse response) throws Exception {
        switch (msg.getEvent()){
            case WeChatConstant.EventType.SUBSCRIBE:doSubscribe(msg, response);break;
            case WeChatConstant.EventType.UNSUBSCRIBE:doUnsubscribe(msg, response);break;
            case WeChatConstant.EventType.CLICK:doClick(msg, response);break;
            case WeChatConstant.EventType.TEMPLATESENDJOBFINISH:doTemplateSendFinish(msg, response);break;
            default:defaultHandler(msg, response);
        }
    }

    //事件默认处理
    public void defaultHandler(ReceiveMsg msg, HttpServletResponse response) throws Exception{
        new DefaultHandler(){}.doHandler(msg, response);
    }

    //关注事件
    public void doSubscribe(ReceiveMsg msg, HttpServletResponse response) throws Exception{
        defaultHandler(msg, response);
    }

    //取关事件
    public void doUnsubscribe(ReceiveMsg msg, HttpServletResponse response) throws Exception{
        defaultHandler(msg, response);
    }

    //菜单点击事件
    public void doClick(ReceiveMsg msg, HttpServletResponse response) throws Exception{
        defaultHandler(msg, response);
    }

    //发送模板消息完成事件 Status:WeChatConstant.TemplateStatus
    //需要注意该事件无法做回复
    public void doTemplateSendFinish(ReceiveMsg msg, HttpServletResponse response) throws Exception{
        switch (msg.getStatus()){
            case WeChatConstant.TemplateStatus.SUCCESS:
                //doSuccess
                break;
            case WeChatConstant.TemplateStatus.BLOCK:
                //doBlock
                break;
            case WeChatConstant.TemplateStatus.FAILED:
                //doFailed
                break;
        }
        defaultHandler(msg, response);
    }


}
