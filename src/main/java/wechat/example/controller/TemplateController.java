package wechat.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wechat.core.api.ApiExecuteCenter;
import wechat.core.entity.api.SendTemplate;
import wechat.example.entity.Response;

@Api(tags = "模板模块")
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private ApiExecuteCenter apiCenter;

    @ApiOperation(value = "手动发送模板消息")
    @RequestMapping(method = RequestMethod.POST,value = "/sendTemplate")
    public Response sendTemplate(@RequestBody SendTemplate sendTemplate){
        return Response.success(apiCenter.template().sendTemplate(sendTemplate));
    }

    @ApiOperation(value = "查询所有模板信息")
    @RequestMapping(method = RequestMethod.GET,value = "/queryTemplate")
    public Response queryTemplate(){
        return Response.success(apiCenter.template().getTemplate());
    }

}
