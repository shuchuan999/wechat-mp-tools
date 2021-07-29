package wechat.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wechat.core.api.ApiExecuteCenter;
import wechat.core.entity.api.CreateMenu;
import wechat.example.entity.Response;

@RestController
@Api(tags = "菜单服务")
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private ApiExecuteCenter executeCenter;

    @ApiOperation(value = "查询自定义菜单")
    @RequestMapping(method = RequestMethod.GET,value = "/queryMenu")
    @ResponseBody
    public Response queryMenu(){
        return Response.success(executeCenter.menu().getMenu());
    }

    @ApiOperation(value = "创建自定义菜单")
    @RequestMapping(method = RequestMethod.POST,value = "/createMenu")
    @ResponseBody
    public Response createMenu(@RequestBody CreateMenu menu){
        return Response.success(executeCenter.menu().createMenu(menu));
    }
}
