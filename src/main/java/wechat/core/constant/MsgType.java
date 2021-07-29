package wechat.core.constant;

/**
 * Author: zhc
 * Description:回复类型
 * Create Time: 2021/7/5
 */
public enum MsgType {

    //in&out
    TEXT("text","文本消息","in&out"),
    IMAGE("image","图片消息","in&out"),
    VOICE("voice","语音消息","in&out"),
    VIDEO("video","视频消息","in&out"),
    //out
    MUSIC("music","音乐消息","out"),
    NEWS("news","图文消息","out"),
    //in
    SHORTVIDEO("shortvideo","小视频消息","in"),
    LOCATION("location","地理位置消息","in"),
    LINK("link","链接消息","in"),
    EVENT("event","事件消息","in"),
    ;

    private String type;
    private String description;
    private String scope;//in:接收 out:发送

    MsgType(String type, String description, String scope) {
        this.type = type;
        this.description = description;
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getScope() {
        return scope;
    }
}
