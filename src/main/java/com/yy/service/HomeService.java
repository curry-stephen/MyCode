package com.yy.service;

import com.yy.pojo.Home;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface HomeService {
    int addHome(Home home);

    int deleteHomeById( int id);

    int updateHomeById(Home home);

    Home queryHomeById(Integer id);

    ArrayList<Home> queryAllHome();

    Home queryHomeByNum( int num);

    int updateH_TypeById(Home home);
}
