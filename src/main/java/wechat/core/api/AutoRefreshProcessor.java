package wechat.core.api;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;
import wechat.core.config.WeChatContext;
import wechat.core.config.WeChatRegistry;
import wechat.core.constant.WeChatConstant;
import wechat.core.entity.api.APIResult;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Author: zhc
 * Description:默认api后置处理
 *              若api调用返回40001错误，则刷新当前token
 *
 *              40001错误:获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真比对 AppSecret 的正确性，或查看是否正在为恰当的公众号调用接口
 * Create Time: 2021/7/10
 */
@Component
public class AutoRefreshProcessor implements ApiAfterProcessor {

    @Override
    public void doProcessor(URLBuilder builder, APIResult result) {
        if(result.getCode().equals(APIResult.FAILURE)&&(result.getErrorMsg().getErrcode().equals(40001)||result.getErrorMsg().getErrcode().equals(42001))) {
            WeChatContext.service().refreshToken();
            try {
                LocalDateTime expireTime = WeChatRegistry.EXPIRETIME;
                expireTime = expireTime.plusSeconds((int) (Math.random() * 300));
                SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                Scheduler scheduler = schedulerFactory.getScheduler();
                TriggerKey triggerKey = TriggerKey.triggerKey(WeChatConstant.TRIGGER, WeChatConstant.TRIGGER_GROUP);
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(WeChatConstant.TRIGGER, WeChatConstant.TRIGGER_GROUP)//组标识
                        .startAt(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))//在过期时间开始生效
                        .withSchedule(
                                SimpleScheduleBuilder
                                        .simpleSchedule()
                                        .withIntervalInMinutes(WeChatRegistry.TOKEN_LAST_MINUTES)//循环规则
                                        .repeatForever()//永久循环
                        ).build();

                scheduler.rescheduleJob(triggerKey, trigger);
            }catch (Exception e){
                System.out.println("重置定时任务出错：" + e.getMessage());
            }

        }
        process(builder, result);
    }

    //实际后置处理
    public void process(URLBuilder builder,APIResult result){

    }
}
