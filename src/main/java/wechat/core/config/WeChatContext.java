package wechat.core.config;


import wechat.core.api.ApiAfterProcessor;
import wechat.core.api.ApiExecuteCenter;
import wechat.core.api.ApiPreProcessor;
import wechat.core.api.DefaultApiExecutor;
import wechat.core.handler.MsgDispatchCenter;
import wechat.core.service.WeChatService;
import wechat.core.token.TokenRepository;

public class WeChatContext {

    //配置中心
    private static WeChatRegistry registry;
    //消息处理
    private static MsgDispatchCenter dispatchCenter;
    //api通信处理核心
    private static DefaultApiExecutor defaultApiExecutor;
    //api通信通信
    private static ApiExecuteCenter apiExecuteCenter;
    //token持久化存储
    private static TokenRepository repository;
    //常用操作
    private static WeChatService service;

    public static WeChatService service(){
        if (service == null) {
            synchronized (WeChatService.class) {
                if (service == null) {
                    service = new WeChatService(api(),repository());
                }
            }
        }
        return service;
    }

    public static TokenRepository repository(){
        return repository;
    }

    public static void setTokenRepository(TokenRepository repository){
        WeChatContext.repository=repository;
    }

    public static MsgDispatchCenter dispatch(){
        if (dispatchCenter == null) {
            synchronized (MsgDispatchCenter.class) {
                if (dispatchCenter == null) {
                    dispatchCenter = new MsgDispatchCenter();
                }
            }
        }
        return dispatchCenter;
    }

    public static ApiExecuteCenter api(){
        if (apiExecuteCenter == null) {
            synchronized (ApiExecuteCenter.class) {
                if (apiExecuteCenter == null) {
                    apiExecuteCenter = new ApiExecuteCenter(getDefaultApiExecutor());
                }
            }
        }
        return apiExecuteCenter;
    }

    public static WeChatRegistry config(){
        if (registry == null) {
            synchronized (WeChatRegistry.class) {
                if (registry == null) {
                    registry = new WeChatRegistry();
                }
            }
        }
        return registry;
    }

    public static DefaultApiExecutor getDefaultApiExecutor(){
        if (defaultApiExecutor == null) {
            synchronized (DefaultApiExecutor.class) {
                if (defaultApiExecutor == null) {
                    defaultApiExecutor = new DefaultApiExecutor(new ApiPreProcessor() {}, new ApiAfterProcessor() {});
                }
            }
        }
        return defaultApiExecutor;
    }

}
