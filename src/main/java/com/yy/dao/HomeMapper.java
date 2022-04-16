package com.yy.dao;

import com.yy.pojo.Home;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface HomeMapper {
    int addHome(Home home);
    
    int deleteHomeById(@Param("id") int id);
    
    int updateHomeById(Home home);
    
    Home queryHomeById(@Param("id") Integer id);
    
    ArrayList<Home> queryAllHome();
    
    Home queryHomeByNum(@Param("num") int num);
    
    int updateH_TypeById(Home home);
}
