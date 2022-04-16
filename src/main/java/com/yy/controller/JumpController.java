package com.yy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jump")
public class JumpController {
    
    @RequestMapping("/guests_add")
    public String guests(){
        return "guests_add";
    }
    @RequestMapping("/vip_add")
    public String vip(){
        return "vip_add";
    }
    @RequestMapping("/home_add")
    public String home(){
        return "home_add";
    }
    @RequestMapping("/excel_down")
    public String excel(){
        return "excel_down";
    }
    @RequestMapping("/pwd_update")
    public String pwd(){
        return "redirect:/admin/login";
    }
    @RequestMapping("/web_cache")
    public String cache(){
        return "web_cache";
    }
    @RequestMapping("/web_index")
    public String wb_index(){
        return "web_index";
    }
    @RequestMapping("/index2")
    public String index(){
        return "index2";
    }
    @RequestMapping("/exit")
    public String exit(){
        return "exit";
    }
}
