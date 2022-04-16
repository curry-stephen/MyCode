package com.yy.controller;

import com.yy.pojo.Admin;
import com.yy.service.AdminService;
import com.yy.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    
    @RequestMapping("/login")
    public String login(String username,String password){
        Admin admin = adminService.findAdmin(username, password);
        if (admin!=null &&admin.getPassword()!=null && admin.getPassword().equals(password)){
            System.out.println(admin.getUsername()+"\n"+admin.getPassword());
            return "index2";
            }else {
                return "error";
            }
        }

        
        
        @RequestMapping("/updatePwd")
        public String updatePwd(Admin admin){
            adminService.updatePwd(admin);
            System.out.println(admin);
            return "suc_a";
        }
    }

