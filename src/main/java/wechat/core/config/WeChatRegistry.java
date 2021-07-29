package wechat.core.config;

import wechat.core.api.ApiAfterProcessor;
import wechat.core.api.ApiPreProcessor;
import wechat.core.api.DefaultApiExecutor;
import wechat.core.constant.MsgType;
import wechat.core.handler.DefaultExceptionHandler;
import wechat.core.handler.DefaultHandler;
import wechat.core.handler.MsgDispatchCenter;
import wechat.core.token.TokenRepository;

import java.time.LocalDateTime;

/**
 * Author: zhc
 * Description:配置中心
 *              该配置方式允许程序运行时动态改变配置，但通常不建议这么做，防止出现不可预料的错误。
 *              若要实现调用业务方法前完成配置加载，且之后不允许改变，可以考虑在WeChatContext中使用单例模式进行懒加载，且只在实例化时进行配置渲染。
 * Create Time: 2021/7/6
 */
public class WeChatRegistry {
    //开发者基础配置
    public volatile static String APPID;
    public volatile static String SECRET;
    //服务器配置
    public volatile static String TOKEN;
    public volatile static String EncodingAESKey;
    //项目配置
    public volatile static int TOKEN_LAST_MINUTES;//access_token持续时间
    //token
    public volatile static String ACCESSTOKEN;
    public volatile static LocalDateTime EXPIRETIME;

    public MsgHandlerConfig configMsgHandler() {
        return new MsgHandlerConfig(WeChatContext.dispatch());
    }

    public CommonConfig configCommon(){
        return new CommonConfig();
    }

    public WeChatApiConfig configApi(){
        return new WeChatApiConfig(WeChatContext.getDefaultApiExecutor());
    }

    //默认配置
    public class DefaultConfig {
        public WeChatRegistry and() {
            return WeChatRegistry.this;
        }
    }

    //接收消息配置
    public class MsgHandlerConfig extends DefaultConfig {

        private MsgDispatchCenter dispatchCenter;

        public MsgHandlerConfig setHandler(DefaultHandler handler, MsgType msgType) {
            dispatchCenter.setHandler(handler, msgType);
            return this;
        }

        public MsgHandlerConfig setExceptionHandler(DefaultExceptionHandler handler, Class<? extends Exception> exClass){
            dispatchCenter.getExceptionHandler().setHandler(handler,exClass);
            return this;
        }

        public MsgHandlerConfig(MsgDispatchCenter dispatchCenter) {
            this.dispatchCenter = dispatchCenter;
        }
    }

    //通用配置
    public class CommonConfig extends DefaultConfig {
        public CommonConfig setAppID(String appID) {
            WeChatRegistry.APPID = appID;
            return this;
        }

        public CommonConfig setSecret(String secret) {
            WeChatRegistry.SECRET = secret;
            return this;
        }

        public CommonConfig setToken(String token) {
            WeChatRegistry.TOKEN = token;
            return this;
        }

        public CommonConfig setEncodingAESKey(String encodingAESKey) {
            WeChatRegistry.EncodingAESKey = encodingAESKey;
            return this;
        }

        public CommonConfig setTokenLastMinutes(int lastMinutes) {
            WeChatRegistry.TOKEN_LAST_MINUTES = lastMinutes;
            return this;
        }

        public CommonConfig setTokenRepository(TokenRepository repository){
            WeChatContext.setTokenRepository(repository);
            return this;
        }
    }

    //api模块配置
    public class WeChatApiConfig extends DefaultConfig{

        private DefaultApiExecutor executor;

        public WeChatApiConfig setPreProcessor(ApiPreProcessor processor){
            executor.setPreProcessor(processor);
            return this;
        }

        public WeChatApiConfig setAfterProcessor(ApiAfterProcessor processor){
            executor.setAfterProcessor(processor);
            return this;
        }

        public WeChatApiConfig(DefaultApiExecutor executor) {
            this.executor = executor;
        }
    }


}
