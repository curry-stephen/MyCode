package com.yy.dao;

import com.yy.pojo.Vip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface VipMapper {
    
    int addVip(Vip vip);

    int deleteVipById(@Param("id") int id);

    int updateVipById(Vip vip);

    Vip queryVipById(@Param("id") int id);

    ArrayList<Vip> queryAllVip();

    Vip queryVipByPhone(@Param("phone") String phone); 
}
