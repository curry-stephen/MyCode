package com.yy.service;

import com.yy.dao.AdminMapper;
import com.yy.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {
    
    private AdminMapper adminMapper;
    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public int updatePwd(Admin admin) {
        return adminMapper.updatePwd(admin);
    }

    public Admin findAdmin(String username, String password) {
        return adminMapper.findAdmin(username,password);
    }

    
}
