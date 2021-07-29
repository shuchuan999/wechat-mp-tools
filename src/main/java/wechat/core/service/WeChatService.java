package wechat.core.service;



import wechat.core.api.ApiExecuteCenter;
import wechat.core.config.WeChatRegistry;
import wechat.core.entity.AccessToken;
import wechat.core.entity.TokenJson;
import wechat.core.entity.api.APIResult;
import wechat.core.token.RefreshPool;
import wechat.core.token.TokenRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class WeChatService {

    private ApiExecuteCenter apiCenter;
    private TokenRepository repository;

    /**
     * Author: zhc
     * Description:申请最新accesstoken
     *              若成功,原先的token将失效
     * Create Time: 2021/7/10
     */
    public String getNewAccessToken(int i){
        try {
            APIResult<TokenJson> result = apiCenter.basic().accessToken();
            if(result.getCode().equals(APIResult.SUCCESS)) return result.getSuccessMsg().getAccess_token();
            else return reSubmit(i);
        }catch (Exception e){
            return reSubmit(i);
        }
    }

    /**
     * Author: zhc
     * Description:刷新token
     *              校验数据库中的token
     * Create Time: 2021/7/10
     */
    public void refreshToken(){
        AccessToken accessToken = repository.get();

        if(accessToken==null) refresh();
        //只有本地token和数据库token不等，且数据库token在有效期内，才不刷新token，但是要注意刷新本地token
        else if(!accessToken.getAccessToken().equals(WeChatRegistry.ACCESSTOKEN)){
            long timeSub= accessToken.getExpireTime().getTime() - LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            if(timeSub/1000/60< WeChatRegistry.TOKEN_LAST_MINUTES) refresh();
            else {
                WeChatRegistry.ACCESSTOKEN=accessToken.getAccessToken();
                WeChatRegistry.EXPIRETIME=accessToken.getExpireTime().toLocalDateTime();
            }
        }else refresh();

    }

    //防止刷新token的操作阻塞主线程,这个操作放在新的线程中执行
    private void refresh() {
        RefreshPool.pool().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        doRefresh();
                    }
                }
        );
    }

    private void doRefresh(){
        String newToken= getNewAccessToken(0);
        if(newToken!=null) {
            LocalDateTime expireTime = LocalDateTime.now().plusHours(2);
            repository.store(new AccessToken(newToken, Timestamp.valueOf(expireTime)));
            synchronized (WeChatRegistry.class) {
                WeChatRegistry.ACCESSTOKEN=newToken;
                WeChatRegistry.EXPIRETIME=expireTime;
            }
            System.out.println("token已刷新，下次过期时间:" + expireTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }

    //等待30秒重试请求
     String reSubmit(int i){
        System.out.println("获取token出错!");
        try {
            Thread.sleep(1000 * 30);
        }catch (Exception e1){}
        if(i<10) return getNewAccessToken(i+1);
        return null;
    }

    public WeChatService(ApiExecuteCenter apiCenter, TokenRepository repository) {
        this.apiCenter = apiCenter;
        this.repository = repository;
    }
}
