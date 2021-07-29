package wechat.core.token;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;
import wechat.core.config.WeChatContext;
import wechat.core.config.WeChatRegistry;
import wechat.core.constant.WeChatConstant;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Author: zhc
 * Description:token初始化和发布自动刷新token定时任务
 * Create Time: 2021/7/9
 */
@Component
public class TokenStarter {

    public void initializeToken(){
        WeChatContext.service().refreshToken();
        startScheduler(WeChatRegistry.EXPIRETIME);//开启定时任务
    }

    /**
     * Author: zhc
     * Description:使用Quartz手动开启定时任务
     * Create Time: 2021/7/5
     */
    public void startScheduler(LocalDateTime expireTime){
        try {
            if(expireTime==null) expireTime=LocalDateTime.now().plusMinutes(10);//当获取token出错时,过期时间为空,这里设置一个早一点的刷新时间
            expireTime=expireTime.plusSeconds((int)(Math.random()*300));//为了防止项目在多处启动,在同一时刻一起刷新token。加入随机延时,在刷新token前检验数据库
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(RefreshTokenJob.class)
                    .withIdentity(WeChatConstant.JOB, WeChatConstant.JOB_GROUP).build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(WeChatConstant.TRIGGER, WeChatConstant.TRIGGER_GROUP)//组标识
                    .startAt(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))//在过期时间开始生效
                    .withSchedule(
                            SimpleScheduleBuilder
                                    .simpleSchedule()
                                    .withIntervalInMinutes(WeChatRegistry.TOKEN_LAST_MINUTES)//循环规则
                                    .repeatForever()//永久循环
                    ).build();

            scheduler.scheduleJob(jobDetail, trigger);

            scheduler.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
