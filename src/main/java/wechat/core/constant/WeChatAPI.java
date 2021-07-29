package wechat.core.constant;

import wechat.core.entity.TokenJson;
import wechat.core.entity.WeChatUser;
import wechat.core.entity.api.*;

/**
 * Author: zhc
 * Description:微信接口配置枚举类
 * Create Time: 2021/7/8
 */
public enum WeChatAPI {

    //基础模块
    ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token?grant_type=%s&appid=%s&secret=%s",WeChatConstant.GET,"基础功能","ACCESS_TOKEN","grant_type&appid&secret", TokenJson.class),

    //用户模块
    USER_GET_INFO("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=%s", WeChatConstant.GET,"用户管理","USER_GET_INFO","access_token&openid&lang", WeChatUser.class),//获取用户详情
    USER_GET_ID_LIST("https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s",WeChatConstant.GET,"用户管理","USER_GET_ID_LIST","access_token&next_openid", UserOpenIdList.class),//获取用户openid列表

    //菜单模块
    MENU_CREATE("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s",WeChatConstant.POST,"自定义菜单","MENU_CREATE","access_token", ErrorMsg.class),//创建自定义菜单
    MENU_GET("https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=%s",WeChatConstant.GET,"自定义菜单","MENU_GET","access_token", QueryMenu.class),//查询自定义菜单
    MENU_DELETE("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s",WeChatConstant.GET,"自定义菜单","MENU_DELETE","access_token",ErrorMsg.class),//删除菜单(包括个性化菜单)

    //模板消息模块
    TEMPLATE_GET("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s",WeChatConstant.GET,"模板管理","TEMPLATE_GET","access_token", QueryTemplate.class),//获取消息模板列表
    TEMPLATE_DELETE("https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=%s",WeChatConstant.POST,"模板管理","TEMPLATE_DELETE","access_token",ErrorMsg.class),//模板删除
    TEMPLATE_SEND("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s",WeChatConstant.POST,"模板管理","TEMPLATE_SEND","access_token", SendTemplateResult.class),//发送模板消息

    ;

    private String url;
    private String method;
    private String tag;
    private String identity;
    private String params;
    private Class resultClass;

    WeChatAPI(String url, String method, String tag, String identity, String params, Class resultClass) {
        this.url = url;
        this.method = method;
        this.tag = tag;
        this.identity = identity;
        this.params = params;
        this.resultClass = resultClass;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getTag() {
        return tag;
    }

    public String getIdentity() {
        return identity;
    }

    public String getParams() {
        return params;
    }

    public Class getResultClass() {
        return resultClass;
    }
}
