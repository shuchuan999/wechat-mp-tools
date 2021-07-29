package wechat.example.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Repository;
import wechat.core.entity.WeChatUser;

@Repository
public interface CommonDao {

    @Description(value = "新增微信用户")
    @Insert({
             " insert into wechat_userinfo (openid,subscribe,nickname,sex,language,city,province,country,headimgurl,subTime,unionid,remark,groupid,subscribe_scene,qr_scene,qr_scene_str,tags) VALUES (#{openid},#{subscribe},#{nickname},#{sex},#{language},#{city},#{province},#{country},#{headimgurl},#{subTime},#{unionid},#{remark},#{groupid},#{subscribe_scene},#{qr_scene},#{qr_scene_str},#{tags})"
    })
    public int insertWeChatUser(WeChatUser userInfo);

    @Description(value = "修改微信用户")
    @Update({
             " update wechat_userinfo set subscribe=#{subscribe},nickname=#{nickname},sex=#{sex},language=#{language},city=#{city},province=#{province},country=#{country},headimgurl=#{headimgurl},subTime=#{subTime},unionid=#{unionid},remark=#{remark},groupid=#{groupid},subscribe_scene=#{subscribe_scene},qr_scene=#{qr_scene},qr_scene_str=#{qr_scene_str},tags=#{tags} where openid=#{openid}"
    })
    public int updateWeChatUser(WeChatUser user);

    @Description(value = "查询微信用户")
    @Select({
             " select * from wechat_userinfo where openid=#{id}"
    })
    public WeChatUser queryWeChatUser(String id);

    @Description(value = "删除微信用户")
    @Delete({
             " delete from wechat_userinfo where openid=#{id}"
    })
    public int deleteUser(String id);
}
