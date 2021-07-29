package wechat.example.wechat.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wechat.core.entity.AccessToken;
import wechat.core.token.TokenRepository;
import wechat.example.dao.TokenDao;

import java.time.format.DateTimeFormatter;

/**
 * Author: zhc
 * Description:自定义数据库token存储
 * Create Time: 2021/7/27
 */
@Component
public class JDBCRepository implements TokenRepository {

    @Autowired
    private TokenDao tokenDao;

    @Override
    public AccessToken get() {
        return tokenDao.queryAccessToken();
    }

    @Override
    public void store(AccessToken accessToken) {
        tokenDao.updateAccessToken(accessToken.getAccessToken(),accessToken.getExpireTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
