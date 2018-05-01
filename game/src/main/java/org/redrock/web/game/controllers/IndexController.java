package org.redrock.web.game.controllers;


import org.redrock.web.game.Utils.WeChatUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }


    @RequestMapping(value = "/WeChatCheck",method = RequestMethod.GET)
    public String WeChatCheck(HttpServletRequest request){
        String signature=request.getParameter("signature");
        System.out.println(signature);
        String timestamp=request.getParameter("timestamp");
        System.out.println(timestamp);
        String nonce=request.getParameter("nonce");
        System.out.println(nonce);
        String echostr=request.getParameter("echostr");
        System.out.println(echostr);

//        if (WeChatUtil.isWeChat(timestamp,nonce,signature)){
//            return echostr;
//        }else {
//            return null;
//        }
        return echostr;
    }
    @RequestMapping(value = "/getcode")
    public String getCode(HttpServletRequest request) {
        String code=request.getParameter("s");
        System.out.println(code);
        //假装这里写着代码能把code存到数据库里去
        return code;
    }
}
