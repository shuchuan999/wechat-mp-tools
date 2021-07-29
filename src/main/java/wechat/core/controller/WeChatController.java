package wechat.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wechat.core.config.WeChatContext;
import wechat.core.config.WeChatRegistry;
import wechat.core.entity.ReceiveMsg;
import wechat.core.util.SecurityUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WeChatController {

    @RequestMapping(method = RequestMethod.GET,value = "/WeChat")
    @ResponseBody
    public String validate(String signature,String timestamp,String nonce,String echostr){
        List<String> arr = Arrays.asList(timestamp,nonce, WeChatRegistry.TOKEN);
        arr.sort(String::compareTo);
        String encodeStr = arr.stream().collect(Collectors.joining());
        if(SecurityUtil.sha1Encode(encodeStr).equals(signature)) return echostr;
        return null;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/WeChat")
    public void handleMessage(@RequestBody ReceiveMsg msg, HttpServletResponse response) {
        WeChatContext.dispatch().doHandler(msg, response);
    }
}
