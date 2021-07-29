package wechat.core.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import wechat.core.api.AutoRefreshProcessor;
import wechat.core.constant.MsgType;
import wechat.core.handler.DefaultEventHandler;
import wechat.core.token.InMemoryRepository;
import wechat.core.token.TokenStarter;

/**
 * Author: zhc
 * Description:静态配置
 * Create Time: 2021/7/7
 */
public abstract class ConfigAdapter  implements InitializingBean {

    @Value("${weixin.appid:appid}")
    public String appid;
    @Value("${weixin.secret:secret}")
    public String secret;
    @Value("${weixin.EncodingAESKey:EncodingAESKey}")
    public String EncodingAESKey;
    @Value("${weixin.token:token}")
    public String token;
    @Value("${weixin.TokenLastMinutes:100}")
    public Integer TokenLastMinutes;
    @Autowired
    public AutoRefreshProcessor refreshProcessor;
    @Autowired
    public TokenStarter tokenStarter;
    @Autowired
    public DefaultEventHandler defaultEventHandler;


    public abstract void config();

    //默认配置:几个常量,自动刷新token的api后置处理,默认事件处理
    private void defaultConfig(){
        WeChatContext.config()
                .configCommon()
                .setAppID(appid)
                .setSecret(secret)
                .setEncodingAESKey(EncodingAESKey)
                .setToken(token)
                .setTokenLastMinutes(TokenLastMinutes)
                .setTokenRepository(new InMemoryRepository())
                .and()
                .configApi()
                .setAfterProcessor(refreshProcessor)
                .and()
                .configMsgHandler()
                .setHandler(defaultEventHandler, MsgType.EVENT);
    }

    //由于是先执行默认配置,后执行自定义配置,所以自定义配置可以覆盖默认配置
    @Override
    public void afterPropertiesSet() throws Exception{
        this.defaultConfig();//加载默认配置
        this.config();//加载自定义配置
        this.tokenStarter.initializeToken();//初始化token
    }

}
