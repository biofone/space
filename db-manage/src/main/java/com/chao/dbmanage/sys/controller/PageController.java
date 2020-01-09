package com.chao.dbmanage.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class PageController {
    @RequestMapping("doIndexUI")
    public String doIndexUI() {
        return "starter";
    }
    //日志页面
    @RequestMapping("doLogUI")
    public String doLogUI() {
        return "sys/log_list";
    }
    //分页页面
    @RequestMapping("doPageUI")
    public String doPageUI() {
        return "common/page";
    }
    //登录页面
    @RequestMapping("doLoginUI")
    public String doLoginUI() {
        return "login";
    }
    @RequestMapping("{module}/{moduleUI}")
    public String doMuduleUI(
            @PathVariable String moduleUI) {
        return "sys/"+moduleUI;
    }


}
