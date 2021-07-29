package wechat.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat.core.api.ApiExecuteCenter;
import wechat.core.entity.WeChatUser;
import wechat.core.entity.api.APIResult;
import wechat.example.dao.CommonDao;

@Service
public class CommonService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ApiExecuteCenter apiCenter;

    /**
     * Author: zhc
     * Description:新增微信用户
     * Create Time: 2021/7/8
     */
    public void insertUser(WeChatUser user){
        commonDao.insertWeChatUser(user);
    }

    /**
     * Author: zhc
     * Description:通过openid新增或修改用户
     * Create Time: 2021/7/8
     */
    public void insertUser(String openid){
        APIResult<WeChatUser> result = apiCenter.user().getUserInfo(openid, "zh_CN ");
        if(result.getCode().equals(APIResult.SUCCESS)){
            WeChatUser WeChatUser = result.getSuccessMsg();
            WeChatUser.convert();
            WeChatUser userExists = commonDao.queryWeChatUser(openid);
            if(userExists==null) insertUser(WeChatUser);
            else commonDao.updateWeChatUser(WeChatUser);
        }
    }

    /**
     * Author: zhc
     * Description:删除微信用户
     * Create Time: 2021/7/8
     */
    public void deleteUser(String openid){
        commonDao.deleteUser(openid);
    }

}
