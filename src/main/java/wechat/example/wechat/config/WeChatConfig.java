package wechat.example.wechat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wechat.core.config.ConfigAdapter;
import wechat.core.config.WeChatContext;
import wechat.core.constant.MsgType;
import wechat.example.wechat.handler.EventHandler;
import wechat.example.wechat.handler.TextHandler;
import wechat.example.wechat.token.JDBCRepository;

@Component
public class WeChatConfig extends ConfigAdapter {

    @Autowired
    private TextHandler textHandler;
    @Autowired
    private EventHandler eventHandler;
    @Autowired
    private JDBCRepository repository;

    @Override
    public void config(){
        WeChatContext.config()
                .configCommon()
                .setTokenRepository(repository)
                .and()
                .configMsgHandler()
                .setHandler(textHandler, MsgType.TEXT)
                .setHandler(eventHandler, MsgType.EVENT);
    }

}
