package wechat.core.api;

import com.google.gson.Gson;
import wechat.core.constant.WeChatConstant;
import wechat.core.entity.api.APIResult;
import wechat.core.entity.api.ErrorMsg;
import wechat.core.util.HttpRequestUtils;
import wechat.core.util.StringUtil;

public class DefaultApiExecutor {
    //前置与后置处理器的处理方法需要是线程安全的
    private ApiPreProcessor preProcessor;
    private ApiAfterProcessor afterProcessor;

    public DefaultApiExecutor(ApiPreProcessor preProcessor, ApiAfterProcessor afterProcessor) {
        this.preProcessor = preProcessor;
        this.afterProcessor = afterProcessor;
    }

    //简单aop
    public <T> APIResult<T> executeProxy(URLBuilder builder, Class<T> tClass){
        preProcessor.doProcessor(builder);
        APIResult<T> result=executeApi(builder,tClass);
        afterProcessor.doProcessor(builder,result);
        return result;
    }


    private  <T> APIResult<T> executeApi(URLBuilder builder,Class<T> tClass){
        APIResult<T> result=new APIResult<>();
        String reStr="";
        if(builder.getMethod().equals(WeChatConstant.GET)) reStr= HttpRequestUtils.sendGetRequest(builder.buildURL());
        else reStr=HttpRequestUtils.sendRequest(builder.buildURL(),builder.getPostData());

        if(StringUtil.isErrorMsg(reStr)){
            ErrorMsg errorMsg = new Gson().fromJson(reStr, ErrorMsg.class);
            if(errorMsg.getErrcode()==0) result.setSuccessMsg((T)errorMsg);
            else result.setErrorMsg(errorMsg);
        }else {
            try{
                result.setSuccessMsg(new Gson().fromJson(reStr,tClass));
            }catch (Exception e){
                result.setCode(APIResult.ERROR);
            }
        }
        return result;
    }

    public void setPreProcessor(ApiPreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    public void setAfterProcessor(ApiAfterProcessor afterProcessor) {
        this.afterProcessor = afterProcessor;
    }
}
