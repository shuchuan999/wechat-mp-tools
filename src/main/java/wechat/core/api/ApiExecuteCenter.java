package wechat.core.api;

import wechat.core.constant.WeChatAPI;
import wechat.core.entity.api.APIResult;
import wechat.core.entity.api.CreateMenu;
import wechat.core.entity.api.DeleteTemplate;
import wechat.core.entity.api.SendTemplate;
import wechat.core.util.StringUtil;

/**
 * Author: zhc
 * Description:api调用中心
 *              目前这种写法没有自动感知泛型的能力
 * Create Time: 2021/7/8
 */
public class ApiExecuteCenter {

    private DefaultApiExecutor executor;

    private Menu menu;
    private User user;
    private Basic basic;
    private Template template;

    public ApiExecuteCenter(DefaultApiExecutor executor) {
        this.executor = executor;
        this.menu=new Menu();
        this.user=new User();
        this.basic=new Basic();
        this.template=new Template();
    }

    public class Menu{

        public <T> APIResult<T> createMenu(CreateMenu menu){
            URLBuilder builder=new URLBuilder(WeChatAPI.MENU_CREATE);
            builder.setPostData(menu,CreateMenu.class);
            return executor.executeProxy(builder,WeChatAPI.MENU_CREATE.getResultClass());
        }

        public <T> APIResult<T> getMenu(){
            return executor.executeProxy(new URLBuilder(WeChatAPI.MENU_GET),WeChatAPI.MENU_GET.getResultClass());
        }

        public <T> APIResult<T> deleteMenu(){
            return executor.executeProxy(new URLBuilder(WeChatAPI.MENU_DELETE),WeChatAPI.MENU_DELETE.getResultClass());
        }

    }

    public class User{

        //lang: 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
        public <T> APIResult<T> getUserInfo(String openid,String lang){
            URLBuilder builder=new URLBuilder(WeChatAPI.USER_GET_INFO);
            builder.setParam("openid",openid);
            builder.setParam("lang",lang);
            return executor.executeProxy(builder,WeChatAPI.USER_GET_INFO.getResultClass());
        }

        public <T> APIResult<T> getUserIds(String next_openid){
            URLBuilder builder=new URLBuilder(WeChatAPI.USER_GET_ID_LIST);
            if(!StringUtil.isEmpty(next_openid)) builder.setParam("next_openid",next_openid);
            return executor.executeProxy(builder, WeChatAPI.USER_GET_ID_LIST.getResultClass());
        }
    }

    public class Basic{

        public <T> APIResult<T> accessToken(){
            URLBuilder builder=new URLBuilder(WeChatAPI.ACCESS_TOKEN);
            builder.setParam("grant_type","client_credential");
            return executor.executeProxy(builder,WeChatAPI.ACCESS_TOKEN.getResultClass());
        }

    }

    public class Template{

        public <T> APIResult<T> getTemplate(){
            URLBuilder builder=new URLBuilder(WeChatAPI.TEMPLATE_GET);
            return executor.executeProxy(builder,WeChatAPI.TEMPLATE_GET.getResultClass());
        }

        public <T> APIResult<T> deleteTemplate(String templateID){
            URLBuilder builder=new URLBuilder(WeChatAPI.TEMPLATE_DELETE);
            builder.setPostData(new DeleteTemplate(templateID),DeleteTemplate.class);
            return executor.executeProxy(builder,WeChatAPI.TEMPLATE_DELETE.getResultClass());
        }

        public <T> APIResult<T> sendTemplate(SendTemplate template){
            URLBuilder builder=new URLBuilder(WeChatAPI.TEMPLATE_SEND);
            builder.setPostData(template,SendTemplate.class);
            return executor.executeProxy(builder,WeChatAPI.TEMPLATE_SEND.getResultClass());
        }
    }

    public Menu menu(){
        return this.menu;
    }

    public User user(){
        return this.user;
    }

    public Basic basic(){
        return this.basic;
    }

    public Template template(){
        return this.template;
    }
}
