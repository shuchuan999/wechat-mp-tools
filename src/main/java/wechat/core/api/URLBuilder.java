package wechat.core.api;

import com.google.gson.Gson;
import wechat.core.config.WeChatRegistry;
import wechat.core.constant.WeChatAPI;
import wechat.core.constant.WeChatConstant;
import wechat.core.util.StringUtil;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLBuilder {

    private WeChatAPI api;
    private String postJson;
    private Map<String,Object> paramsMap;

    public URLBuilder(WeChatAPI api) {
        this.paramsMap = new HashMap<>();
        if(!StringUtil.isEmpty(api.getParams())) {
            String[] params = api.getParams().split("\\&");
            for (String param : params) {
                //设置一些默认值
                if(param.equals("access_token")) this.paramsMap.put(param, WeChatRegistry.ACCESSTOKEN);
                else if(param.equals("secret")) this.paramsMap.put(param, WeChatRegistry.SECRET);
                else if(param.equals("appid")) this.paramsMap.put(param, WeChatRegistry.APPID);
                else this.paramsMap.put(param, null);
            }
        }
        this.api=api;
    }

    public void setParam(String paramName,Object content){
        if(paramsMap.containsKey(paramName)) paramsMap.replace(paramName,content);
    }

    public String buildURL(){
        String url=api.getUrl();
        Object[] paramContent=new String[paramsMap.keySet().size()];
        Pattern p=Pattern.compile("[?&]{1}(.+?)={1}");
        Matcher m=p.matcher(url);
        for(int i=0;m.find();i++){
            String s=m.group(1);
            Object content=paramsMap.get(s);
            if(content==null) paramContent[i]="";
            else paramContent[i]= URLEncoder.encode(content.toString());
        }
        return String.format(url, paramContent);
    }

    public <T> void setPostData(T obj,Class<T> tClass) {
        if (api.getMethod().equals(WeChatConstant.POST))
            this.postJson = new Gson().toJson(obj, tClass);
    }

    public Class getResultClass(){
        return this.api.getResultClass();
    }

    public String getMethod(){
        return this.api.getMethod();
    }

    public String getPostData(){
        return this.postJson;
    }

    public WeChatAPI getApi() {
        return api;
    }
}
