package wechat.core.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Author: zhc
 * Description:接收微信推送的消息实体
 *              接收模板消息:https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_standard_messages.html
 *              接收菜单事件:https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Custom_Menu_Push_Events.html
 *              接收事件消息:https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_event_pushes.html
 *
 *
 *              由于微信服务器传来的数据是xml格式的需要使用jaxb转换未实体
 * Example:
 *  <xml>
 *      <ToUserName><![CDATA[toUser]]></ToUserName>
 *      <FromUserName><![CDATA[fromUser]]></FromUserName>
 *      <CreateTime>1348831860</CreateTime>
 *      <MsgType><![CDATA[text]]></MsgType>
 *      <Content><![CDATA[this is a test]]></Content>
 *      <MsgId>1234567890123456</MsgId>
 *  </xml>
 *
 * Create Time: 2021/7/2
 */
@XmlRootElement(name="xml")//根节点名称为xml
@XmlAccessorType(XmlAccessType.FIELD)//映射所有字段
public class ReceiveMsg {
    private String ToUserName;//开发者微信号
    private String FromUserName;//发送方帐号（一个OpenID）
    private Long CreateTime;//消息创建时间 （整型）
    private String MsgType;//消息类型   文本:text,图片:image,语音:voice,视频:video,小视频:shortvideo,地理位置:location,事件:event


    //消息型数据
    private Long MsgId;//消息id，64位整型
    //多媒体类型(图片,语音,视频,小视频)
    private String MediaId;//对应类型的素材id，可以调用获取临时素材接口拉取数据。
    //文本类型
    private String Content;//文本消息内容
    //地理位置类型
    private Double Location_X;//地理位置纬度
    private Double Location_Y;//地理位置经度
    private Integer Scale;//地图缩放大小
    private String Label;//地理位置信息
    //图片类型
    private String PicUrl;//图片链接（由系统生成）
    //语音类型
    private String Format;//语音格式，如amr，speex等
    private String Recognition;//语音识别结果，UTF8编码(开启语音识别功能会添加这个字段)
    //视频和小视频类型
    private String ThumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。


    //事件类型
    private String Event;//点击:CLICK,跳转链接:VIEW
    private String EventKey;//事件KEY值，与自定义菜单接口中KEY值对应
    //连接跳转
    private String MenuID;//指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
    //扫码推送
    private String ScanCodeInfo;//扫描信息
    private String ScanType;//扫描类型，一般是qrcode
    private String ScanResult;//扫描结果，即二维码对应的字符串信息
    //弹出系统拍照发图的事件推送
    private String SendPicsInfo;//发送的图片信息
    private Integer Count;//发送的图片数量
    private String PicList;//图片列表
    private String PicMd5Sum;//图片的MD5值，开发者若需要，可用于验证接收到图片
    //弹出地理位置选择器的事件推送
    private String SendLocationInfo;//发送的位置信息
//    private Double Location_X;//和上面的属性重复
//    private Double Location_Y;//和上面的属性重复
//    private Integer Scale;//和上面的属性重复
//    private String Label;//和上面的属性重复
    private String Poiname;//朋友圈POI的名字，可能为空
    //模板消息推送事件
    private Long MsgID;//消息id
    private String Status;//发送状态      success  成功,  failed:user block  用户拒收  ,  failed: system failed  其它原因失败

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public Long getMsgID() {
        return MsgID;
    }

    public void setMsgID(Long msgID) {
        MsgID = msgID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Double getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(Double location_X) {
        Location_X = location_X;
    }

    public Double getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(Double location_Y) {
        Location_Y = location_Y;
    }

    public Integer getScale() {
        return Scale;
    }

    public void setScale(Integer scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }

    public String getScanCodeInfo() {
        return ScanCodeInfo;
    }

    public void setScanCodeInfo(String scanCodeInfo) {
        ScanCodeInfo = scanCodeInfo;
    }

    public String getScanType() {
        return ScanType;
    }

    public void setScanType(String scanType) {
        ScanType = scanType;
    }

    public String getScanResult() {
        return ScanResult;
    }

    public void setScanResult(String scanResult) {
        ScanResult = scanResult;
    }

    public String getSendPicsInfo() {
        return SendPicsInfo;
    }

    public void setSendPicsInfo(String sendPicsInfo) {
        SendPicsInfo = sendPicsInfo;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public String getPicList() {
        return PicList;
    }

    public void setPicList(String picList) {
        PicList = picList;
    }

    public String getPicMd5Sum() {
        return PicMd5Sum;
    }

    public void setPicMd5Sum(String picMd5Sum) {
        PicMd5Sum = picMd5Sum;
    }

    public String getSendLocationInfo() {
        return SendLocationInfo;
    }

    public void setSendLocationInfo(String sendLocationInfo) {
        SendLocationInfo = sendLocationInfo;
    }

    public String getPoiname() {
        return Poiname;
    }

    public void setPoiname(String poiname) {
        Poiname = poiname;
    }
}
