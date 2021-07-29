package wechat.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wechat.core.api.ApiExecuteCenter;
import wechat.example.entity.Response;
import wechat.example.service.CommonService;

@Api(tags = "微信公众号通用接口")
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private ApiExecuteCenter apiCenter;

    @ApiOperation(value = "手动新增微信用户")
    @RequestMapping(value = "/addWeChatUser", method = RequestMethod.GET)
    @ResponseBody
    public Response addWeChatUser(
            @ApiParam(name = "openid", value = "openid", required = true)
            @RequestParam(name = "openid", required = true) String openid) {
        commonService.insertUser(openid);
        return Response.success();
    }

    @ApiOperation(value = "手动删除微信用户")
    @RequestMapping(value = "/deleteWeChatUser", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteWeChatUser(
            @ApiParam(name = "openid", value = "openid", required = true)
            @RequestParam(name = "openid", required = true) String openid) {
        commonService.deleteUser(openid);
        return Response.success();
    }

    @ApiOperation(value = "查询用户id列表")
    @RequestMapping(value = "/queryUserIds", method = RequestMethod.GET)
    @ResponseBody
    public Response queryUserIds(
            @ApiParam(name = "next_openid", value = "next_openid", required = false)
            @RequestParam(name = "next_openid", required = false) String next_openid) {
        return Response.success(apiCenter.user().getUserIds(next_openid));
    }

    @ApiOperation(value = "查询微信用户详情")
    @RequestMapping(value = "/queryWeChatUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public Response queryWeChatUserInfo(
            @ApiParam(name = "openid", value = "openid", required = true)
            @RequestParam(name = "openid", required = true) String openid) {
        return Response.success(apiCenter.user().getUserInfo(openid,"zh_CN"));
    }

}
