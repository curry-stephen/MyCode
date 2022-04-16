package com.yy.service;

import com.yy.pojo.Vip;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface VipService {
    int addVip(Vip vip);

    int deleteVipById(int id);

    int updateVipById(Vip vip);

    Vip queryVipById(int id);

    ArrayList<Vip> queryAllVip();

    Vip queryVipByPhone(String phone);
}
