package wechat.core.token;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import wechat.core.config.WeChatContext;

public class RefreshTokenJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        WeChatContext.service().refreshToken();
    }

}
