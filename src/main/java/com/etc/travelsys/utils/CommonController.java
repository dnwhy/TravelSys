package com.etc.travelsys.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class CommonController {
    //跳转页面的通用方法
    @RequestMapping("toPage")
    public String toPage(String url, HttpSession session,
                         @RequestParam(required = false) String isCheck){

        if (!StringUtils.isEmpty(isCheck)){
            session.setAttribute("isCheck",isCheck);
        }

        //经过视图解析器，为url自动添加前缀和后缀  templates/url.html
        return url;

    }
}
