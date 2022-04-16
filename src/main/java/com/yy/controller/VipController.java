package com.yy.controller;

import com.yy.pojo.Vip;
import com.yy.service.VipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vip")
public class VipController {
    @Autowired
    VipServiceImpl vipService;
    
    @PostMapping("/add")
    @ResponseBody
    public ModelAndView add(Vip vip){
        ModelAndView modelAndView = new ModelAndView();
         vipService.addVip(vip);
        int i = vipService.addVip(vip);
        System.out.println(vipService.queryVipById(i));
        modelAndView.setViewName("suc_v");
         return modelAndView;
    }
    @RequestMapping("/delete")
    public String delete(int id){
        vipService.deleteVipById(id);
        return "redirect:/vip/list";
    }
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView view = new ModelAndView();
        List<Vip> vips = vipService.queryAllVip();
        view.addObject("list",vips);
        view.setViewName("vip_list");
        return view;
    }
    @RequestMapping("/update1")
    public ModelAndView update1(int id){
        ModelAndView mv = new ModelAndView();
        Vip vip = vipService.queryVipById(id);
        mv.addObject("v",vip);
        mv.setViewName("vip_update");
        return mv;
    }
    @RequestMapping("/update2")
    public String update2(Vip vip){
        vipService.updateVipById(vip);
        return "redirect:/vip/list";
    }
    @RequestMapping("/find")
    public ModelAndView find(String findByPhone) {
        ModelAndView mv = new ModelAndView();
        Vip vip = vipService.queryVipByPhone(findByPhone);
        
        List<Vip> list = new ArrayList<Vip>();
        list.add(vip);
        if (vip == null) {
            list = vipService.queryAllVip();
            mv.addObject("error", "未查询出结果");
        }
        mv.addObject("list", list);
        mv.setViewName("vip_list");
        return mv;
    } 
}
