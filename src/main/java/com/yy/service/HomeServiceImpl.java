package com.yy.service;

import com.yy.dao.HomeMapper;
import com.yy.pojo.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class HomeServiceImpl implements HomeService {
    
    private HomeMapper homeMapper;
    @Autowired
    public void setHomeMapper(HomeMapper homeMapper) {
        this.homeMapper = homeMapper;
    }

    public int addHome(Home home) {
        return homeMapper.addHome(home);
    }

    public int deleteHomeById(int id) {
        return homeMapper.deleteHomeById(id);
    }

    public int updateHomeById(Home home) {
        return homeMapper.updateHomeById(home);
    }

    public Home queryHomeById(Integer id) {
        return homeMapper.queryHomeById(id);
    }

    public ArrayList<Home> queryAllHome() {
        return homeMapper.queryAllHome();
    }

    public Home queryHomeByNum(int num) {
        return homeMapper.queryHomeByNum(num);
    }

    public int updateH_TypeById(Home home) {
        return homeMapper.updateH_TypeById(home);
    }
}
