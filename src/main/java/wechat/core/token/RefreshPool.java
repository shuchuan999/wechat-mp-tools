package wechat.core.token;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: zhc
 * Description:刷新token的线程池
 *      保证全局只有一个线程在执行token刷新任务
 *      若添加任务时已有线程在执行，则直接抛弃任务
 * Create Time: 2021/7/22
 */
public class RefreshPool {

    private static ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolExecutor pool() {
        if (threadPoolExecutor == null) {
            synchronized (RefreshPool.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(1, 1, 600, TimeUnit.SECONDS, new ArrayBlockingQueue(1), new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }
        return threadPoolExecutor;
    }

}
