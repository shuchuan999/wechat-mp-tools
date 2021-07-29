package wechat.example.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import wechat.core.entity.AccessToken;

@Repository
public interface TokenDao {

    //查询access_token
    @Select({
            " select access_token as accessToken,expire_time as expireTime from wechat_token "
    })
    public AccessToken queryAccessToken();

    //更新token
    @Update({
            " update wechat_token set access_token=#{token},expire_time=#{expire} "
    })
    public int updateAccessToken(String token, String expire);
}
