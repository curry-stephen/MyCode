package com.yy.dao;

import com.yy.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AdminMapper {
    int updatePwd(Admin admin);
    
    Admin findAdmin(@Param("username") String username, @Param("password") String password);
}
