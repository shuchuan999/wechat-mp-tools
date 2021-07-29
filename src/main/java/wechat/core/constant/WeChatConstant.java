package wechat.core.constant;

public class WeChatConstant {

    public static final String JOB="TokenJob";
    public static final String JOB_GROUP="TokenJobGroup";
    public static final String TRIGGER="TokenTrigger";
    public static final String TRIGGER_GROUP="TokenTriggerGroup";

    public static final String GET="GET";
    public static final String POST="POST";

    public class EventType{
        public static final String SUBSCRIBE="subscribe";//关注事件
        public static final String UNSUBSCRIBE="unsubscribe";//取关事件
        public static final String CLICK="CLICK";//菜单点击事件
        public static final String TEMPLATESENDJOBFINISH="TEMPLATESENDJOBFINISH";//模板消息发送完成事件
    }

    public class TemplateStatus{
        public static final String SUCCESS="success";//发送成功
        public static final String BLOCK="failed:user block";//用户拒收
        public static final String FAILED="failed: system failed";//其他原因失败
    }
}
