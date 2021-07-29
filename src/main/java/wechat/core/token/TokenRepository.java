package wechat.core.token;

import wechat.core.entity.AccessToken;

/**
 * Author: zhc
 * Description:Token持久化存储仓库
 *              要注意这个是持久化存储相关的方法，请和项目中实际使用的token区分
 *              若get()直接返回null,store()不做任何操作,则使用内存管理
 * Create Time: 2021/7/9
 */
public interface TokenRepository {
    //持久化存储中获取token
    AccessToken get();
    //更新持久化存储
    void store(AccessToken accessToken);
}
