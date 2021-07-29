package wechat.core.entity.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhc
 * Description:发送模板信息实体
 *                  https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html
 * Create Time: 2021/7/12
 */
public class SendTemplate {

    private String touser;//接收者openid
    private String template_id;//模板ID
    private String url;//模板跳转链接（海外帐号没有跳转能力）
    private Miniprogram miniprogram;//跳小程序所需数据，不需跳小程序可不用传该数据
    private Map data;//模板数据

    /**
     * Author: zhc
     * Description:设置消息内容
     *                  key:标签名
     *                  value:内容
     *                  color:颜色(null默认黑色)
     * Create Time: 2021/7/12
     */
    public void setContent(String key,String value,String color){
        if(data==null) data=new HashMap();
        Map content=new HashMap();
        content.put("value",value);
        if(color!=null) content.put("color",color);
        data.put(key,content);
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Miniprogram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Miniprogram miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public static class Miniprogram {

        private String appid;//所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
        private String pagepath;//所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagepath() {
            return pagepath;
        }

        public void setPagepath(String pagepath) {
            this.pagepath = pagepath;
        }
    }
}
