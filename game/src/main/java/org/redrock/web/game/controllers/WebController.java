package org.redrock.web.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @RequestMapping(value = "/skip")
    public String skip(){
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3f30a94b82f6a6b7&redirect_uri=www.zssgk3.natappfree.cc/getcode&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    }

}
