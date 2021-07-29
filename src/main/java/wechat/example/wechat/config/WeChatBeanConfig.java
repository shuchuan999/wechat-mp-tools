package wechat.example.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wechat.core.api.ApiExecuteCenter;
import wechat.core.config.WeChatContext;
import wechat.core.handler.MsgDispatchCenter;
import wechat.example.wechat.handler.TextHandler;

@Configuration
public class WeChatBeanConfig {

    @Bean
    public ApiExecuteCenter getApiExecuteCenter(){
        return WeChatContext.api();
    }

    @Bean
    public MsgDispatchCenter getMsgDispatchCenter(){
        return WeChatContext.dispatch();
    }

    @Bean
    public TextHandler getTextHandler(){
        return new TextHandler();
    }
}
