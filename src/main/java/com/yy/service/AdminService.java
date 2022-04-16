package com.yy.service;

import com.yy.pojo.Admin;
import org.springframework.stereotype.Service;


public interface AdminService {
    int updatePwd(Admin admin);

    Admin findAdmin(String username,String password);
}
